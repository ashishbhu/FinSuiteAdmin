package com.springirafinanceapp.services;

import java.util.List;

import com.springirafinanceapp.entity.SubUsers;
import com.springirafinanceapp.entity.Users;

public interface UserDetails {
	
	public List<Users> getUsers();
	public String doUserActive(String userid);
	
	public String doUserSuspend(String userid);
	
	public String saveEditedUser(String detail);
	
	public List<SubUsers> getAllSubUsers(String userid);
	
	public String doSubUserActive(String userid);
	
	public String doSubUserSuspend(String userid);
      
}
