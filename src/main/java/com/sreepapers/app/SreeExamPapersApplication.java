package com.sreepapers.app;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SreeExamPapersApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(SreeExamPapersApplication.class);
	}
	public static void main(String[] args) {
		new SpringApplicationBuilder(SreeExamPapersApplication.class)
		//.bannerMode(Mode.CONSOLE)
		.build().run(args);
	}
}
