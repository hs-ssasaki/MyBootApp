package com.tuyano.springboot;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.repositories.MsgDataRepository;

@Controller
public class MsgDataController {
	@Autowired
	MsgDataRepository msgDataRepository;
	
	@PersistenceContext
	EntityManager entityManager;

	MsgDataDaoImpl dao;
	
	@RequestMapping(value = "/msg", method = RequestMethod.GET)
	public ModelAndView msg(ModelAndView mav) {
		mav.setViewName("showMsgData");
		mav.addObject("title", "Sample");
		mav.addObject("msg", "MsgData sample");
		MsgData msgData = new MsgData();
		mav.addObject("formModel", msgData);
		List<MsgData> list = (List<MsgData>)dao.getAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/msg", method = RequestMethod.POST)
	public ModelAndView msgform(ModelAndView mav,
			@Valid @ModelAttribute MsgData msgData,
			Errors result) {
		if(result.hasErrors()) {
			mav.setViewName("showMsgData");
			mav.addObject("title", "Sample [Error]");
			mav.addObject("msg", "Please check parameters.");
			return mav;
		} else {
			// エンティティの定義で、MsgData#myDataに、@ManyToOne, @OneToManyと関連付けを定義したおかげで、
			// この記述だけで、msgData に関連する myData のインスタンスもまとめて保存される。 
			msgDataRepository.save(msgData);
			return new ModelAndView("redirect:/msg");
		}
	}
	
	@PostConstruct
	public void init() {
		dao = new MsgDataDaoImpl(entityManager);
	}
}
