package com.tuyano.springboot;


/*
 * Thymeleafにユーティリティオブジェクトを追加するには、
 * Dialectクラスと、ユーティリティオブジェクト本体のクラスを用意する。
 * 以下は、ユーティリティオブジェクトのクラス。
 */
public class MyTLUtility {
	
	public String hello() {
		return "Hello";
	}
	
	public String prevUrl(int num) {
		return "/page/" + (num > 1 ? num - 1 : 1);
	}
	
	public String nextUrl(int num) {
		return "/page/" + (num + 1);
	}
}
