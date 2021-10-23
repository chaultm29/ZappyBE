package com.example.springboot.entities;

import java.util.Date;

public class Exam {
	private Long score;
	private Date createdDate;
	private Date time;

	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam(Long score, Date createdDate, Date time) {
		super();
		this.score = score;
		this.createdDate = createdDate;
		this.time = time;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
