package com.springirafinanceapp.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springirafinanceapp.config.PathConfig;
import com.springirafinanceapp.dao.DaoImplementation;
import com.springirafinanceapp.entity.InventoryData;
import com.springirafinanceapp.entity.Item;

@Service
public class ImplementsExcel implements Excel {

	
	//1.------------------------upload item excel----------------------------------------//
	
	@Override
	public String svaeExcel(MultipartFile uploading, String userid) 
	{
		JSONObject res = new JSONObject();
		
		//first check is user registered and active or not
		DaoImplementation dao = new DaoImplementation();
		
		try
		{
			String isExist = dao.checkUserExistance(userid);
			
			if(isExist == "notexist")
			{
				res.put("StatusCode", 402);
				res.put("Message", "User Not Exist");
				return res.toString();
			}
		
			// Verify file which needed to excel
			String fileName = uploading.getOriginalFilename().toLowerCase();
			
			if(fileName.endsWith(".xlsx") == false)
			{
				res.put("StatusCode", 401);
				res.put("Message", "Please Upload Excel File Only");
				return res.toString();
			}
			
			ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
			
			PathConfig excelpath = (PathConfig) context.getBean("myPath");
			
			context.close();
			
			String path = excelpath.getExcelPath();
			
			
			
			File file = new File(path+"\\"+userid+"_"+uploading.getOriginalFilename());
			//System.out.println(file);
			uploading.transferTo(file);
			
			Workbook workbook = WorkbookFactory.create(file);
			 
			Sheet sheet = workbook.getSheetAt(0);
				  
				  
			DataFormatter dataFormatter = new DataFormatter();
			Iterator<Row> rowIterator = sheet.rowIterator();
			
			List<Item> item = new ArrayList<Item>();
			
			
			while (rowIterator.hasNext()) 
			{
				Item newItem = new Item();
				 List<String> itemdetail=null;
				Row row = rowIterator.next();
				
			  if(row.getRowNum()>0)
			  {
				 
				  itemdetail = new ArrayList<String>();
			  // Now let's iterate over the columns of the current row
			    Iterator<Cell> cellIterator = row.cellIterator();
			    int count=1;
			   
			    
			    while (cellIterator.hasNext())
			    {
			    
			    		 Cell cell = cellIterator.next();
			    		 
				         String cellValue = dataFormatter.formatCellValue(cell);
				        
				         itemdetail.add(cellValue);
				         
				         System.out.print(cellValue + "\t");
					     
			     }
			   // System.out.print("\n");
			    newItem.setItemName(itemdetail.get(0));
			    newItem.setItemPrice(itemdetail.get(1));
			    newItem.setMeasurement(itemdetail.get(2));
			    newItem.setItemCategory(itemdetail.get(3));
			    newItem.setGstCategory(itemdetail.get(4));
			    newItem.setBarCode(itemdetail.get(5));
			    newItem.setHsnNumber(itemdetail.get(6));
			    newItem.setPrintName(itemdetail.get(7));
			    
			    
			    item.add(newItem);
			    
	              
			   // System.out.println("\u0142o\u017Cy\u0142");
			    
			   }
			}
			   
			    if(item.size()>0)
			    {
			     	String message = dao.saveExcelData(item,userid);
			    	//System.out.println(message +" टीम इंडिया");
			    	if(message == "success")
			     	{
			    		res.put("StatusCode", 200);
			    		res.put("Message", "success");
			    	}
			    	else
			    	{
			    		res.put("StatusCode", 500);
			    		res.put("Message", message);
			    		return res.toString();
			    	}
			    }
			    else
			    {
			    	res.put("StatusCode", 403);
			    	res.put("Message", "Item Not Found in Excel Sheet");
			    	return res.toString();
			    }
			   // newItem.setItemName(itemdetail.get(0));
			   
			    
			  
		} 
		catch (Exception e)
		{
			
			System.out.println(e);
			
			try
			{
				res.put("StatusCode", 405);
		    	res.put("Message",e);
		    	
		    	return res.toString();
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		
		}
	  //System.out.println(uploading.getOriginalFilename());
	  return res.toString();
	}


//10.=================================Upload Inventory Data Excel============================================================//	
	
	@Override
	public String uploadInventoryExcel(MultipartFile uploading, String userid) 
	{
		
		JSONObject res = new JSONObject();
		
		//first check is user registered and active or not
		DaoImplementation dao = new DaoImplementation();
		
		try
		{
			String isExist = dao.checkUserExistance(userid);
			
			if(isExist == "notexist")
			{
				res.put("StatusCode", 402);
				res.put("Message", "User Not Exist");
				return res.toString();
			}
		
			// Verify file which needed to excel
			String fileName = uploading.getOriginalFilename().toLowerCase();
			
			if(fileName.endsWith(".xlsx") == false)
			{
				res.put("StatusCode", 401);
				res.put("Message", "Please Upload Excel File Only");
				return res.toString();
			}
			
			ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
			
			PathConfig excelpath = (PathConfig) context.getBean("myPath");
			
			context.close();
			
			String path = excelpath.getInventoryPath();
			
			File file = new File(path+"\\"+userid+"_"+uploading.getOriginalFilename());
			//System.out.println(file);
			uploading.transferTo(file);
			
			
			Workbook workbook = WorkbookFactory.create(file);
			 
			Sheet sheet = workbook.getSheetAt(0);
				  
				  
			DataFormatter dataFormatter = new DataFormatter();
			Iterator<Row> rowIterator = sheet.rowIterator();
			
			List<InventoryData> item = new ArrayList<InventoryData>();
			
			
			while (rowIterator.hasNext()) 
			{
				InventoryData newItem = new InventoryData();
				 List<String> itemdetail=null;
				Row row = rowIterator.next();
				
			  if(row.getRowNum()>0)
			  {
				 
				  itemdetail = new ArrayList<String>();
			  // Now let's iterate over the columns of the current row
			    Iterator<Cell> cellIterator = row.cellIterator();
			    int count=1;
			   
			    
			    while (cellIterator.hasNext())
			    {
			    
			    		 Cell cell = cellIterator.next();
			    		 
				         String cellValue = dataFormatter.formatCellValue(cell);
				        
				         itemdetail.add(cellValue);
				         
				        // System.out.print(cellValue + "\t");
					     
			     }
			   // System.out.print("\n");
			    newItem.setLocation(itemdetail.get(0));
			    newItem.setItemId(itemdetail.get(1));
			    newItem.setItemQty(itemdetail.get(2));
			    newItem.setReorderPoint(itemdetail.get(3));
			    newItem.setLastReference(itemdetail.get(4));
			    newItem.setTotalPrice(itemdetail.get(5));
			 
			    item.add(newItem);
			    
	              
			   // System.out.println("\u0142o\u017Cy\u0142");
			    
			   }
			}
			
			 if(item.size()>0)
			    {
			     	String message = dao.saveInventoryExcelData(item,userid);
			    	//System.out.println(message +" टीम इंडिया");
			    	if(message == "success")
			     	{
			    		res.put("StatusCode", 200);
			    		res.put("Message", "success");
			    	}
			    	else
			    	{
			    		res.put("StatusCode", 500);
			    		res.put("Message", message);
			    		return res.toString();
			    	}
			    }
			    else
			    {
			    	res.put("StatusCode", 403);
			    	res.put("Message", "Item Not Found in Excel Sheet");
			    	return res.toString();
			    }
			
			
		
		}
		catch(Exception e)
		{
			System.out.println(e);
			
			try
			{
				res.put("StatusCode", 405);
		    	res.put("Message",e);
		    	
		    	return res.toString();
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
		
		return res.toString();

	}
}
