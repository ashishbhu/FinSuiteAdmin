package com.springirafinanceapp.entity;

public class Item
{
	private String subId;
	private String itemId;
	private String itemName;
	private String itemPrice;
	private String measurement;
	private String itemCategory;
	private String gstCategory;
	private String startDate;
	private String endDate;
	private String count;
	private String version;
	
	private String BarCode;
	private String HsnNumber;
	private String printName;
	
	// Default Constructor
	public Item()
	{
		
	}

	public String getSubId() 
	{
		return subId;
	}

	public void setSubId(String subId)
	{
		this.subId = subId;
	}

	public String getItemId() 
	{
		return itemId;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	public String getItemName() 
	{
		return itemName;
	}

	public void setItemName(String itemName) 
	{
		this.itemName = itemName;
	}

	public String getItemPrice()
	{
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) 
	{
		this.itemPrice = itemPrice;
	}

	public String getMeasurement() 
	{
		return measurement;
	}

	public void setMeasurement(String measurement) 
	{
		this.measurement = measurement;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) 
	{
		this.itemCategory = itemCategory;
	}

	public String getGstCategory() 
	{
		return gstCategory;
	}

	public void setGstCategory(String gstCategory) 
	{
		this.gstCategory = gstCategory;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getCount()
	{
		return count;
	}

	public void setCount(String count) 
	{
		this.count = count;
	}

	public String getVersion() 
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getPrintName() 
	{
		return printName;
	}

	public void setPrintName(String printName) 
	{
		this.printName = printName;
	}

	public String getBarCode() 
	{
		return BarCode;
	}

	public void setBarCode(String barCode)
	{
		BarCode = barCode;
	}

	public String getHsnNumber() 
	{
		return HsnNumber;
	}

	public void setHsnNumber(String hsnNumber) 
	{
		HsnNumber = hsnNumber;
	}
	
	
	

}
