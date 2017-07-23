package com.sreepapers.app.web.dao;

import java.util.List;

import com.sreepapers.app.web.model.PatternSubjectRecord;

public interface PatternSubjectRecordDAO {
	
	public void addPatternSubjectRecord(PatternSubjectRecord patternSubjectRecord);
	public void updatePatternSubjectRecord(PatternSubjectRecord patternSubjectRecord);
	public List<PatternSubjectRecord> listPatternSubjectRecords();
	public PatternSubjectRecord getPatternSubjectRecordById(long patternSubjectRecordId);
	public void removePatternSubjectRecord(long patternSubjectRecordId);
}
