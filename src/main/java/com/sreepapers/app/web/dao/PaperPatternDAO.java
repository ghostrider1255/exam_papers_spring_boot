package com.sreepapers.app.web.dao;

import java.util.List;
import java.util.Map;

import com.sreepapers.app.web.model.ExamQuestion;
import com.sreepapers.app.web.model.PaperPattern;

public interface PaperPatternDAO {
	
	public void addPaperPattern(PaperPattern paperPattern);
	public void updatePaperPattern(PaperPattern paperPattern);
	public List<PaperPattern> listPaperPatterns();
	public PaperPattern getPaperPatternById(long paperPatternId);
	public void removePaperPattern(long paperPatternId);
	Map<String, ExamQuestion> getQuestionByPatternId(long patternId);
}
