package com.springirafinanceapp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springirafinanceapp.entity.InventoryData;
import com.springirafinanceapp.entity.Item;
import com.springirafinanceapp.entity.SubUsers;
import com.springirafinanceapp.entity.Users;

public class DaoImplementation implements Dao {
	
	ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
	DbConnection db=(DbConnection) context.getBean("mydbconnection");
	
	// DbConnection db = new DbConnection();
	
	
//--------------------------------Check User is registered user and is active or not---------------------------//
	@Override
	public String checkUserExistance(String userid)
	{
		String checkUser = "select *from logincontrol where username='"+userid+"' and acctlocked='"+false+"'";
		Connection con=db.getConnection();
		try
		{
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(checkUser);
			
			if(rs.next() == false)
			{
				return "notexist";
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
		}
		
		
		return "exist";
	}

 
//-----------------------------------------Save Excel data in database ----------------------------------------//	
	@Override
	public String saveExcelData(List<Item> item, String userid) 
	{
		Connection con=db.getConnection();
		  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		    Date date1 = new Date();  
		    
		   String date = formatter.format(date1);
		
		//System.out.println("daoImplementation: "+date);
		
		//Get latest Item Id of a given user
		int count=0;
		String getItemId = "select count(itemId) from itemmain where subId='"+userid+"'";
	
		
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(getItemId);
			
			if(rs.next() == true)
			{
				
					count = rs.getInt(1);
			
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			return e.toString();
		}
		
		
			
			//System.out.println("count: "+count);
		
		
		
		
		String saveData="insert into itemmain(subId,itemId,itemName,itemPrice,measurement,itemCategory,gstcategory,bar_code, hsn_number, print_name, startDate,endDate,count,version) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try
		{
			
			for(int i=0; i<item.size(); i++)
			{
			
				String itemno = userid+"I"+(count+i+1);
				
				con.setAutoCommit(false); // start transaction
				
		 		PreparedStatement ps = con.prepareStatement(saveData);
				
				ps.setString(1, userid);
				ps.setString(2, itemno);
				ps.setString(3, item.get(i).getItemName());
				ps.setString(4, item.get(i).getItemPrice());
				ps.setString(5, item.get(i).getMeasurement());
				ps.setString(6, item.get(i).getItemCategory());
				ps.setString(7, item.get(i).getGstCategory());
				ps.setString(8, item.get(i).getBarCode());
				ps.setString(9, item.get(i).getHsnNumber());
				ps.setString(10, item.get(i).getPrintName());
				ps.setString(11, date);
				ps.setString(12, "2999-12-31");
				ps.setInt(13, 0);
				ps.setInt(14, 1);
				
				ps.executeUpdate();
				
				con.commit(); // end transaction
				
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			return e.toString();
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
		}
		
		return "success";
	}


	
//-------------------------------------------------Login Admin------------------------------------------------------//	
	@Override
	public String loginAdmin(HashMap<String, String> map) {
	
		Connection con=db.getConnection();
		
		JSONObject res = new JSONObject();
		
		try
		{
			String checkAdmin="select *from logincontrol where username='"+map.get("userid")+"' and pswd='"+map.get("password")+"'";
			
			Statement st =con.createStatement();
			ResultSet rs = st.executeQuery(checkAdmin);
			
			if(rs.next() == true)
			{
				res.put("StatusCode", 200);
				res.put("Message", "success");
				res.put("role", rs.getString(7));
			}
			else
			{
				return "notexist";
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
			
		}
		return res.toString();
	}

//3.================================================Get All Users=================================================//
	@Override
	public List<Users> getAllUsers() 
	{
		//System.out.println("Get All Users");
		
		//JSONObject res = new JSONObject();
		
		List<Users> userDetail = new ArrayList<Users>();
		
		Connection con=db.getConnection();
		
		String getUsers = "select *from registration";
		
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(getUsers);
			
			if(rs.next() == true)
			{
				do 
				{
					//System.out.println(rs.getString(1));
					Users user = new Users();
					
					user.setUserId(rs.getString(1));
					user.setFirstName(rs.getString(2));
					user.setShopname(rs.getString(4));
					user.setMobileNumber(rs.getString(6));
					user.setEmail(rs.getString(7));
					user.setAddress(rs.getString(5));
					user.setGstnumber(rs.getString(8));
					user.setStatus(rs.getString(9));
					
					//userDetail.set(0, user);
					userDetail.add(user);
					
					
				}
				while(rs.next());
				
				
			}
			else
			{
				
				return userDetail;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
			
		}
		
		
		
		return userDetail;
	}


//4.=====================================Do Active User ==================================================================//		
	
	@Override
	public String doUserActive(String userid) 
	{
		
		//System.out.println("douseractive: "+userid);
		JSONObject res = new JSONObject();
		
		Connection con=db.getConnection();
		
		String activeUser = "update registration set acctlocked='false' where subid='"+userid+"'";
		String activeLogin ="update logincontrol set acctlocked='false' where parentid='"+userid+"'";
		
		//active corresponding child user also
		
		String activeChild="update subuser set acctLocked='false' where subId='"+userid+"'";
		
		try
		{
			con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement(activeUser);
			
			PreparedStatement ps1 = con.prepareStatement(activeLogin);
			
			PreparedStatement psChild = con.prepareStatement(activeChild);
			
			ps.executeUpdate();
			ps1.executeUpdate();
			
			psChild.executeUpdate();
			
			con.commit();
			
			res.put("StatusCode", 200);
			res.put("Message", "success");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
			try
			{
				res.put("StatusCode", 401);
				res.put("Message", e);
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
			
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
			
		}
		
		
		
		return res.toString();
	}

//5.=====================================Do Suspend User ==================================================================//
	
	@Override
	public String doUserSuspend(String userid) 
	{
		JSONObject res = new JSONObject();
		
		Connection con=db.getConnection();
		
		String suspendUser = "update registration set acctlocked='true' where subid='"+userid+"'";
		String suspendLogin ="update logincontrol set acctlocked='true' where parentid='"+userid+"'";
		
		//suspend corresponding child also
		String suspendSubUser="update subuser set acctLocked='true' where subId='"+userid+"'";
		
		
		try
		{
			con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement(suspendUser);
			
			PreparedStatement ps1 = con.prepareStatement(suspendLogin);
			
			PreparedStatement psChild = con.prepareStatement(suspendSubUser);
			
			ps.executeUpdate();
			ps1.executeUpdate();
			
			psChild.executeUpdate();
			
			con.commit();
			
			res.put("StatusCode", 200);
			res.put("Message", "success");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
			try
			{
				res.put("StatusCode", 401);
				res.put("Message", e);
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
			
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
			
		}
		
		return res.toString();
	}


//6.=====================================save detail of edited user======================================================//	
	
	@Override
	public String saveEditedUser(HashMap<String, String> map) 
	{
		Connection con=db.getConnection();
		
		JSONObject res = new JSONObject();
		//System.out.println(map.get("id") +" "+map.get("mobile") +" email: "+map.get("email")+" "+map.get("address"));
		
		String saveDetails ="update registration set mobilenumber=?, emailid=?, address=?, shopname=? where subid='"+map.get("id")+"'";
		
		try
		{
			con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement(saveDetails);
			
			ps.setString(1, map.get("mobile"));
			ps.setString(2, map.get("email"));
			ps.setString(3, map.get("address"));
			ps.setString(4, map.get("shopname"));
			
			ps.executeUpdate();
			
			con.commit();
			
			res.put("StatusCode", 200);
			res.put("Message", "success");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
			
			try 
			{
				res.put("StatusCode", 402);
				res.put("Message", e);
				return res.toString();
				
			} 
			catch (JSONException e1) 
			{
				System.out.println(e1);
				
			}
		
			
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
			
		}
		
		return res.toString();
	}

	
//7.====================================Get All Sub User ====================================================//

	@Override
	public List<SubUsers> getAllSubUsers(String userid)
	{
		System.out.println("getAll Sub User");
		
		Connection con=db.getConnection();
		
		List<SubUsers> subusers = new ArrayList<SubUsers>();
		
	
		String getSubUser = "select subId, name, childUserName, acctLocked from subuser where subId='"+userid+"'";
		
		try
		{
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(getSubUser);
				
				if(rs.next() == true)
				{
					do
					{
						SubUsers user = new SubUsers();
						
						user.setParentId(rs.getString(1));
						user.setSubUserName(rs.getString(2));
		   				user.setSubUserId(rs.getString(3));
						user.setStatus(rs.getString(4));
						
						subusers.add(user);
						
						
					}while(rs.next());
					
				}
				else
				{
					return subusers;
					
				}
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
			
		}
		
		return subusers;
	}

//8.============================================Do Sub User Active========================================//
	
	@Override
	public String doSubUserActive(String userid)
	{
	
		JSONObject res = new JSONObject();
		
		Connection con=db.getConnection();
		
		try
		{
			
			String activeSubuser="update subuser set acctLocked='false' where childUserName='"+userid+"'";
			
			String activeSubuserLogin ="update logincontrol set acctlocked='false' where username='"+userid+"'";
			
			PreparedStatement ps = con.prepareStatement(activeSubuser);
			
			PreparedStatement ps1 = con.prepareStatement(activeSubuserLogin);
			
			ps.executeUpdate();
			
			ps1.executeUpdate();
			
			res.put("StatusCode", 200);
			res.put("Message", "success");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
			try
			{
				res.put("StatusCode", 401);
				res.put("Message", e);
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
			
		}
		
		return res.toString();
	}

	
//8.============================================Do Sub User Suspend========================================//
	@Override
	public String doSubUserSuspend(String userid) 
	{
		
		JSONObject res = new JSONObject();
		
		Connection con=db.getConnection();
		
		try
		{
			String suspendSubuser="update subuser set acctLocked='true' where childUserName='"+userid+"'";
			
			String suspendSubuserLogin ="update logincontrol set acctlocked='true' where username='"+userid+"'";
			
			PreparedStatement ps = con.prepareStatement(suspendSubuser);
			
			PreparedStatement ps1 = con.prepareStatement(suspendSubuserLogin);
			
			ps.executeUpdate();
			
			ps1.executeUpdate();
			
			res.put("StatusCode", 200);
			res.put("Message", "success");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
			try
			{
				res.put("StatusCode", 401);
				res.put("Message", e);
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
		finally
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				System.out.println(e);
			}
			
		}
		
		return res.toString();
		
		
	}

//10.=======================================save inventory excel data===========================================//
	@Override
	public String saveInventoryExcelData(List<InventoryData> item, String userid) 
	{
		JSONObject res = new JSONObject();
		
		Connection con=db.getConnection();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date1 = new Date();  
	    
	   String date = formatter.format(date1);
		
	   try
	   {
		   //start transaction
		   con.setAutoCommit(false);
	    
			for(int i=0; i<item.size(); i++)
			{
				
				String invenmain="select item_id,item_qty from inventory_main where user="+userid+ " and item_id='"+item.get(i).getItemId()+"' and location='"+item.get(i).getLocation()+"'";
				
				
				Statement 	 st1=con.createStatement();
				ResultSet	 rs1=st1.executeQuery(invenmain);
			
				//System.out.println("rs1: "+rs1);
				if(rs1.next()==false)
				{
					System.out.println("in is rs1");
					 String s="insert into inventory_main(user,location,item_id,item_qty,item_blocked,reorderpoint,date_updated,last_reference,user_id_updated, total_price) values(?,?,?,?,?,?,?,?,?,?)";
					
					 PreparedStatement	ps = con.prepareStatement(s);
							
					System.out.println("in if");
					ps.setString(1, userid);
					ps.setString(2, item.get(i).getLocation());
					ps.setString(3, item.get(i).getItemId());
					ps.setDouble(4, Double.parseDouble(item.get(i).getItemQty()));
					ps.setString(5, "0");
					ps.setString(6, item.get(i).getReorderPoint());
					ps.setString(7, date);
					ps.setString(8, item.get(i).getLastReference());
					ps.setString(9, userid);
					ps.setDouble(10,  Double.parseDouble(item.get(i).getTotalPrice()));
					
		
					ps.executeUpdate();
					
					String addaction="insert into inventory_transaction (user,location,item_id,action,qty,date,reference, total_price) values (?,?,?,?,?,?,?,?)";
					
					 PreparedStatement	ps1 = con.prepareStatement(addaction);
					 
					 ps1.setString(1, userid);
					 ps1.setString(2, item.get(i).getLocation());
					 ps1.setString(3, item.get(i).getItemId());
					 ps1.setString(4, "inserted");
					 ps1.setDouble(5, Double.parseDouble(item.get(i).getItemQty()));
					 ps1.setString(6, date);
					 ps1.setString(7, item.get(i).getLastReference());
					 ps1.setDouble(8, Double.parseDouble(item.get(i).getTotalPrice()));
					 
					 
					 ps1.executeUpdate();
				
				}
				else
				{
					 
					double qty=rs1.getDouble(2);	
					String updmain="update inventory_main set user=?,location=?,item_id=?,item_qty=?,item_blocked=?,reorderpoint=?,date_updated=?,last_reference=?,user_id_updated=? where user="+userid+ " and item_id='"+item.get(i).getItemId()+"'";
					
					PreparedStatement	ps1 = con.prepareStatement(updmain);
					
					ps1.setString(1, userid);
					ps1.setString(2, item.get(i).getLocation());
					ps1.setString(3, item.get(i).getItemId());
					ps1.setDouble(4, qty+Double.parseDouble(item.get(i).getItemQty()));
					ps1.setString(5, "0");
					ps1.setString(6, item.get(i).getReorderPoint());
					ps1.setString(7, date);
					ps1.setString(8, item.get(i).getLastReference());
					ps1.setString(9, userid);
					
			
					ps1.executeUpdate();
				

					String addaction="insert into inventory_transaction (user,location,item_id,action,qty,date,reference, total_price) values (?,?,?,?,?,?,?,?)";
					
					 PreparedStatement	ps2 = con.prepareStatement(addaction);
					 
					 ps2.setString(1, userid);
					 ps2.setString(2, item.get(i).getLocation());
					 ps2.setString(3, item.get(i).getItemId());
					 ps2.setString(4, "updated");
					 ps2.setDouble(5, Double.parseDouble(item.get(i).getItemQty()));
					 ps2.setString(6, date);
					 ps2.setString(7, item.get(i).getLastReference());
					 ps2.setDouble(8, Double.parseDouble(item.get(i).getTotalPrice()));
					 
					 ps2.executeUpdate();
				
				//isexist=1;
				
				}
		
			}
		
			con.commit();  
			  
	
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
		   return e.toString();
	   }
	   
	   return "success"; 

	}
}		
		
	










