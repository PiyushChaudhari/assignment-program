package org.backend.technical.voter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.util.UrlPathHelper;

public class MinuteBasedVoter implements AccessDecisionVoter<Object> {
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> collection) {

		FilterInvocation fi = (FilterInvocation) object;
		String currentUrl = fi.getRequestUrl();

		List<String> byPassUrlList = new ArrayList<>();
		byPassUrlList.add("/auth/login");
		byPassUrlList.add("/auth/signIn");
		byPassUrlList.add("/auth/signOut");

		if (currentUrl.equals("/") || byPassUrlList.contains(currentUrl)) {
			return ACCESS_GRANTED;
		}
		
		HttpServletRequest req = fi.getRequest();

		String currenturl = new UrlPathHelper().getPathWithinApplication(req);
		String url = null;
		
		String urlDivider[] = currenturl.replaceFirst("/", "").split("/");

		try {
			StringBuilder sb = new StringBuilder();
			if (urlDivider.length > 0)
				
				sb.append(urlDivider[0]);
			
			if (urlDivider.length > 1)
				sb.append("_" + urlDivider[1]);

			url = sb.toString().toUpperCase();
		} catch (Exception e) {
			return ACCESS_DENIED;
		}
		
		return authentication.getAuthorities().contains(new SimpleGrantedAuthority(url)) ? ACCESS_GRANTED : ACCESS_DENIED;

	}
}
