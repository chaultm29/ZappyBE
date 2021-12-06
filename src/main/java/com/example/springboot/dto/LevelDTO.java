package com.example.springboot.dto;

public class LevelDTO {
    private Integer level;
    private Long  currentExp;
    private Long  levelExp;

    public LevelDTO() {
    }

	public LevelDTO(Integer level, Long curentExp, Long levelExp) {
		super();
		this.level = level;
		this.currentExp = curentExp;
		this.levelExp = levelExp;
	}
	
	//{0, 100, 300, 600, 1000, 1500, 2100, 2800, 3600, 4500, 5500, 6600, 7800, 9100};
	public void analysisExp(Long totalExp) {
		int _level = 0;
		int sumExp = 0;
		while ((_level+1) * 100 + sumExp <= totalExp) {
			sumExp += ++_level * 100;			
		}
		this.level = _level;
		this.currentExp = totalExp - sumExp;
		this.levelExp = (_level+1) * 100l;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getCurrentExp() {
		return currentExp;
	}

	public void setCurrentExp(Long currentExp) {
		this.currentExp = currentExp;
	}

	public Long getLevelExp() {
		return levelExp;
	}

	public void setLevelExp(Long levelExp) {
		this.levelExp = levelExp;
	}
    
}
