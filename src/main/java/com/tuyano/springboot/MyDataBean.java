package com.tuyano.springboot;

import org.springframework.beans.factory.annotation.Autowired;

import com.tuyano.springboot.repositories.MyDataRepository;

public class MyDataBean {
	
	@Autowired
	MyDataRepository myDataRepository;
	
	public String getTableTagById(Long id) {
		MyData mydata = myDataRepository.getOne(id);
		String result = "<tr><td>" + mydata.getName() 
			+ "</td><td>" + mydata.getMail()
			+ "</td><td>" + mydata.getAge()
			+ "</td><td>" + mydata.getMemo();
		return result;	
	}
}
