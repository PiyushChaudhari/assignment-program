package org.backend.technical.controller;

import org.backend.technical.config.UrlConstant;
import org.backend.technical.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value= {UrlConstant.USER})
public class UserController {
	
	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(value = { UrlConstant.LIST_USER }, method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView(UrlConstant.USER_LIST);
		mv.addObject("userList", userManagementService.getUserList());
		return mv;
	}
}
