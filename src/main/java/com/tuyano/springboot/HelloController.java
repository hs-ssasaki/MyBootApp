package com.tuyano.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HelloController {
	
	@Autowired
	MyDataRepository myDataRepository;
	
	/* JPAのエンティティクラスのインスタンスを自動生成し、
	 * Viewに渡すモデルにaddAttributeするアノテーション @ModelAttribute が用意されている、
	 * @ModelAttribute("formModel") MyData mydata は、
	 * メソッドコール時に、自動で MyData エンティティクラスのインスタンス mydata を自動生成し（つまり、DBエントリを読み込み）、
	 * さらに、model.addAttribute("formModel", mydata) する */
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {
		mav.setViewName("index");
		Iterable<MyData> list = myDataRepository.findAll();
		mav.addObject("msg", "this is sample content.");
		mav.addObject("datalist", list);
		
		return mav;
	}
	
	/* @Transactional(readOnly = fase) でデータの更新もできるトランザクションを設定 */
	@RequestMapping(value="/", method=RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {
		myDataRepository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
}
