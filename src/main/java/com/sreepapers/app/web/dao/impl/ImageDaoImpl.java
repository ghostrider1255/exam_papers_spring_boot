package com.sreepapers.app.web.dao.impl;

import static com.sreepapers.app.web.utils.properties.HelperProperties.POSTFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.PREFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.REST_SERVER_HOST_URL;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.sreepapers.app.web.dao.ImageDAO;
import com.sreepapers.app.web.model.MyImage;
import com.sreepapers.app.web.utils.HelperUtils;

@Repository
public class ImageDaoImpl implements ImageDAO{

	private static final Logger logger = LoggerFactory.getLogger(ImageDaoImpl.class);

	public static final String SAVE_IMAGE = "/imageAction/saveImage";
	public static final String GET_IMAGES="/imageAction/images";
	public static final String DELETE_IMAGE="/imageAction/deleteImage/";
	public static final String GET_IMAGE="/imageAction/image/";
	public static final String UPDATE_IMAGE="/imageAction/updateImage";
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void addImage(String imagePath) {
		
		String url = webServerRestUrl+SAVE_IMAGE;
		
		MyImage image = new MyImage();
		byte[] bFile ;
		File imageFile = new File(imagePath);
		bFile = new byte[(int) imageFile.length()];
		
		try(FileInputStream inputStream = new FileInputStream(imageFile)){
			
			inputStream.read(bFile);
			inputStream.close();
		}
		catch(Exception imageException){
			logger.error(imageException.getMessage());
		}
		image.setImageTitle(imagePath);
		image.setImageData(bFile);
		
		HttpEntity<MyImage> requestEntity = HelperUtils.prepareRequestEntity(image);
		
		ResponseEntity<MyImage> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,MyImage.class);
		MyImage newImage = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Image saved successfully, Image Details={}",newImage);
		}else{
			logger.info("Image saved successfully");
		}
	}

	@Override
	public List<MyImage> listImages() {
		
		String url = webServerRestUrl+GET_IMAGES;
		HttpEntity<String> requestEntity = HelperUtils.prepareRequestEntity(null);
		ResponseEntity<MyImage[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,MyImage[].class);
		MyImage[] imageList = responseEntity.getBody();
			
		return Arrays.asList(imageList);
	}
	
	public MyImage getImageById(long imageId) {
		
		String url = webServerRestUrl+GET_IMAGE+imageId;
		HttpEntity<String> requestEntity = HelperUtils.prepareRequestEntity(null);
		ResponseEntity<MyImage> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,MyImage.class);
		
		return responseEntity.getBody();
	}

}
