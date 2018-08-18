package com.springirafinanceapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection
{
		//private String url="jdbc:mysql://localhost:3306/irafinance?useUnicode=true&characterEncoding=UTF-8";
		//private String username="root";
		//private String password="ashish";
	
		private String url;
		private String username;
		private String password;
	
	
		public DbConnection(String url, String username, String password) 
		{
			this.url = url;
			this.username = username;
			this.password = password;
	    }

		
		// method for get connection	
		public Connection getConnection() 
		{
			Connection con=null;
	       
	         try
	           {
	        	 Class.forName("com.mysql.jdbc.Driver");
	        	 con=DriverManager.getConnection(url,username,password);
	       	   }		
	           catch(Exception e)
	           {
		         System.out.println(e);
	        	 System.out.println("Exception in DbConnection......");
	           }
	        
	         return con;
		}

}
