package com.sreepapers.app.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sreepapers.app.web.model.MyImage;
import com.sreepapers.app.web.services.ImageService;

@Controller
public class MainStreamController {

	private ImageService imageService;
	
	@Autowired(required=true)
	@Qualifier(value="imageService")
	public void setImageService(ImageService imageService)
	{
		this.imageService = imageService;
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String getIndex(Model model)
	{
		model.addAttribute("imageList", this.imageService.listImages());
		return "index";
	}
	
	@RequestMapping(value="index/imageDisplay",method = RequestMethod.GET)
	public void showImage(@RequestParam("img_id") Integer img_id,HttpServletResponse response,HttpServletRequest request) {
		try{
			MyImage image= this.imageService.getImageById(img_id);
			System.out.println("");
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(image.getImageData());
			response.getOutputStream().close();	
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	@RequestMapping(value="/layout", method = RequestMethod.GET)
	public String getLayout(Model model)
	{
		return "homePage";
	}
	
}
