package com.tuyano.springboot;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		// @QueryでJPQLを指定した、レポジトリのメソッドを実行
		Iterable<MyData> list = myDataRepository.findAllOrderByName();
		mav.addObject("msg", "MyData sample");
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/age", method=RequestMethod.GET)
	public ModelAndView age(ModelAndView mav) {
		mav.setViewName("index");
		// @QueryでJPQLを指定した、レポジトリのメソッドを実行（引数つき）
		Iterable<MyData> list = myDataRepository.findByAge(10, 40);
		mav.addObject("msg", "MyData sample");
		mav.addObject("datalist", list);
		return mav;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData sample");
		mav.addObject("value", "");
		Iterable<MyData> list = dao.getAll();
		mav.addObject("datalist", list);
		return mav;
	}

	// @RequestParamじゃなくてもフォームのデータは受け取れる。
	// String param = HttpServletRequest#getParameter(コントロール名)とすればいい。
	// メソッド引数に、@RequestParam(コントロール名)String param とするのと同じ。
	@RequestMapping(value="/find", method=RequestMethod.POST)
	public ModelAndView search(ModelAndView mav, HttpServletRequest httpServletRequest) {
		mav.setViewName("find");
		String param = httpServletRequest.getParameter("fstr");
		if (param == "") {
			mav =  new ModelAndView("redirect:/find");
		} else {
			mav.addObject("title", "Find result");
			mav.addObject("msg", "「" + param + "」の検索結果");
			mav.addObject("value", param);
			List<MyData> list = dao.find(param);
			mav.addObject("datalist", list);
		}
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
