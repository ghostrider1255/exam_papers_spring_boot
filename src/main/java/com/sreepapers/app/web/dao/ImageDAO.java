package com.sreepapers.app.web.dao;

import java.util.List;

import com.sreepapers.app.web.model.MyImage;

public interface ImageDAO {

	public void addImage(String imagePath);
	
	public List<MyImage> listImages();
	
	public MyImage getImageById(long imageId);
}
