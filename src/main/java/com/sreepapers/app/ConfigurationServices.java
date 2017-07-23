package com.sreepapers.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sreepapers.app.web.dao.ExamDAO;
import com.sreepapers.app.web.dao.ExamQuestionDAO;
import com.sreepapers.app.web.dao.ImageDAO;
import com.sreepapers.app.web.dao.PaperPatternDAO;
import com.sreepapers.app.web.dao.PatternSubjectRecordDAO;
import com.sreepapers.app.web.dao.PersonDAO;
import com.sreepapers.app.web.dao.QuestionDAO;
import com.sreepapers.app.web.dao.ResultPatternDAO;
import com.sreepapers.app.web.dao.SubjectDAO;
import com.sreepapers.app.web.dao.UserDAO;
import com.sreepapers.app.web.dao.impl.ExamDAOImpl;
import com.sreepapers.app.web.dao.impl.ExamQuestionDAOImpl;
import com.sreepapers.app.web.dao.impl.ImageDaoImpl;
import com.sreepapers.app.web.dao.impl.PaperPatternDAOImpl;
import com.sreepapers.app.web.dao.impl.PatternSubjectRecordDAOImpl;
import com.sreepapers.app.web.dao.impl.PersonDAOImpl;
import com.sreepapers.app.web.dao.impl.QuestionDAOImpl;
import com.sreepapers.app.web.dao.impl.ResultPatternDAOImpl;
import com.sreepapers.app.web.dao.impl.SubjectDAOImpl;
import com.sreepapers.app.web.dao.impl.UserDAOImpl;
import com.sreepapers.app.web.services.ExamQuestionService;
import com.sreepapers.app.web.services.ExamService;
import com.sreepapers.app.web.services.ImageService;
import com.sreepapers.app.web.services.PaperPatternService;
import com.sreepapers.app.web.services.PatternSubjectRecordService;
import com.sreepapers.app.web.services.PersonService;
import com.sreepapers.app.web.services.QuestionService;
import com.sreepapers.app.web.services.ResultPatternService;
import com.sreepapers.app.web.services.SubjectService;
import com.sreepapers.app.web.services.UserService;
import com.sreepapers.app.web.services.impl.ExamQuestionServiceImpl;
import com.sreepapers.app.web.services.impl.ExamServiceImpl;
import com.sreepapers.app.web.services.impl.ImageServiceImpl;
import com.sreepapers.app.web.services.impl.PaperPatternServiceImpl;
import com.sreepapers.app.web.services.impl.PatternSubjectRecordServiceImpl;
import com.sreepapers.app.web.services.impl.PersonServiceImpl;
import com.sreepapers.app.web.services.impl.QuestionServiceImpl;
import com.sreepapers.app.web.services.impl.ResultPatternServiceImpl;
import com.sreepapers.app.web.services.impl.SubjectServiceImpl;
import com.sreepapers.app.web.services.impl.UserServiceImpl;

@Configuration
public class ConfigurationServices extends WebMvcConfigurerAdapter{
	
	@Bean(name="imageService")
	ImageService imageService(){
		return new ImageServiceImpl();
	}
	
	@Bean(name="examQuestionService")
	ExamQuestionService examQuestionService(){
		return new ExamQuestionServiceImpl();
	}
	
	@Bean(name="examService")
	ExamService examService(){
		return new ExamServiceImpl();
	}
	
	@Bean(name="paperPatternService")
	PaperPatternService paperPatternService(){
		
		return new PaperPatternServiceImpl();
	}
	
	@Bean(name="patternSubjectRecordService")
	PatternSubjectRecordService patternSubjectRecordService(){
		
		return new PatternSubjectRecordServiceImpl();
	}
	
	@Bean(name="personService")
	PersonService personService(){
		
		return new PersonServiceImpl();
	}

	@Bean(name="questionService")
	QuestionService questionService(){
		return new QuestionServiceImpl();
	}
	
	@Bean(name="resultPatternService")
	ResultPatternService resultPatternService(){
		return new ResultPatternServiceImpl();
	}
	
	@Bean(name="subjectService")
	SubjectService subjectService(){
		return new SubjectServiceImpl();
	}
	
	@Bean(name="userService")
	UserService userService(){
		return new UserServiceImpl();
	}
	
	
	
	//DAO
	@Bean(name="imageDAO")
	ImageDAO imageDAO(){
		return new ImageDaoImpl();
	}
	
	@Bean(name="examQuestionDAO")
	ExamQuestionDAO examQuestionDAO(){
		return new ExamQuestionDAOImpl();
	}
	
	@Bean(name="examDAO")
	ExamDAO examDAO(){
		return new ExamDAOImpl();
	}
	
	@Bean(name="paperPatternDAO")
	PaperPatternDAO paperPatternDAO(){
		
		return new PaperPatternDAOImpl();
	}
	
	@Bean(name="patternSubjectRecordDAO")
	PatternSubjectRecordDAO patternSubjectRecordDAO(){
		
		return new PatternSubjectRecordDAOImpl();
	}
	
	@Bean(name="personDAO")
	PersonDAO personDAO(){
		
		return new PersonDAOImpl();
	}

	@Bean(name="questionDAO")
	QuestionDAO questionDAO(){
		return new QuestionDAOImpl();
	}
	
	@Bean(name="resultPatternDAO")
	ResultPatternDAO resultPatternDAO(){
		return new ResultPatternDAOImpl();
	}
	
	@Bean(name="subjectDAO")
	SubjectDAO subjectDAO(){
		return new SubjectDAOImpl();
	}
	
	@Bean(name="userDAO")
	UserDAO userDAO(){
		return new UserDAOImpl();
	}
	
	
	/*@Bean(name="dataSource")
	public DriverManagerDataSource dataSource(){
		
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3307/rajuDB");
		driverManagerDataSource.setUsername("raju");
		driverManagerDataSource.setPassword("raju");
		
		return driverManagerDataSource;
	}*/

}
