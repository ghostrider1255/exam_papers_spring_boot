package com.sreepapers.app.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUBJECT")
public class Subject {

	@Id
	@Column(name="subjectId")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long subjectId;
	private String subjectCode;
	private String subjectDesc;
	private boolean subjectStatus;
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	public boolean isSubjectStatus() {
		return subjectStatus;
	}
	public void setSubjectStatus(boolean subjectStatus) {
		this.subjectStatus = subjectStatus;
	}
	
}
