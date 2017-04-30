package com.tuyano.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("/{num}")
	// 引数に Model クラス追加
	public String index(@PathVariable int num, Model model) {
		int result = 0;
		for (int i = 1; i <= num; i++) {
			result += i;
		}
		// Model#addAttribute で値を渡す
		model.addAttribute("msg", "total: " + result);
		return "index";
	}
}