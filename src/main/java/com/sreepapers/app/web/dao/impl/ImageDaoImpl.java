package com.sreepapers.app.web.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sreepapers.app.web.dao.ImageDAO;
import com.sreepapers.app.web.dao.jpa.repository.ImageRepository;
import com.sreepapers.app.web.model.MyImage;
import com.sreepapers.app.web.model.PaperPattern;

@Repository
public class ImageDaoImpl implements ImageDAO{

	private static final Logger logger = LoggerFactory.getLogger(ImageDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ImageRepository imageRepository;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addImage(String imagePath) {
		Session session = this.sessionFactory.getCurrentSession();
		byte[] bFile ;
		File imageFile = new File(imagePath);
		bFile = new byte[(int) imageFile.length()];
		
		try(FileInputStream inputStream = new FileInputStream(imageFile)){
			
			inputStream.read(bFile);
			inputStream.close();
		}
		catch(Exception imageException){
			imageException.printStackTrace();
		}
		session.persist(imagePath);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyImage> listImages() {
		/*Session session = this.sessionFactory.getCurrentSession();
		List<MyImage> imageList = session.createQuery("from MyImage").list();
		return imageList;*/
		Iterable<MyImage> imageList = imageRepository.findAll();
		Iterator<MyImage> it = imageList.iterator();
		
		List<MyImage> finalImageList = new ArrayList<MyImage>();
		while(it.hasNext()){
			finalImageList.add(it.next());
		}
		
		return finalImageList;
	}
	
	public MyImage getImageById(long imageId) {
		/*Session session = this.sessionFactory.getCurrentSession();		
		MyImage myImage = (MyImage) session.load(MyImage.class, imageId);
		
		return myImage;*/
		
		return imageRepository.getImageByImageId(imageId);
	}

}
