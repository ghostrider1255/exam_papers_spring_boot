package com.sreepapers.app.web.dao;

import java.util.List;

import com.sreepapers.app.web.model.ResultPattern;

public interface ResultPatternDAO {
	
	public void addResultPattern(ResultPattern resultPattern);
	public void updateResultPattern(ResultPattern resultPattern);
	public List<ResultPattern> listResultPatterns();
	public ResultPattern getResultPatternById(long resultPatternId);
	public void removeResultPattern(long resultPatternId);
}
