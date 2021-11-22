package com.example.springboot.dto;

public class ProgressDTO {
	private Double vocaProgress;
	private Double kanjiProgress;
	private Double grammarProgess;
	private Double progressAll;

	public ProgressDTO() {
	}

	public ProgressDTO(Double vocaProgress, Double kanjiProgress, Double grammarProgess, Double progressAll) {
		this.vocaProgress = vocaProgress;
		this.kanjiProgress = kanjiProgress;
		this.grammarProgess = grammarProgess;
		this.progressAll = progressAll;
	}

	public Double getVocaProgress() {
		return vocaProgress;
	}

	public void setVocaProgress(Double vocaProgress) {
		this.vocaProgress = vocaProgress;
	}

	public Double getKanjiProgress() {
		return kanjiProgress;
	}

	public void setKanjiProgress(Double kanjiProgress) {
		this.kanjiProgress = kanjiProgress;
	}

	public Double getGrammarProgess() {
		return grammarProgess;
	}

	public void setGrammarProgess(Double grammarProgess) {
		this.grammarProgess = grammarProgess;
	}

	public Double getProgressAll() {
		return progressAll;
	}

	public void setProgressAll(Double progressAll) {
		this.progressAll = progressAll;
	}
}
