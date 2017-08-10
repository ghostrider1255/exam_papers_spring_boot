package com.sreepapers.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@Configuration
public class ConfigurationHibernate {
	
	@Bean(name="sessionFactory")
	public HibernateJpaSessionFactoryBean localSessionFactoryBean(){
		return new HibernateJpaSessionFactoryBean();
	}
}
