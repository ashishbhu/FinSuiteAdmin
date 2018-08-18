package com.springirafinanceapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springirafinanceapp.entity.SubUsers;
import com.springirafinanceapp.entity.Users;
import com.springirafinanceapp.services.Admin;
import com.springirafinanceapp.services.Excel;
import com.springirafinanceapp.services.ImplementsAdmin;
import com.springirafinanceapp.services.ImplementsExcel;
import com.springirafinanceapp.services.ImplementsUserDetails;
import com.springirafinanceapp.services.UserDetails;

@RestController
public class Controller {

	//1.==================================Upload Excel file of Item==============================================================//

	  @RequestMapping(method=RequestMethod.POST,value="/upload-excel/{userid}") 
	  public String excel(@RequestParam("file") MultipartFile uploading, @PathVariable("userid") String userid)
	  {		
		  Excel excel = new ImplementsExcel();
		  return excel.svaeExcel(uploading, userid);
	  }


	  //2.=====================================Login for Admin================================================================//
	  
	  @RequestMapping(method=RequestMethod.POST,value="/admin-login") 
	  public String excel(@RequestBody String detail)
	  {		
		 Admin admin = new ImplementsAdmin();
		  return admin.loginAdmin(detail);
	  }
	  
	 //3.====================================Get All Main User ====================================================//
	  
	  @RequestMapping(method=RequestMethod.GET,value="/users") 
	  public  List<Users> getAlUsers()
	  {		
		UserDetails userDetails = new ImplementsUserDetails();
		  return userDetails.getUsers();
	  }
	  
	//4.=====================================Do Active User ==================================================================//
	  
	  @RequestMapping(method=RequestMethod.GET,value="/active-user/{userid}") 
	  public  String doUserActive(@PathVariable("userid") String userid)
	  {		
		UserDetails userDetails = new ImplementsUserDetails();
		  return userDetails.doUserActive(userid);
	  }
	  
   //5.=====================================Do suspend User ==================================================================//
	  
	  @RequestMapping(method=RequestMethod.GET,value="/suspend-user/{userid}") 
	  public  String doUserSuspend(@PathVariable("userid") String userid)
	  {		
		UserDetails userDetails = new ImplementsUserDetails();
		  return userDetails.doUserSuspend(userid);
	  }  
	  
	 
  //6.=====================================save detail of edited user======================================================//
	  
	  @RequestMapping(method=RequestMethod.POST,value="/save-edited-user") 
	  public String saveEditedUser(@RequestBody String detail)
	  {		
		  UserDetails userDetails = new ImplementsUserDetails();
		  return userDetails.saveEditedUser(detail);
	  }
	  
	  
 //7.====================================Get All Sub User ====================================================//
	  
	  @RequestMapping(method=RequestMethod.GET,value="/sub-user/{userid}") 
	  public  List<SubUsers> getAllSubUsers(@PathVariable("userid") String userid)
	  {		
		UserDetails userDetails = new ImplementsUserDetails();
		  return userDetails.getAllSubUsers(userid);
	  }	  
	  
	
//8.=====================================Do Active Sub User ==================================================================//
	  
	  @RequestMapping(method=RequestMethod.GET,value="/active-subuser/{userid}") 
	  public  String doSubUserActive(@PathVariable("userid") String userid)
	  {		
		UserDetails userDetails = new ImplementsUserDetails();
		  return userDetails.doSubUserActive(userid);
	  }
	
 //9.=====================================Do suspend Sub User ==================================================================//
	  
	  @RequestMapping(method=RequestMethod.GET,value="/suspend-subuser/{userid}") 
	  public  String doSubUserSuspend(@PathVariable("userid") String userid)
	  {		
		UserDetails userDetails = new ImplementsUserDetails();
		  return userDetails.doSubUserSuspend(userid);
	  }  
	  	  
	 
	  
 //10.=================================Upload Inventory Data Excel============================================================//
	  
	  @RequestMapping(method=RequestMethod.POST,value="/upload-inventory-excel/{userid}") 
	  public String uploadInventory(@RequestParam("file") MultipartFile uploading, @PathVariable("userid") String userid)
	  {		
		  Excel excel = new ImplementsExcel();
		  return excel.uploadInventoryExcel(uploading, userid);
	  }
	  
	  
}






