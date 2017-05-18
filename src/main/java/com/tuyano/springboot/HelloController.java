package com.tuyano.springboot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HelloController {
	
	@Autowired
	MyDataRepository myDataRepository;
	
	/* index.htmlの表示に formModelオブジェクトとdatalistオブジェクトが必要。
	 * datalistオブジェクトは、メソッド内で作成。
	 * formModelオブジェクトは、@ModelAttribute("formModel") Mydata mydata で、生成している。
	 * @ModelAttributeの記述は、 mav.addAttribute("formModel", new Mydata()) と同等。*/
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {
		mav.setViewName("index");
		Iterable<MyData> list = myDataRepository.findAll();
		mav.addObject("msg", "this is sample content.");
		mav.addObject("datalist", list);
		return mav;
	}

	/* Viewで <form th:object="${formModel}>" を指定したので、formModelオブジェクトに
	 * フォームデータが格納されてくる。ここでは、MyDataクラスを formModelオブジェクトとして指定している */
	@RequestMapping(value = "/", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView form(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {
		myDataRepository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
	public ModelAndView edit(
			@PathVariable long id,
			@ModelAttribute MyData mydata,
			ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "edit mydata.");
		MyData data = myDataRepository.findById(id);
		mav.addObject("formModel", data);
		return mav;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute MyData mydata,
			ModelAndView mav) {
		/* データの更新の仕方は新規作成と同じsaveAndFlushで良い 
		 * 同じidのデータが保存されていれば、更新になる */
		myDataRepository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
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
