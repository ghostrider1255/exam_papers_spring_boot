package com.sreepapers.app.web.services;

import java.util.List;

import com.sreepapers.app.web.model.MyImage;


public interface ImageService 
{
	public void addImage(String imagePath);
	
	public List<MyImage> listImages();
	
	public MyImage getImageById(int imageId);
}
