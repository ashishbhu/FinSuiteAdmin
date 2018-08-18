package com.springirafinanceapp.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartFile;

@ComponentScan
public interface Excel {
	
	public String svaeExcel(MultipartFile uploading, String userid);
	
	public String uploadInventoryExcel(MultipartFile uploading, String userid);

}
