package son.jun.Test;

import java.util.List;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import son.jun.Test.bean.TestBean;
import son.jun.Test.service.TestService;



@Controller

public class TestController {

	@Autowired
	TestService service;

	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String test(Model model) throws Exception {

		List<TestBean> list;		
		list = service.test();	
		model.addAttribute("list",list);	
		return "test";

	}

}