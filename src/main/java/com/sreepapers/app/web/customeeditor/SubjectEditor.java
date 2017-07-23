package com.sreepapers.app.web.customeeditor;

import java.beans.PropertyEditorSupport;

import com.sreepapers.app.web.services.SubjectService;

public class SubjectEditor extends PropertyEditorSupport{
	private SubjectService subjectService;
	
	public void setSubjectService(SubjectService subjectService){
		this.subjectService = subjectService;
	}
	
	@Override
	public void setAsText(String subjectId){
		this.setValue(this.subjectService.getSubjectById(Long.parseLong(subjectId)));
	}
}
