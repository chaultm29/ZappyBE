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
//		RoleEntity roleEntity = roleRepository.findById((long)3).get();// c???n s???a role ch??? n??y
//		accountEntity.setRoleEntity(roleEntity);
		return accountRepository.save(accountEntity);
	}

	@Transactional
	public String save(AccountDTO accountDTO) {
		AccountEntity accountEntity1 = accountRepository.findByUsername(accountDTO.getUsername());
		if (accountEntity1 != null) {
			return "Username: " + accountEntity1.getUsername() + " ???? t???n t???i trong h??? th???ng!";
		}
		UserEntity userEntity1 = userRepository.getUserByEmail(accountDTO.getEmail());
		if (userEntity1 != null) {
			return "Email: " + accountDTO.getEmail() + " ???? t???n t???i trong h??? th???ng!";
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
		return "T???o t??i kho???n " + afterSave.getUsername() + " th??nh c??ng";
	}

	public String update(Long id, AccountDTO accountDetails) {
		try {
			AccountEntity accountEntity = accountRepository.getAccountEnableByID(id);
			if (accountEntity == null) {
				return "Kh??ng t???n t???i account v???i id l??: " + id;
			}
			AccountEntity accountEntity1 = accountRepository.findByUsername(accountDetails.getUsername());
			AccountEntity accountEntity2 = accountRepository.findByEmail(accountDetails.getEmail());

			if (accountEntity1 != null && !accountEntity1.getId().equals(accountEntity.getId())) {
				return "Username: " + accountDetails.getUsername() + " ???? t???n t???i trong h??? th???ng!";
			}
			if (accountEntity2 != null && !accountEntity2.getId().equals(accountEntity.getId())) {
				return "Email: " + accountDetails.getEmail() + " ???? t???n t???i trong h??? th???ng!";
			}
			AccountEntity updateAccountEntity = accountConverter.toEntity(accountDetails, accountEntity);
			AccountEntity afterSave = accountRepository.save(updateAccountEntity);
			afterSave.setPassword("");
			return "C???p nh???t account " + afterSave.getUsername() + " th??nh c??ng";
		} catch (Exception e) {
			return "C???p nh???t account kh??ng th??nh c??ng";
		}
	}

	public HashMap<String, Object> resetPassword(String username) throws UnsupportedEncodingException, MessagingException {
		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		String mess = "";
		AccountEntity accountEntity = accountRepository.findByUsername(username);
		String newPassword = RandomString.make(8);
		accountEntity.setPassword(newPassword);
		sendResetPwEmail(accountEntity.getUserEntity().getEmail(), newPassword);
		AccountEntity afterSave = saveAccount(accountEntity);
		mess = "C???p nh???t account " + afterSave.getUsername() + " th??nh c??ng";
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
			sendEmail(email, "https://www.zappy-js.com/forgot?email=" + email + "&token=" + token);
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
//				+ "<p><b>[B???N TI???NG VI???T]</b><p>"
//				+"<p>Xin ch??o,<p>"
//				+ "<p>Ch??ng t??i nh???n ???????c y??u c???u ?????i m???t kh???u t??? b???n.</p>"
//				+ "<p>Vui l??ng v??o ???????ng link b??n d?????i ????? ?????i m???t kh???u:</p>"
//				+ "<p><a href=\""+ resetPasswordLink + "\">?????i m???t kh???u</a><b></p>"
//				+ "<p>Vui l??ng b??? qua email n??y n???u b???n c??n nh??? m???t kh???u, ho???c b???n kh??ng ph???i l?? ng?????i ???? g???i request</p>"
//				+ "==============================================================================="
//				+ "<p><b>[????????????]</b><p>"
//				+ "<p>ZAPPY??????????????????,<p>"
//				+ "<p>????????????????????????????????????????????????????????????????????????</p>"
//				+ "<p>?????????????????????????????????????????????????????????????????????????????????????????????</p>"
//				+ "<p><a href=\""+ resetPasswordLink + "\">????????????????????????</a><b></p>"
//				+ "<p>????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????</p>"
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}
	private void sendResetPwEmail(String email, String randomPassword)
			throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(email);

		String subject = "[ZAPPY]Reset password";

		String content = "<p>Hello,<p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Here is your new password: " + randomPassword + "</p>"
				+ "<p>Thank you for use our service.</p>";
			
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}
}
