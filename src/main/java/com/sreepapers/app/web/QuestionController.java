package com.sreepapers.app.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sreepapers.app.web.model.Question;
import com.sreepapers.app.web.model.Subject;
import com.sreepapers.app.web.services.QuestionService;
import com.sreepapers.app.web.services.SubjectService;

@Controller
public class QuestionController {

	
	private QuestionService questionService;
	private SubjectService subjectService;
	
	@Autowired(required=true)
	@Qualifier(value="questionService")
	public void setQuestionService(QuestionService questionService)
	{
		this.questionService = questionService;
	}
	@Autowired(required=true)
	@Qualifier(value="subjectService")
	public void setSubjectService(SubjectService subjectService)
	{
		this.subjectService = subjectService;
	}
	
	@InitBinder("question")
	public void dataBinding(WebDataBinder binder){
		binder.registerCustomEditor(Set.class,"subjects", new CustomCollectionEditor(Set.class){
			@Override
			protected Object convertElement(Object element){
				if (element!=null && element instanceof Long){
					return subjectService.getSubjectById((Long)element);
				}
				else if (element!=null && element instanceof String){
					return subjectService.getSubjectById(Long.parseLong((String)element));
				}
				return null;
			}
		});
	}
	
	@RequestMapping(value="/questions", method = RequestMethod.GET)
	public String listQuestions(Model model)
	{
		model.addAttribute("question",new Question());
		model.addAttribute("listSubjects", this.subjectService.listSubjects());
		return "questions";
	}
	
	//For add and update Question both
	@RequestMapping(value= "/questions/addQuestion", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute("question") Question question,BindingResult result,Model model){
			
		if(question.getQuestionId() == 0){
			//new Subject, add it
			this.questionService.addQuestion(question);
		}else{
			//existing subject, call update
			this.questionService.updateQuestion(question);
		}
		return "redirect:/questions";
			
	}
		
	@RequestMapping("/removeQuestion/{questionId}")
	public String removeQuestion(@PathVariable("questionId") long questionId){
			
		this.questionService.removeQuestion(questionId);
	    return "redirect:/questions";
	 }
	 
	 @RequestMapping("/editQuestion/{questionId}")
	 public String editQuestion(@PathVariable("questionId") long questionId, Model model){
		 model.addAttribute("question", this.questionService.getQuestionById(questionId));
		 model.addAttribute("listQuestions", this.questionService.listQuestions());
	     model.addAttribute("listSubjects", this.subjectService.listSubjects());
	     return "questions";
	 }
}
