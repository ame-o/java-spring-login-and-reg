package com.americao.user.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.americao.user.models.LoginUser;
import com.americao.user.models.User;
import com.americao.user.services.UserService;

@Controller
public class UserController {
	    // Add once service is implemented:
	    @Autowired
	    private UserService userService;
	    	
//	    --- RENDER LOGIN/ REGISTRATION ---
	    @GetMapping("/")
	    public String index(Model model) {
	    
//	        // Bind empty User and LoginUser objects to the JSP
//	        // to capture the form input
	        model.addAttribute("newUser", new User());
	        model.addAttribute("newLogin", new LoginUser());
	        return "user/index.jsp";
	    }
//	  	---PROCESS REGISTRATION---  
	    @PostMapping("/registration")
	    public String register(@Valid @ModelAttribute("newUser") User newUser, 
	            BindingResult result, Model model, HttpSession session) {
	        
	        // call a register method in the service 
	    	userService.register(newUser, result);
	        //some extra validations and create a new user!
	        
	        if(result.hasErrors()) {
	            // Be sure to send in the empty LoginUser before 
	            // re-rendering the page.
	            model.addAttribute("newLogin", new LoginUser());
	            return "user/index.jsp";
	        } else {
	        // No errors! 
	        // TO-DO Later: Store their ID from the DB in session, 
	        // in other words, log them in.
	        session.setAttribute("user_id", newUser.getId());
	        return "redirect:/home";
	        }
	    }
//		---PROCESS LOGIN---	    
	    @PostMapping("/login")
	    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
	            BindingResult result, Model model, HttpSession session) {
	        
	        // Add once service is implemented:
	    	User user = userService.login(newLogin, result);
	    
	        if(result.hasErrors()) {
	            model.addAttribute("newUser", new User());
	            return "user/index.jsp";
	        }else {	// No errors! 
	        	// TO-DO Later: Store their ID from the DB in session, 
	        	// in other words, log them in.
	        	session.setAttribute("user_id", user.getId());
	        	return "redirect:/home";
	        }
	    
	    }
	   
//		--- HOME ROUTE ---    
	    @RequestMapping("/home")
	    public String home(HttpSession session, Model model) {
//		retrieve the user from session
		Long userId = (Long) session.getAttribute("user_id");
//		check if userId is null
		if (userId == null) {
			return "redirect:/";
		} else {
//			go to the db to retrieve the user using the id from session
			User thisUser = userService.findOne(userId);
			model.addAttribute("thisUser", thisUser);			
//			model.addAttribute("thisUser", thisUser.getUserName());			
			return "user/home.jsp";
		}
	}

		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
}
    
