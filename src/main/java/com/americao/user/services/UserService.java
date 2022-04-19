package com.americao.user.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.americao.user.models.LoginUser;
import com.americao.user.models.User;
import com.americao.user.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepo;
    
    public User register(User newUser, BindingResult result) {
//    	Reject if the email is taken (present in the database)
    	Optional<User> existingUser =  userRepo.findByEmail(newUser.getEmail());
    	if (existingUser.isPresent()) {
    		result.rejectValue("email", "registerErrors", "This email is taken");
    	}
//    	Reject if passwords don't match with confirm
    	if (!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("confirm", "registerErrors", "this confirmation password must match the password");
    	}
    	
//    	Return null if result has errors
    	if (result.hasErrors()) {
    		return null;
    	} else {
//    		Hash and set password, save user to the DB!
    		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    		newUser.setPassword(hashed);
//    		SAVE USER TO THE DB!!!!
    		return userRepo.save(newUser);
    	}
   
    }
    
    public User login(LoginUser newLoginObject, BindingResult result) {
//    	FIND THE USER IN THE DB
    	Optional<User> existingUser = userRepo.findByEmail(newLoginObject.getEmail());
    	if (!existingUser.isPresent()) {
    		result.rejectValue("email", "loginError", "email not found");
    	} else {
    		User user = existingUser.get();
//    	Reject if BCrypt pass match fails
    		if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
//    		Reject
    			result.rejectValue("password", "loginError", "invalid password");
    		}
//    	return null if result has errors
    		if (result.hasErrors()) {
    			return null;
    		} else {
//    		otherwise, return the user object
    			return user;
    		}
    	}	
    	return null;  	
    }
    
//    Find ONE
    public User findOne(Long id) {
    	Optional<User> existingUser = userRepo.findById(id);
    	if (existingUser.isPresent()) {
    		return existingUser.get();
    	} else {
    		return null;
    	}
    }
}
