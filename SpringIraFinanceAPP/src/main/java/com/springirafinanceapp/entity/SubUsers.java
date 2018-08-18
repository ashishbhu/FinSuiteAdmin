package com.springirafinanceapp.entity;

public class SubUsers
{
	private String parentId;
	private String subUserName;
	private String subUserId;
	private String status;
	
	
	public String getParentId()
	{
		return parentId;
	}
	
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	
	
	
	
	public String getSubUserName() 
	{
		return subUserName;
	}

	public void setSubUserName(String subUserName) 
	{
		this.subUserName = subUserName;
	}

	public String getSubUserId()
	{
		return subUserId;
	}
	
	public void setSubUserId(String subUserId)
	{
		this.subUserId = subUserId;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	

}
