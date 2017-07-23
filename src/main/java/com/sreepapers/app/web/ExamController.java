package com.sreepapers.app.web;

import java.beans.PropertyEditorSupport;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sreepapers.app.web.model.Exam;
import com.sreepapers.app.web.model.ExamQuestion;
import com.sreepapers.app.web.model.PaperPattern;
import com.sreepapers.app.web.model.PatternSubjectRecord;
import com.sreepapers.app.web.model.Question;
import com.sreepapers.app.web.model.Subject;
import com.sreepapers.app.web.services.ExamQuestionService;
import com.sreepapers.app.web.services.ExamService;
import com.sreepapers.app.web.services.PaperPatternService;
import com.sreepapers.app.web.services.PatternSubjectRecordService;
import com.sreepapers.app.web.services.QuestionService;
import com.sreepapers.app.web.services.SubjectService;

@Controller
public class ExamController {
	
	private PaperPatternService paperPatternService;
	private PatternSubjectRecordService patternSubjectRecordService;
	private QuestionService questionService;
	private ExamService examService;
	private ExamQuestionService examQuestionService;
	
	@Autowired(required = true)
	@Qualifier(value="examQuestionService")
	public void setExamQuestionService(ExamQuestionService examQuestionService){
		this.examQuestionService = examQuestionService;
	}
	
	@Autowired(required = true)
	@Qualifier(value="examService")
	public void setExamService(ExamService examService){
		this.examService = examService;
	}
	
	@Autowired(required=true)
	@Qualifier(value="questionService")
	public void setQuestionService(QuestionService questionService)
	{
		this.questionService = questionService;
	}
	
	@Autowired(required=true)
	@Qualifier(value="paperPatternService")
	public void setPaperPatternService(PaperPatternService paperPatternService)
	{
		this.paperPatternService = paperPatternService;
	}
	
	@Autowired(required=true)
	@Qualifier(value="patternSubjectRecordService")
	public void setPatternSubjectRecordService(PatternSubjectRecordService patternSubjectRecordService)
	{
		this.patternSubjectRecordService = patternSubjectRecordService;
	}
	
	@RequestMapping(value="/exam/createExamPattern", method = RequestMethod.GET)
	public String listPatterns(Model model)
	{
		model.addAttribute("examPattern",new Exam());
		model.addAttribute("listPaperPattern", this.paperPatternService.listPaperPatterns());
		return "exam/createExamPattern";
	}
	
	@RequestMapping(value= "/exam/selectExamQuestions", method = RequestMethod.POST)
	public String initPattern(@ModelAttribute("examPattern") Exam exam,BindingResult result,Model model){
			
		if(exam!=null && exam.getExamId() == 0){
			PaperPattern paperPattern = this.paperPatternService.getPaperPatternById(exam.getPaperPattern().getPaperPatternId());
			exam.setPaperPattern(paperPattern);
			Map<String,ExamQuestion> questionsBySubject = this.paperPatternService.getQuestionByPatternId(exam.getPaperPattern().getPaperPatternId());
			exam.setExamQuestions(questionsBySubject);
			this.examService.addExam(exam);
			/*Iterator iterator = questionsBySubject.keySet().iterator();
			while(iterator.hasNext()){
				String key = (String) iterator.next();
				ExamQuestion examQuestion = questionsBySubject.get(key);
				examQuestion.setExam(exam);
				this.examQuestionService.addExamQuestion(examQuestion);
			}*/
			/*exam.setExamQuestions(questionsBySubject);
			this.examService.addExam(exam);*/
			//model.addAttribute("examPatternInit",exam);
			
			//model.addAttribute("examQuestions",this.paperPatternService.getQuestionByPatternId(exam.getPaperPattern().getPaperPatternId()));
		}
		return "redirect:/exam/createExamPattern";
	}
	
	
	@InitBinder
	public void paperPatternBinding(WebDataBinder binder){
		binder.registerCustomEditor(PaperPattern.class,new PropertyEditorSupport(){
			@Override
			public void setAsText(String paperPatternId){
				PaperPattern paperPattern= new PaperPattern();
				paperPattern.setPaperPatternId(Long.parseLong((String)paperPatternId));
				this.setValue(paperPattern);
			}
		});
	}
			
	@InitBinder("questionsMap")
	public void dataBinding(WebDataBinder binder){
		binder.registerCustomEditor(List.class,"examQuestions", new CustomCollectionEditor(List.class){
			@Override
			protected Object convertElement(Object element){
				if (element!=null && element instanceof String){
					System.out.println((String)element);
					return null;
				}
				return null;
			}
		});
	}
	
	@RequestMapping(value= "/exam/addExam", method = RequestMethod.POST)
	public String addPaperPattern(@ModelAttribute("examPatternInit") Exam examPatternInit,BindingResult result,Model model){
			
		if(examPatternInit!=null && examPatternInit.getExamId() == 0){
			Map<String,ExamQuestion> examQuestions = examPatternInit.getExamQuestions();
			PaperPattern paperPattern = this.paperPatternService.getPaperPatternById(examPatternInit.getPaperPattern().getPaperPatternId());
			examPatternInit.setPaperPattern(paperPattern);
			if(examQuestions!=null && examQuestions.size()>0)
			{
				Iterator iterator = examQuestions.keySet().iterator();
				while(iterator.hasNext()){
					String key = (String) iterator.next();
					ExamQuestion eQuestion = (ExamQuestion) examQuestions.get(key);
					for(Question eachQuestion: eQuestion.getQuestions())
					{
						System.out.println(eachQuestion.getQuestionText());
					}
				}
			}
		}
		
		return "redirect:/exam/createExamPattern";
	}
}
