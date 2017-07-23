package com.sreepapers.app.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sreepapers.app.web.dao.PatternSubjectRecordDAO;
import com.sreepapers.app.web.model.PatternSubjectRecord;
import com.sreepapers.app.web.services.PatternSubjectRecordService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PatternSubjectRecordServiceImpl implements PatternSubjectRecordService{

	@Autowired
	private PatternSubjectRecordDAO patternSubjectRecordDAO;
	
	public void setPatternSubjectRecordDAO(PatternSubjectRecordDAO patternSubjectRecordDAO) {
		this.patternSubjectRecordDAO = patternSubjectRecordDAO;
	}
	
	@Override
	@Transactional
	public void addPatternSubjectRecord(PatternSubjectRecord patternSubjectRecord) {
		this.patternSubjectRecordDAO.addPatternSubjectRecord(patternSubjectRecord);
		
	}

	@Override
	@Transactional
	public void updatePatternSubjectRecord(PatternSubjectRecord patternSubjectRecord) {
		this.patternSubjectRecordDAO.updatePatternSubjectRecord(patternSubjectRecord);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<PatternSubjectRecord> listPatternSubjectRecords() {
		return this.patternSubjectRecordDAO.listPatternSubjectRecords();
	}

	@Override
	@Transactional
	public PatternSubjectRecord getPatternSubjectRecordById(long patternSubjectRecordId) {
		return this.patternSubjectRecordDAO.getPatternSubjectRecordById(patternSubjectRecordId);
	}

	@Override
	@Transactional
	public void removePatternSubjectRecord(long patternSubjectRecordId) {
		this.patternSubjectRecordDAO.removePatternSubjectRecord(patternSubjectRecordId);
		
	}

}
