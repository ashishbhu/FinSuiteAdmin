package com.springirafinanceapp.services;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.springirafinanceapp.dao.DaoImplementation;

@Service
public class ImplementsAdmin implements Admin {

	@Override
	public String loginAdmin(String detail) 
	{
		JSONObject res = new JSONObject();
		try
		{
			JSONObject obj = new JSONObject(detail);
			
			HashMap<String,String> map = new HashMap<String,String>();
			
			map.put("userid", obj.getString("userid"));
			map.put("password", obj.getString("password"));
			
			
			System.out.println(map.get("userid"));
			DaoImplementation daoimp = new DaoImplementation();
			
			String message=daoimp.loginAdmin(map);
			
			if(message == "notexist")
			{
				res.put("StatusCode", 401);
				res.put("Message", "notexist");
			}
			else
			{
				System.out.println("message: "+message);
				return message;
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			try 
			{
				res.put("StatusCode", 400);
				res.put("Message", e);
				return e.toString();
			} 
			catch (Exception e2) {
				System.out.println(e2);
			}
		}
		return res.toString();
	}

}
