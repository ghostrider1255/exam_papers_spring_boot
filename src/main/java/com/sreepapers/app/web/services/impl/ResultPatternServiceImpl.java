package com.sreepapers.app.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sreepapers.app.web.dao.ResultPatternDAO;
import com.sreepapers.app.web.model.ResultPattern;
import com.sreepapers.app.web.services.ResultPatternService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ResultPatternServiceImpl implements ResultPatternService{

	@Autowired
	private ResultPatternDAO resultPatternDAO;
	
	public void setResultPatternDAO(ResultPatternDAO resultPatternDAO) {
		this.resultPatternDAO = resultPatternDAO;
	}
	
	@Override
	@Transactional
	public void addResultPattern(ResultPattern resultPattern) {
		this.resultPatternDAO.addResultPattern(resultPattern);
		
	}

	@Override
	@Transactional
	public void updateResultPattern(ResultPattern resultPattern) {
		this.resultPatternDAO.updateResultPattern(resultPattern);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<ResultPattern> listResultPatterns() {
		return this.resultPatternDAO.listResultPatterns();
	}

	@Override
	@Transactional
	public ResultPattern getResultPatternById(long resultPatternId) {
		return this.resultPatternDAO.getResultPatternById(resultPatternId);
	}

	@Override
	@Transactional
	public void removeResultPattern(long resultPatternId) {
		this.resultPatternDAO.removeResultPattern(resultPatternId);
	}
}
