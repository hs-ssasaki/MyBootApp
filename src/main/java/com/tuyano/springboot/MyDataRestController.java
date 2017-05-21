package com.tuyano.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * RESTサービスの作り方。
 * サーバでは、@RestController で、Javaオブジェクトを返却するだけ（自動でJSONに変換して送信される）
 * クライアントでは、JQuery で、受信結果をJavascriptオブジェクトとして処理するだけ（自動でJSONをJavascriptオブジェクトとして取り込まれる）
 */
@RestController
public class MyDataRestController {
	@Autowired
	MyDataService myDataService;
	
	@Autowired
	MySampleBean bean;
	
	@RequestMapping(value = "/rest")
	public List<MyData> restAll() {
		return (List<MyData>)myDataService.getAll();
	}

	@RequestMapping(value="/rest/{num}")
	public MyData rest(@PathVariable int num) {
		return (MyData)myDataService.get(num);
	}

	@RequestMapping("/count")
	public int count() {
		return bean.count();
	}
}
