package org.backend.technical.controller;

import org.backend.technical.config.UrlConstant;
import org.backend.technical.service.LocatorService;
import org.backend.technical.vo.SearchCityResponseVO;
import org.backend.technical.vo.SearchCityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value= {UrlConstant.LOCATOR})
public class LocatorController {
	
	@Autowired
	private LocatorService locatorService;

	@RequestMapping(value = {UrlConstant.ATMS }, method = RequestMethod.GET)
	public ModelAndView index(final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView(UrlConstant.LOCATOR_ATMS);
		modelAndView.addObject("searchCityVO",new SearchCityVO());
		return modelAndView;
	}
	
	@RequestMapping(value = {UrlConstant.SEARCH_ATMS}, method = RequestMethod.POST)
	public ModelAndView searchAtms(@ModelAttribute("searchCityVO") SearchCityVO searchCityVO,final RedirectAttributes redirectAttributes) {
		if(StringUtils.isEmpty(searchCityVO.getCityName())) {
			
			redirectAttributes.addFlashAttribute("error","Please enter city name.");
			return new ModelAndView("redirect:"+UrlConstant.LOCATOR_ATMS);
			
		}else {
			SearchCityResponseVO  searchCityResponseVO = locatorService.getLocator();			
			if(Boolean.TRUE.equals(searchCityResponseVO.getIsSuccess()) && HttpStatus.OK.equals(searchCityResponseVO.getStatus())) {
				ModelAndView modelAndView = new ModelAndView(UrlConstant.LOCATOR_SEARCH_ATMS);
				modelAndView.addObject("atmLocators",locatorService.getAtmLocator(searchCityVO.getCityName(),searchCityResponseVO.getResponseBody()));
				return modelAndView;
			}else {
				redirectAttributes.addFlashAttribute("error","facing issue to get atms details");
				return new ModelAndView("redirect:"+UrlConstant.LOCATOR_ATMS);
			}
			
		}
	}
}
