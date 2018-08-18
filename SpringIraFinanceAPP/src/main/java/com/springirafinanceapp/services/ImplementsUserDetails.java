package com.springirafinanceapp.services;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.springirafinanceapp.dao.Dao;
import com.springirafinanceapp.dao.DaoImplementation;
import com.springirafinanceapp.entity.SubUsers;
import com.springirafinanceapp.entity.Users;

public class ImplementsUserDetails implements UserDetails {

	@Override
	public List<Users> getUsers() 
	{
		Dao dao = new  DaoImplementation();
		
		return dao.getAllUsers();
	}


//4.=====================================Do Active User ==================================================================//	
	
	@Override
	public String doUserActive(String userid) 
	{
		Dao dao = new  DaoImplementation();
		return dao.doUserActive(userid);
	}

//5.=====================================Do Suspend User ==================================================================//	
	
	@Override
	public String doUserSuspend(String userid) 
	{
		Dao dao = new  DaoImplementation();
		return dao.doUserSuspend(userid);
	}


	
//6.=====================================save detail of edited user======================================================//
	
	

	@Override
	public String saveEditedUser(String detail) 
	{
		Dao dao = new  DaoImplementation();
		
		JSONObject res = new JSONObject();
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		try
		{
			JSONObject obj = new JSONObject(detail);
			
			map.put("id", obj.getString("userid"));
			map.put("mobile", obj.getString("mobile"));
			map.put("email", obj.getString("email"));
			map.put("address", obj.getString("address"));
			map.put("shopname", obj.getString("shopname"));
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			try 
			{
				res.put("StatusCode", 401);
				res.put("Message", e);
				return res.toString();
			} 
			catch (Exception e2)
			{
				System.out.println(e2);
			}
			
		}
		
		return dao.saveEditedUser(map);
	}

	
//7.====================================Get All Sub User ====================================================//
	
	@Override
	public List<SubUsers> getAllSubUsers(String userid)
	{
		Dao dao = new  DaoImplementation();
		
		return dao.getAllSubUsers(userid);
	}


//8.====================================Do Active Sub User ====================================================//	
	@Override
	public String doSubUserActive(String userid) {
		
		Dao dao = new  DaoImplementation();
		return dao.doSubUserActive(userid);
	}


//9.=====================================Do suspend Sub User ====================================================//
	
	@Override
	public String doSubUserSuspend(String userid) 
	{
		Dao dao = new  DaoImplementation();
		return dao.doSubUserSuspend(userid);
	}
	

}
