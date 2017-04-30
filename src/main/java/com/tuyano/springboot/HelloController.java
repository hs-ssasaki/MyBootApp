package com.tuyano.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("/")
	public String index() {
		// pom.xmlで spring-boot-starter-thymeleaf を設定しているため、
		// 名前で resource/templates 以下のテンプレートが自動ロードされる
		return "index";
	}
}