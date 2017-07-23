package com.sreepapers.app.web.dao.modelrepository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sreepapers.app.web.model.MyImage;

@Transactional
public interface ImageRepository extends CrudRepository<MyImage, Serializable>{

	public MyImage getImageByImageId(long imageId);
	
}
