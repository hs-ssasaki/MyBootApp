package com.tuyano.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/*
 * @Componet でBeanを登録できる。
 * 本では、@Autowiredがコンストラクタに必須と書いてあるが、そんなことはない。なくても動く。
 * ただし、以下のようにコンストラクタが複数ある場合、@Autowiredをつけたコンストラクタを使って、
 * インジェクション時のインスタンスが生成される模様。
 */
@Component
public class MySampleBean {
	private int counter = 0;
	private int max;

	public MySampleBean() {
		max = 3;
	}

	/* *
	 * ApplicationArguments は、@SpringBootAplicationが付与されたアプリで、
	 * SpringApplication.run が実行されるときの引数を管理するクラス。
	 * getNonOptionArgs で、引数をListとして受け取れる。
	 */
	@Autowired
	public MySampleBean(ApplicationArguments args) {
		List<String> files = args.getNonOptionArgs();
		try {
			max = Integer.parseInt(files.get(0));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public int count() {
		counter++;
		counter = counter > max ? 0 : counter;
		return counter;
	}
}
