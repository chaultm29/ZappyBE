package com.example.springboot.dto;

public class ProgressDTO {
	private Integer vocaProgress;
	private Integer kanjiProgress;
	private Integer grammarProgess;
	private Integer progressAll;

	public ProgressDTO() {
	}

	public ProgressDTO(Integer vocaProgress, Integer kanjiProgress, Integer grammarProgess, Integer progressAll) {
		super();
		this.vocaProgress = vocaProgress;
		this.kanjiProgress = kanjiProgress;
		this.grammarProgess = grammarProgess;
		this.progressAll = progressAll;
	}

	public Integer getVocaProgress() {
		return vocaProgress;
	}

	public void setVocaProgress(Integer vocaProgress) {
		this.vocaProgress = vocaProgress;
	}

	public Integer getKanjiProgress() {
		return kanjiProgress;
	}

	public void setKanjiProgress(Integer kanjiProgress) {
		this.kanjiProgress = kanjiProgress;
	}

	public Integer getGrammarProgess() {
		return grammarProgess;
	}

	public void setGrammarProgess(Integer grammarProgess) {
		this.grammarProgess = grammarProgess;
	}

	public Integer getProgressAll() {
		return progressAll;
	}

	public void setProgressAll(Integer progressAll) {
		this.progressAll = progressAll;
	}

	
	
}
