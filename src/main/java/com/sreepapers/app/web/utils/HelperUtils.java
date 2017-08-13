package com.sreepapers.app.web.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HelperUtils {
	
	private HelperUtils(){}
	
	public static <T> HttpEntity<T> prepareRequestEntity(T entity){
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		if(entity!=null){
			return new HttpEntity<T>(entity,header);	
		}
		else{
			return new HttpEntity<T>(header);
		}
	}
}
