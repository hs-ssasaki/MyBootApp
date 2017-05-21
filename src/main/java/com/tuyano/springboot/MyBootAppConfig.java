package com.tuyano.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 設定クラスを使った Bean の登録。
 * 設定クラス内で、@Beanをつけてクラスのインスタンスを返すメソッドを作れば、
 * それらが全てBeanとして登録される。
 *  */
@Configuration
public class MyBootAppConfig {
	@Bean
	MyDataBean myDataBean() {
		return new MyDataBean();
	}
}
