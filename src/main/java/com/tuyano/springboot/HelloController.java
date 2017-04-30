package com.tuyano.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	// @RequestMapping は、
	// value でパス指定、method でメソッド指定
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "お名前を入力してください");
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	// @PathValiable と同様に、フォームからパラメータを受け取るには、
	// @RequestParam(フォームパラメータ名) を引数につける
	public ModelAndView send(@RequestParam("text1")String str, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "こんにちは" + str + "さん");
		mav.addObject("value", str);
		return mav;
	}

}