package com.sreepapers.app.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RESULTPATTERN")
public class ResultPattern {

	@Id
	@Column(name="resultPatternId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long resultPatternId;
	private float marksPerAnswer;
	private float negativePerWrong;
	private float passPercentage;
	
	public long getResultPatternId() {
		return resultPatternId;
	}
	public void setResultPatternId(long resultPatternId) {
		this.resultPatternId = resultPatternId;
	}
	public float getPassPercentage() {
		return passPercentage;
	}
	public void setPassPercentage(float passPercentage) {
		this.passPercentage = passPercentage;
	}
	public float getMarksPerAnswer() {
		return marksPerAnswer;
	}
	public void setMarksPerAnswer(float marksPerAnswer) {
		this.marksPerAnswer = marksPerAnswer;
	}
	public float getNegativePerWrong() {
		return negativePerWrong;
	}
	public void setNegativePerWrong(float negativePerWrong) {
		this.negativePerWrong = negativePerWrong;
	}
}
