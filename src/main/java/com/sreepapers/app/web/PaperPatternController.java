package com.sreepapers.app.web;

import java.util.List;

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

import com.sreepapers.app.web.model.PaperPattern;
import com.sreepapers.app.web.model.PatternSubjectRecord;
import com.sreepapers.app.web.model.Subject;
import com.sreepapers.app.web.services.PaperPatternService;
import com.sreepapers.app.web.services.PatternSubjectRecordService;
import com.sreepapers.app.web.services.SubjectService;

@Controller
public class PaperPatternController {

	
	private PaperPatternService paperPatternService;
	private SubjectService subjectService;
	private PatternSubjectRecordService patternSubjectRecordService;
	
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
	
	@Autowired(required=true)
	@Qualifier(value="subjectService")
	public void setSubjectService(SubjectService subjectService)
	{
		this.subjectService = subjectService;
	}
	
	@InitBinder("paperPattern")
	public void subjectDropDownBinding(WebDataBinder binder){
		binder.registerCustomEditor(List.class,"subjectRules", new CustomCollectionEditor(List.class){
			@Override
			protected Object convertElement(Object element){
				PatternSubjectRecord pSubjectRecord=null;
				
				if (element!=null && element instanceof Long){
					pSubjectRecord = new PatternSubjectRecord();
					pSubjectRecord.setpSubject(subjectService.getSubjectById((Long)element));
					return pSubjectRecord;
				}
				else if (element!=null && element instanceof String){
					pSubjectRecord = new PatternSubjectRecord();
					pSubjectRecord.setpSubject(subjectService.getSubjectById(Long.parseLong((String)element)));
					return pSubjectRecord;
				}
				return null;
			}
		});
	}
	
	@RequestMapping(value="/pattern/createPattern", method = RequestMethod.GET)
	public String listQuestions(Model model)
	{
		model.addAttribute("paperPattern",new PaperPattern());
		model.addAttribute("listSubjects", this.subjectService.listSubjects());
		model.addAttribute("listPaperPattern", this.paperPatternService.listPaperPatterns());
		return "pattern/createPattern";
	}
	
	@RequestMapping(value= "/patterns/initPattern", method = RequestMethod.POST)
	public String initPattern(@ModelAttribute("paperPattern") PaperPattern paperPattern,BindingResult result,Model model){
			
		if(paperPattern!=null && paperPattern.getPaperPatternId() == 0){
			model.addAttribute("paperPatternInit",paperPattern);
		}
		return "pattern/updatePaperPattern";
	}
	
	
	/*@InitBinder("subjectId")
	public void numberOfQuestionsBinding(WebDataBinder binder){
		binder.registerCustomEditor(Subject.class,new SubjectEditor());
	}*/
	
	
	@RequestMapping(value= "/patterns/addPaperPattern", method = RequestMethod.POST)
	public String addPaperPattern(@ModelAttribute("paperPatternInit") PaperPattern paperPattern,BindingResult result,Model model){
			
		if(paperPattern!=null && paperPattern.getPaperPatternId() == 0){
			List<PatternSubjectRecord> subjectRecordRules = paperPattern.getSubjectRules();
			paperPattern.setSubjectRules(null);
			if(subjectRecordRules!=null && subjectRecordRules.size()>0)
			{
				for (PatternSubjectRecord patternSubjectRecord : subjectRecordRules) {
					patternSubjectRecord.setPaperPattern(paperPattern);
					long subjectId = patternSubjectRecord.getpSubject().getSubjectId();
					Subject subject = this.subjectService.getSubjectById(subjectId);
					patternSubjectRecord.setpSubject(subject);
					patternSubjectRecord.setPaperPattern(paperPattern);
					this.patternSubjectRecordService.addPatternSubjectRecord(patternSubjectRecord);
				}
			}
			this.paperPatternService.addPaperPattern(paperPattern);
		}
		
		return "redirect:/pattern/createPattern";
	}
}
