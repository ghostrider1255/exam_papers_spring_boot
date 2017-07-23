package com.sreepapers.app.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sreepapers.app.web.dao.SubjectDAO;
import com.sreepapers.app.web.model.Subject;
import com.sreepapers.app.web.services.SubjectService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectDAO subjectDAO;
	
	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}
	
	@Override
	@Transactional
	public void addSubject(Subject subject) {
		this.subjectDAO.addSubject(subject);
		
	}

	@Override
	@Transactional
	public void updateSubject(Subject subject) {
		this.subjectDAO.updateSubject(subject);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Subject> listSubjects() {
		return this.subjectDAO.listSubjects();
	}

	@Override
	@Transactional
	public Subject getSubjectById(long id) {
		return this.subjectDAO.getSubjectById(id);
	}

	@Override
	@Transactional
	public void removeSubject(long id) {
		this.subjectDAO.removeSubject(id);
		
	}

}
