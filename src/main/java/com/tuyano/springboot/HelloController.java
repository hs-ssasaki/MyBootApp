package com.tuyano.springboot;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
	@RequestMapping("/{id}/{month}/{num}/{tax}")
	public ModelAndView index(@PathVariable int id, @PathVariable int month, @PathVariable int num, @PathVariable int tax, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "current data.");
		mav.addObject("html_msg", "message 1<br/>message 2<br/>message 3");
		DataObject obj = new DataObject(123, "hanako", "hanako@flower");
		mav.addObject("object", obj);
		
		mav.addObject("id", id);
		mav.addObject("check", id % 2 == 0);
		mav.addObject("trueVal", "Even number");
		mav.addObject("falseVal", "Odd number");
		
		mav.addObject("check_num", Math.floor(month / 3));
		
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(new String[]{"taro", "taro@yamada", "090-999-9999"});
		data.add(new String[]{"hanako", "hanako@flower", "080-888-8888"});
		data.add(new String[]{"sachiko", "sachiko@happy", "080-999-9999"});
		mav.addObject("data", data);
	
		ArrayList<DataObject> dataObjectList = new ArrayList<DataObject>();
		dataObjectList.add(new DataObject(0, "taro", "taro@yamada"));
		dataObjectList.add(new DataObject(1, "hanako", "hanako@flower"));
		dataObjectList.add(new DataObject(2, "sachiko", "sachiko@happy"));
		mav.addObject("dataObjectList", dataObjectList);
		mav.addObject("expression", "num >= dataObjectList.size() ? 0 : num");
		
		mav.addObject("tax", tax);
		return mav;
	}
}

class DataObject {
	private int id;
	private String name;
	private String value;
	
	public DataObject(int id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	
}