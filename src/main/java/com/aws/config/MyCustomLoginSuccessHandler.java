package com.aws.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class MyCustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	public MyCustomLoginSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			String redirectUrl = (String) session.getAttribute("url_prior_login");
			System.out.println(redirectUrl);
			if (redirectUrl != null) {
				redirectUrl+="successlogin";
				session.removeAttribute("url_prior_login");
				getRedirectStrategy().sendRedirect(request, response, redirectUrl);
			}			
		}
		super.onAuthenticationSuccess(request, response, authentication);		
	}
}