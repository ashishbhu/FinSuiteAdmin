package com.springirafinanceapp.dao;

import java.util.HashMap;
import java.util.List;

import com.springirafinanceapp.entity.InventoryData;
import com.springirafinanceapp.entity.Item;
import com.springirafinanceapp.entity.SubUsers;
import com.springirafinanceapp.entity.Users;

public interface Dao 
{

	public String checkUserExistance(String userid);
	
	public String saveExcelData(List<Item> item, String userid);
	
	public String loginAdmin(HashMap<String,String> map);
	
	public  List<Users> getAllUsers();
	
	public String doUserActive(String userid);
	
	public String doUserSuspend(String userid);
	
	public String saveEditedUser(HashMap<String,String> map);
	
	public  List<SubUsers> getAllSubUsers(String userid);
	
	public String doSubUserActive(String userid);
	
	public String doSubUserSuspend(String userid);
	
	
	public String saveInventoryExcelData(List<InventoryData> item, String userid);
	
	
}
