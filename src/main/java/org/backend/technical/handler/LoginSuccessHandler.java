package org.backend.technical.handler;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	
	private static final Logger LOG = LoggerFactory.getLogger(LoginSuccessHandler.class);	

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response);

		if (response.isCommitted()) {
			LOG.info("Response has already been committed. Unable to redirect to {} " , targetUrl);
			return;
		}
		super.getRedirectStrategy().sendRedirect(request, response, targetUrl);
		super.clearAuthenticationAttributes(request);
	}

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		return "/locator/atms";
	}
}
