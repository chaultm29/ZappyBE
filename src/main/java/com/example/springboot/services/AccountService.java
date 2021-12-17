package com.example.springboot.services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.springboot.converters.UserConverter;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.entities.UserEntity;
import com.example.springboot.repositories.UserRepository;
//import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javassist.NotFoundException;
import net.bytebuddy.utility.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.AccountConverter;
import com.example.springboot.dto.AccountDTO;
import com.example.springboot.dto.PasswordDTO;
import com.example.springboot.entities.AccountEntity;
import com.example.springboot.entities.RoleEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.AccountRepository;
import com.example.springboot.repositories.RoleRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AccountConverter accountConverter;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserConverter userConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;

	public AccountEntity findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	public AccountEntity saveAccount(AccountEntity accountEntity) {
		accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
		accountEntity.setIsEnabled(true);
//		RoleEntity roleEntity = roleRepository.findById((long)3).get();// cần sửa role chỗ này
//		accountEntity.setRoleEntity(roleEntity);
		return accountRepository.save(accountEntity);
	}

	@Transactional
	public String save(AccountDTO accountDTO) {
		AccountEntity accountEntity1 = accountRepository.findByUsername(accountDTO.getUsername());
		if (accountEntity1 != null) {
			return "Username: " + accountEntity1.getUsername() + " đã tồn tại trong hệ thống!";
		}
		UserEntity userEntity1 = userRepository.getUserByEmail(accountDTO.getEmail());
		if (userEntity1 != null) {
			return "Email: " + accountDTO.getEmail() + " đã tồn tại trong hệ thống!";
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setAvatar(accountDTO.getAvatar());
		userDTO.setEmail(accountDTO.getEmail());
		userDTO.setDateOfBirth(accountDTO.getDateOfBirth());
		userDTO.setPhone(accountDTO.getPhone());
		userDTO.setFullName(accountDTO.getFullName());
		UserEntity userEntity = userConverter.toEntity(userDTO);
		userEntity.setExp(0l);
		AccountEntity accountEntity = accountConverter.toEntity(accountDTO);
		accountEntity.setUserEntity(userEntity);

		AccountEntity afterSave = saveAccount(accountEntity);
		return "Tạo tài khoản " + afterSave.getUsername() + " thành công";
	}

	public String update(Long id, AccountDTO accountDetails) {
		try {
			AccountEntity accountEntity = accountRepository.getAccountEnableByID(id);
			if (accountEntity == null) {
				return "Không tồn tại account với id là: " + id;
			}
			AccountEntity accountEntity1 = accountRepository.findByUsername(accountDetails.getUsername());
			AccountEntity accountEntity2 = accountRepository.findByEmail(accountDetails.getEmail());

			if (accountEntity1 != null && !accountEntity1.getId().equals(accountEntity.getId())) {
				return "Username: " + accountDetails.getUsername() + " đã tồn tại trong hệ thống!";
			}
			if (accountEntity2 != null && !accountEntity2.getId().equals(accountEntity.getId())) {
				return "Email: " + accountDetails.getEmail() + " đã tồn tại trong hệ thống!";
			}
			AccountEntity updateAccountEntity = accountConverter.toEntity(accountDetails, accountEntity);
			AccountEntity afterSave = accountRepository.save(updateAccountEntity);
			afterSave.setPassword("");
			return "Cập nhật account " + afterSave.getUsername() + " thành công";
		} catch (Exception e) {
			return "Cập nhật account không thành công";
		}
	}

	public HashMap<String, Object> resetPassword(String username) {
		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		String mess = "";
		AccountEntity accountEntity = accountRepository.findByUsername(username);

		accountEntity.setPassword("123456");
		AccountEntity afterSave = saveAccount(accountEntity);
		mess = "Cập nhật account " + afterSave.getUsername() + " thành công";
		stringObjectHashMap.put("message", mess);
		return stringObjectHashMap;
	}

	public void delete(Long id) {
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));
//		accountRepository.delete(accountEntity);
		accountEntity.setIsEnabled(false);
		accountRepository.save(accountEntity);
	}

	public AccountDTO get(Long id) {
		AccountEntity accountEntity = accountRepository.getAccountEnableByID(id);
		AccountDTO accountDTO = (accountEntity != null) ? accountConverter.toDTO(accountEntity) : new AccountDTO();
		accountDTO.setPasswordNew("");
		accountDTO.setPasswordOld("");
		return accountDTO;
	}

	public List<AccountDTO> get() {
		List<AccountEntity> accountEntities = accountRepository.getAllAccountEnable();
		List<AccountDTO> accountDTOS = (accountEntities != null && accountEntities.size() != 0)
				? accountConverter.toDTOs(accountEntities)
				: new ArrayList<>();
		for (AccountDTO accountDTO : accountDTOS) {
			accountDTO.setPasswordNew("");
			accountDTO.setPasswordOld("");
		}
		return accountDTOS;
	}

	public boolean changePassword(PasswordDTO passwordDTO) {
		if (!passwordDTO.isEqual()) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			AccountEntity accountEntity = accountRepository.findByUsername(username);
			if (passwordEncoder.matches(passwordDTO.getOldPassword(), accountEntity.getPassword())) {
				accountEntity.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
				accountRepository.save(accountEntity);
				return true;
			}
		}
		return false;
	}

	public boolean setNewPassword(String email, String token, PasswordDTO passwordDTO) {
		AccountEntity accountEntity = accountRepository.findByEmail(email);
		if (accountEntity != null && token.equals(accountEntity.getPwToken())) {
			updatePwByToken(accountEntity, passwordDTO.getNewPassword());
			return true;
		}
		return false;
	}

	public boolean checkForgotPassword(String email, String token) throws Exception {
		AccountEntity accountEntity1 = accountRepository.findByEmail(email);
		AccountEntity accountEntity2 = getByPwToken(token);
		if (accountEntity1 != null && accountEntity2 != null) {
			return accountEntity1.getPwToken().compareTo(token) == 0
					&& accountEntity2.getUserEntity().getEmail().compareTo(email) == 0;
		} else {
			return false;
		}
	}

	private AccountEntity getByPwToken(String resetPasswordToken) {
		return accountRepository.findByPwToken(resetPasswordToken);
	}

	private void updatePwByToken(AccountEntity accountEntity, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		accountEntity.setPassword(encodedPassword);
		accountEntity.setPwToken(null);
		accountRepository.save(accountEntity);
	}

	public void forgotPassword(String email)
			throws UnsupportedEncodingException, MessagingException, NotFoundException {
		AccountEntity accountEntity = accountRepository.findByEmail(email);
		if (accountEntity != null) {
			String token = RandomString.make(45);
			accountEntity.setPwToken(token);
			accountRepository.save(accountEntity);
			sendEmail(email, "http://localhost:3000/forgot?email=" + email + "&token=" + token);
		} else {
			throw new NotFoundException("Could not find any user with this email");
		}
	}

	private void sendEmail(String email, String resetPasswordLink)
			throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(email);

		String subject = "[ZAPPY]Here's the link to reset your password";

		String content = "<p>Hello,<p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + resetPasswordLink
				+ "\">Change my password</a><b></p>"
				+ "<p>Ignore this email if you do remember your password, or you have not made the request</p>";
//				+ "==============================================================================="
//				+ "<p><b>[BẢN TIẾNG VIỆT]</b><p>"
//				+"<p>Xin chào,<p>"
//				+ "<p>Chúng tôi nhận được yêu cầu đổi mật khẩu từ bạn.</p>"
//				+ "<p>Vui lòng vào đường link bên dưới để đổi mật khẩu:</p>"
//				+ "<p><a href=\""+ resetPasswordLink + "\">Đổi mật khẩu</a><b></p>"
//				+ "<p>Vui lòng bỏ qua email này nếu bạn còn nhớ mật khẩu, hoặc bạn không phải là người đã gửi request</p>"
//				+ "==============================================================================="
//				+ "<p><b>[日本語版]</b><p>"
//				+ "<p>ZAPPYのユーザーへ,<p>"
//				+ "<p>パスワードのリセットリクエストを受け取りました。</p>"
//				+ "<p>パスワードを変更するには、以下のリンクをクリックしてください。</p>"
//				+ "<p><a href=\""+ resetPasswordLink + "\">パスワードを変更</a><b></p>"
//				+ "<p>パスワードを覚えている場合、またはあなたがリクエストを行っている人ではない場合は、このメールを無視してください。</p>"
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}
}
