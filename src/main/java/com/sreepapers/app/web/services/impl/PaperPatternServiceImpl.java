package com.sreepapers.app.web.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sreepapers.app.web.dao.PaperPatternDAO;
import com.sreepapers.app.web.model.ExamQuestion;
import com.sreepapers.app.web.model.PaperPattern;
import com.sreepapers.app.web.model.Question;
import com.sreepapers.app.web.services.PaperPatternService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PaperPatternServiceImpl implements PaperPatternService{

	@Autowired
	private PaperPatternDAO paperPaternDAO;
	
	public void setPaperPatternDAO(PaperPatternDAO paperPaternDAO) {
		this.paperPaternDAO = paperPaternDAO;
	}
	
	@Override
	@Transactional
	public void addPaperPattern(PaperPattern paperPattern) {
		this.paperPaternDAO.addPaperPattern(paperPattern);
		
	}

	@Override
	@Transactional
	public void updatePaperPattern(PaperPattern paperPattern) {
		this.paperPaternDAO.updatePaperPattern(paperPattern);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<PaperPattern> listPaperPatterns() {
		return this.paperPaternDAO.listPaperPatterns();
	}

	@Override
	@Transactional
	public PaperPattern getPaperPatternById(long paperPatternId) {
		return this.paperPaternDAO.getPaperPatternById(paperPatternId);
	}

	@Override
	@Transactional
	public void removePaperPattern(long paperPatternId) {
		this.paperPaternDAO.removePaperPattern(paperPatternId);
		
	}

	@Override
	@Transactional
	public Map<String, ExamQuestion> getQuestionByPatternId(long patternId) {
		
		return this.paperPaternDAO.getQuestionByPatternId(patternId);
	}
	
	

}
