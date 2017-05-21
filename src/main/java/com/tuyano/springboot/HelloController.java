package com.tuyano.springboot;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

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
	
	@Autowired
	MyDataService myDataService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData sample");
		// DAOではなくサービスを呼び出すように変更する
		Iterable<MyData> list = myDataService.getAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method=RequestMethod.GET)
	public ModelAndView age(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData sample");
		// DAOではなくサービスを呼び出すように変更する
		Iterable<MyData> list = myDataService.getAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value="/find", method=RequestMethod.POST)
	public ModelAndView search(ModelAndView mav, HttpServletRequest httpServletRequest) {
		mav.setViewName("find");
		String param = httpServletRequest.getParameter("fstr");
		if (param == "") {
			mav =  new ModelAndView("redirect:/find");
		} else {
			mav.addObject("title", "Find result");
			mav.addObject("msg", "「" + param + "」の検索結果");
			mav.addObject("value", param);
			// DAOではなくサービスを呼び出すように変更する
			List<MyData> list = myDataService.find(param);
			mav.addObject("datalist", list);
		}
		return mav;
	}
	
	@PostConstruct
	public void init() {
		MyData data1 = new MyData();
		data1.setName("Tuyano");
		data1.setAge(23);
		data1.setMail("aaa@xxdd");
		data1.setMemo("080888888");
		MyData data2 = new MyData();
		data2.setName("Muyano");
		data2.setAge(42);
		data2.setMail("afe@xxdd");
		data2.setMemo("090999999");
		MyData data3 = new MyData();
		data3.setName("Yuyano");
		data3.setAge(45);
		data3.setMail("dijfe@xxdd");
		data3.setMemo("555555555");
		myDataRepository.saveAndFlush(data1);
		myDataRepository.saveAndFlush(data2);
		myDataRepository.saveAndFlush(data3);
	}
}
