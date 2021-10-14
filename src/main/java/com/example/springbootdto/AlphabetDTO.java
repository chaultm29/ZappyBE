package com.example.springbootdto;

public class AlphabetDTO {
	private boolean isHiragana;

	public AlphabetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlphabetDTO(boolean isHiragana) {
		super();
		this.isHiragana = isHiragana;
	}

	public boolean isHiragana() {
		return isHiragana;
	}

	public void setHiragana(boolean isHiragana) {
		this.isHiragana = isHiragana;
	}

}
