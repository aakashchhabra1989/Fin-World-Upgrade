package com.achhabra.finworld.services;

import com.achhabra.finworld.beans.EntityBean;
import com.achhabra.finworld.model.User;

public interface UserService {
	
	public User getUser(String login);
	
	public long registerUser(EntityBean entityBean);
	
	public boolean isEmailRegistered(String emailId);
	
	public EntityBean getUserDetailByRegisteredEmail(String emailId);

}
