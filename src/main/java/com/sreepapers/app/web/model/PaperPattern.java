package com.sreepapers.app.web.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PAPERPATTERN")
public class PaperPattern 
{
	@Id
	@Column(name="paperPatternId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long paperPatternId;
	private String paperPatternCode;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy= "paperPattern")
	private List<PatternSubjectRecord> subjectRules = new ArrayList<PatternSubjectRecord>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "paperPattern")
	private Set<Exam> exams = new HashSet<Exam>();
	
	public List<PatternSubjectRecord> getSubjectRules() {
		return subjectRules;
	}

	public void setSubjectRules(List<PatternSubjectRecord> subjectRules) {
		this.subjectRules = subjectRules;
	}

	public long getPaperPatternId() {
		return paperPatternId;
	}

	public void setPaperPatternId(long paperPatternId) {
		this.paperPatternId = paperPatternId;
	}

	public String getPaperPatternCode() {
		return paperPatternCode;
	}

	public void setPaperPatternCode(String paperPatternCode) {
		this.paperPatternCode = paperPatternCode;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		if(exams!=null && exams.size()>0){
			Set<Exam> dupExams = new HashSet<Exam>();
			for(Exam exam : exams){
				exam.setPaperPattern(this);
				dupExams.add(exam);
			}
			this.exams = dupExams;
		}
		else{
			this.exams = null;
		}
	}
}
