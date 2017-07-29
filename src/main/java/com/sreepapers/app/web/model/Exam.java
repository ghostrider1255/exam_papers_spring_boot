package com.sreepapers.app.web.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="EXAM")
public class Exam {

	@Id
	@Column(name="examId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long examId;
	private String examCode;
	private String examDesc;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= "paperPatternId")
	private PaperPattern paperPattern;
	@OneToMany(cascade = CascadeType.ALL,mappedBy="exam", fetch = FetchType.LAZY)
	@MapKeyColumn(name="subjectType",nullable=true)
	private Map<String,ExamQuestion> examQuestions = new HashMap<String,ExamQuestion>();
	@OneToOne(cascade = CascadeType.ALL)
	private ResultPattern resultPattern;
	
	public long getExamId() {
		return examId;
	}
	public void setEaxmId(long examId) {
		this.examId = examId;
	}
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public String getExamDesc() {
		return examDesc;
	}
	public void setExamDesc(String examDesc) {
		this.examDesc = examDesc;
	}
	public ResultPattern getResultPattern() {
		return resultPattern;
	}
	public void setResultPattern(ResultPattern resultPattern) {
		this.resultPattern = resultPattern;
	}
	public PaperPattern getPaperPattern() {
		return paperPattern;
	}
	public void setPaperPattern(PaperPattern paperPattern) {
		this.paperPattern = paperPattern;
	}
	public Map<String,ExamQuestion> getExamQuestions() {
		return examQuestions;
	}
	public void setExamQuestions(Map<String,ExamQuestion> examQuestions) {
		Map<String,ExamQuestion> dupExamQuestions = new HashMap<String,ExamQuestion>();
		if(examQuestions!=null & examQuestions.size()>0){
			Iterator<String> iterator = examQuestions.keySet().iterator();
			while(iterator.hasNext())
			{
				String key = (String)iterator.next();
				ExamQuestion dupObje = examQuestions.get(key);
				dupObje.setExam(this);
				dupExamQuestions.put(key, dupObje);
			}
			this.examQuestions = dupExamQuestions;
		}
		else{
			this.examQuestions = null;
		}
	}
}
