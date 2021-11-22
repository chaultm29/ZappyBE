package com.example.springboot.dto;

public class LevelDTO {
    private Integer level;
    private Integer  percentage;

    public LevelDTO() {
    }

    public LevelDTO(Integer level, Integer percentage) {
        this.level = level;
        this.percentage = percentage;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
