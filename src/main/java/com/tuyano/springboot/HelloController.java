package com.tuyano.springboot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HelloController {
	
	@Autowired
	MyDataRepository myDataRepository;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		Iterable<MyData> list = myDataRepository.findAll();
		mav.addObject("msg", "this is sample content.");
		mav.addObject("datalist", list);
		return mav;
	}
	
	@PostConstruct
	public void init() {
		MyData data1 = new MyData();
		data1.setName("Tuyano");
		data1.setAge(23);
		data1.setMail("aaa@xxdd");
		data1.setMemo("this is data1");
		MyData data2 = new MyData();
		data2.setName("Muyano");
		data2.setAge(42);
		data2.setMail("afe@xxdd");
		data2.setMemo("this is data2");
		MyData data3 = new MyData();
		data3.setName("Yuyano");
		data3.setAge(45);
		data3.setMail("dijfe@xxdd");
		data3.setMemo("this is data3");
		myDataRepository.saveAndFlush(data1);
		myDataRepository.saveAndFlush(data2);
		myDataRepository.saveAndFlush(data3);
	}
}
