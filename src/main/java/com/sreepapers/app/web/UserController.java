package com.sreepapers.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sreepapers.app.web.model.User;
import com.sreepapers.app.web.services.UserService;

@Controller
public class UserController {

	
	private UserService userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public String listUsers(Model model)
	{
		model.addAttribute("user",new User());
		model.addAttribute("listUsers", this.userService.listUsers());
		return "user";
	}
	
	//For add and update person both
	@RequestMapping(value= "/user/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user){
		if(user.getUserId() == 0){
			//new user, add it
			this.userService.addUser(user);
		}else{
			//existing user, call update
			this.userService.updateUser(user);
		}
		return "redirect:/users";
			
	}

	@RequestMapping("/removeUser/{userId}")
	public String removeUser(@PathVariable("userId") int userId){
			
		this.userService.removeUser(userId);
	    return "redirect:/users";
	}
	 
    @RequestMapping("/editUser/{userId}")
    public String editUser(@PathVariable("userId") int userId, Model model){
        model.addAttribute("user", this.userService.getUserById(userId));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "user";
    }
}
