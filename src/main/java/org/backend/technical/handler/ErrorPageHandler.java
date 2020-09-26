package org.backend.technical.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorPageHandler {

	@ExceptionHandler(value= {Throwable.class})
	public ModelAndView errorPage(Throwable throwable) {
		return getErrorPage();
	}
	
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR,value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler()
	public ModelAndView internalServerErrorPage(Exception exception) {
		return getErrorPage();
	}
	
	private ModelAndView getErrorPage(){
		return new ModelAndView("/error/error-page");
	}
	
	
	
}
