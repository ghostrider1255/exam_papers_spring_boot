package com.sreepapers.app.web.services;

import java.util.List;

import com.sreepapers.app.web.model.ResultPattern;

public interface ResultPatternService {
	
	public void addResultPattern(ResultPattern resultPattern);
	public void updateResultPattern(ResultPattern resultPattern);
	public List<ResultPattern> listResultPatterns();
	public ResultPattern getResultPatternById(long resultPatternId);
	public void removeResultPattern(long resultPatternId);
}
