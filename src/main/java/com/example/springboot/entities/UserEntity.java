package com.example.springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "avatar")
	private String avatar;

	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<UserAchievenmentEntity> userAchievenmentEntities;

	@ManyToMany
	@JoinTable(name = "user_alphabet_rf", joinColumns = @JoinColumn(name = "alphabet_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<AlphabetEntity> alphabetEntities;

	@ManyToMany
	@JoinTable(name = "user_kanji_rf", joinColumns = @JoinColumn(name = "kanji_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<KanjiEntity> kanjiEntities;

	@ManyToMany
	@JoinTable(name = "user_vocabulary_rf", joinColumns = @JoinColumn(name = "vocabulary_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<VocabularyEntity> vocabularyEntities;

	@ManyToMany
	@JoinTable(name = "user_grammar_rf", joinColumns = @JoinColumn(name = "grammar_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<GrammarEntity> grammarEntities;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<ExamEntity> examEntities;

	@JsonIgnore
	@OneToMany(mappedBy = "userEntities", cascade = CascadeType.ALL)
	private Set<PracticeEntiry> paPracticeEntiries;

	public UserEntity() {
	}

	public UserEntity(Long id, String fullName, String phone, String email, Date dateOfBirth, String avatar, Set<UserAchievenmentEntity> userAchievenmentEntities, Set<AlphabetEntity> alphabetEntities, Set<KanjiEntity> kanjiEntities, Set<VocabularyEntity> vocabularyEntities, Set<GrammarEntity> grammarEntities, Set<ExamEntity> examEntities, Set<PracticeEntiry> paPracticeEntiries) {
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.avatar = avatar;
		this.userAchievenmentEntities = userAchievenmentEntities;
		this.alphabetEntities = alphabetEntities;
		this.kanjiEntities = kanjiEntities;
		this.vocabularyEntities = vocabularyEntities;
		this.grammarEntities = grammarEntities;
		this.examEntities = examEntities;
		this.paPracticeEntiries = paPracticeEntiries;
	}

	public Set<ExamEntity> getExamEntities() {
		return examEntities;
	}

	public void setExamEntities(Set<ExamEntity> examEntities) {
		this.examEntities = examEntities;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<UserAchievenmentEntity> getUserAchievenmentEntities() {
		return userAchievenmentEntities;
	}

	public void setUserAchievenmentEntities(Set<UserAchievenmentEntity> userAchievenmentEntities) {
		this.userAchievenmentEntities = userAchievenmentEntities;
	}

	public Set<PracticeEntiry> getPaPracticeEntiries() {
		return paPracticeEntiries;
	}

	public void setPaPracticeEntiries(Set<PracticeEntiry> paPracticeEntiries) {
		this.paPracticeEntiries = paPracticeEntiries;
	}

	public Set<AlphabetEntity> getAlphabetEntities() {
		return alphabetEntities;
	}

	public void setAlphabetEntities(Set<AlphabetEntity> alphabetEntities) {
		this.alphabetEntities = alphabetEntities;
	}

	public Set<KanjiEntity> getKanjiEntities() {
		return kanjiEntities;
	}

	public void setKanjiEntities(Set<KanjiEntity> kanjiEntities) {
		this.kanjiEntities = kanjiEntities;
	}

	public Set<VocabularyEntity> getVocabularyEntities() {
		return vocabularyEntities;
	}

	public void setVocabularyEntities(Set<VocabularyEntity> vocabularyEntities) {
		this.vocabularyEntities = vocabularyEntities;
	}

	public Set<GrammarEntity> getGrammarEntities() {
		return grammarEntities;
	}

	public void setGrammarEntities(Set<GrammarEntity> grammarEntities) {
		this.grammarEntities = grammarEntities;
	}

}
