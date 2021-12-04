package com.example.springboot.dto;

public class LevelDTO {
    private Integer level;
    private Integer  curentExp;
    private Integer  levelExp;

    public LevelDTO() {
    }

	public LevelDTO(Integer level, Integer curentExp, Integer levelExp) {
		super();
		this.level = level;
		this.curentExp = curentExp;
		this.levelExp = levelExp;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getCurentExp() {
		return curentExp;
	}

	public void setCurentExp(Integer curentExp) {
		this.curentExp = curentExp;
	}

	public Integer getLevelExp() {
		return levelExp;
	}

	public void setLevelExp(Integer levelExp) {
		this.levelExp = levelExp;
	}

    
}
