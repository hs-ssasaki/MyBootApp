package com.tuyano.springboot;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HelloController {
	
	@Autowired
	MyDataRepository myDataRepository;
	
	// @PersistenceContext でEntityManagerのインスタンスを取得する
	// Spring Bootでは、EntityManagerのインスタンスは起動時に登録されており、
	// このアノテーションでインジェクションするのが基本
	//
	// MyDataDaoImpl の方で @PersistenceContext を記述しても動作はする。
	// しかし、@PersistenceContextは、１クラス（EntityManager)につき１インスタンスしか用意されない。
	// 以降の手順で、DAOを複数用意するため、ここでは、コントローラで@PersistenceContextし、それをDAOに渡している。
	// DAOを１クラスにまとめるのであれば、@PersistenceContextは、DAO側に記載してもよい。
	@PersistenceContext
	EntityManager entityManager;
	
	MyDataDaoImpl dao;
	
	/* index.htmlの表示に datalistオブジェクトが必要。
	 * datalistオブジェクトは、メソッド内で作成。 */
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {
		mav.setViewName("index");
		// daoインスタンスのメソッドを呼び出してデータを取得
		Iterable<MyData> list = dao.getAll();
		mav.addObject("msg", "MyData sample");
		mav.addObject("datalist", list);
		return mav;
	}

	@PostConstruct
	public void init() {
		// daoインスタンスを生成
		dao = new MyDataDaoImpl(entityManager);
		MyData data1 = new MyData();
		data1.setName("Tuyano");
		data1.setAge(23);
		data1.setMail("aaa@xxdd");
		data1.setMemo("080888888");
		MyData data2 = new MyData();
		data2.setName("Muyano");
		data2.setAge(42);
		data2.setMail("afe@xxdd");
		data2.setMemo("090999999");
		MyData data3 = new MyData();
		data3.setName("Yuyano");
		data3.setAge(45);
		data3.setMail("dijfe@xxdd");
		data3.setMemo("555555555");
		myDataRepository.saveAndFlush(data1);
		myDataRepository.saveAndFlush(data2);
		myDataRepository.saveAndFlush(data3);
	}
}
