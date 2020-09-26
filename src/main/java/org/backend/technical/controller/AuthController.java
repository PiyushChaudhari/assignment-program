package org.backend.technical.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.backend.technical.config.UrlConstant;
import org.backend.technical.vo.LoginVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { UrlConstant.HOME, UrlConstant.AUTH })
public class AuthController {

	@RequestMapping(value = { UrlConstant.HOME,UrlConstant.LOGIN }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView(UrlConstant.AUTH_LOGIN);
		modelAndView.addObject("loginVO", new LoginVO());
		return modelAndView;
	}

	@RequestMapping(value = { UrlConstant.SIGNIN }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView signIn(@ModelAttribute("loginVO") LoginVO loginVO) {
		return new ModelAndView(UrlConstant.AUTH_LOGIN);
	}
	
	@RequestMapping(value = { UrlConstant.UNAUTHORIZED }, method = RequestMethod.GET)
	public ModelAndView unauthorized() {
		return new ModelAndView(UrlConstant.AUTH_UNAUTHORIZED);
	}
	
	@RequestMapping(value = { UrlConstant.SIGNOUT }, method = RequestMethod.GET)
	public ModelAndView signOut(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:"+UrlConstant.AUTH_LOGIN);
	}
}
