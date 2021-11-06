package com.example.springboot.dto;

public class QuestionNotExitDTO {
	private Long idLession;
	private Long idSkill;
	private Long idType;

	public QuestionNotExitDTO() {
	}

	public QuestionNotExitDTO(Long idLession, Long idSkill, Long idType) {
		this.idLession = idLession;
		this.idSkill = idSkill;
		this.idType = idType;
	}

	public Long getIdLession() {
		return idLession;
	}

	public void setIdLession(Long idLession) {
		this.idLession = idLession;
	}

	public Long getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(Long idSkill) {
		this.idSkill = idSkill;
	}

	public Long getIdType() {
		return idType;
	}

	public void setIdType(Long idType) {
		this.idType = idType;
	}

	@Override
	public boolean equals(Object obj) {
		QuestionNotExitDTO questionNotExitDTO = (QuestionNotExitDTO) obj;
		return this.idLession.equals(questionNotExitDTO.idLession) && this.idSkill.equals(questionNotExitDTO.idSkill)
				&& this.idType.equals(questionNotExitDTO.idType);
	}

}
