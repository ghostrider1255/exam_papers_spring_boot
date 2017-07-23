package com.sreepapers.app.web.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PATTERNSUBJECTRECORD")
public class PatternSubjectRecord {
	
	@Id
	@Column(name="pSubjectRecordId")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long pSubjectRecordId;
	@OneToOne(cascade = CascadeType.ALL)
	private Subject pSubject;
	private long numberOfQuestions;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="paperPatternId")
	private PaperPattern paperPattern;

	public long getpSubjectRecordId() {
		return pSubjectRecordId;
	}
	public void setpSubjectRecordId(long pSubjectRecordId) {
		this.pSubjectRecordId = pSubjectRecordId;
	}
	public Subject getpSubject() {
		return pSubject;
	}
	public void setpSubject(Subject pSubject) {
		this.pSubject = pSubject;
	}
	public long getNumberOfQuestions() {
		return numberOfQuestions;
	}
	public void setNumberOfQuestions(long numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}
	public PaperPattern getPaperPattern() {
		return paperPattern;
	}
	public void setPaperPattern(PaperPattern paperPattern) {
		this.paperPattern = paperPattern;
	}
}
