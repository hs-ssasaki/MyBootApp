package com.tuyano.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyBootAppApplication {

	public static void main(String[] args) {
		// MySampleBean で、ApplicationArgument を利用することにしたので、
		// ここで引数を渡す。
		SpringApplication.run(MyBootAppApplication.class, new String[]{"5"});
	}
}
