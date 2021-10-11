package com.example.springboot.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "phone")
	private int phone;

	@Column(name = "email")
	private String email;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private AccountEntity accountEntity;

	@ManyToMany
	@JoinTable(name = "user_achievement_rf", 
			joinColumns = @JoinColumn(name = "achievement_id"), 
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<AchievenmentEntity> achievenmentEntities;
	
	@ManyToMany
	@JoinTable(name = "user_alphabet_rf", 
			joinColumns = @JoinColumn(name = "alphabet_id"), 
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<AlphabetEntity> alphabetEntities;
	
	@ManyToMany
	@JoinTable(name = "user_kanji_rf", 
			joinColumns = @JoinColumn(name = "kanji_id"), 
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<KanjiEntity> kanjiEntities;
	
	@ManyToMany
	@JoinTable(name = "user_vocabulary_rf", 
			joinColumns = @JoinColumn(name = "vocabulary_id"), 
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<VocabularyEntity> vocabularyEntities;
	
	@ManyToMany
	@JoinTable(name = "user_grammar_rf", 
			joinColumns = @JoinColumn(name = "grammar_id"), 
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<GrammarEntity> grammarEntities;
	

	public UserEntity() {
	}

	public UserEntity(Long id, String fullName, int phone, String email, Date dateOfBirth,
			AccountEntity accountEntity) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.accountEntity = accountEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}

}
