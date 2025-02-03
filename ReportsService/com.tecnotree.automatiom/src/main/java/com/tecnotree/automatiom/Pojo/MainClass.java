package com.tecnotree.automatiom.Pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainClass {

	public static void main(String[] args) throws JsonProcessingException {
		// TODO Auto-generated method stub
		MasterFiledServiceConfigure master = new MasterFiledServiceConfigure("FileUploadSftp",3,"SFTP",5000,new FileUpload("7441",500,"/bulk-store/"));
		System.out.println("----------"+master.toString());
        ObjectMapper Obj = new ObjectMapper();  
        String jsonStr = Obj.writeValueAsString(master);  
        System.out.println(jsonStr);



	}

}
