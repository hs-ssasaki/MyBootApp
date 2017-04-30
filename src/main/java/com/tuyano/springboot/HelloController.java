package com.tuyano.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping("/{num}")
	// 戻り値を ModelAndView クラスに変更、引数に ModelAndView クラス追加
	public ModelAndView index(@PathVariable int num, ModelAndView mav) {
		int result = 0;
		for (int i = 1; i <= num; i++) {
			result += i;
		}
		// ModelAndView#addObject で値を渡す
		mav.addObject("msg", "total: " + result);
		// ModelAndView#setViewName でテンプレート名を渡す
		mav.setViewName("index");
		return mav;
	}
}