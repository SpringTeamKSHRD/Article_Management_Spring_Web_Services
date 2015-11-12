package com.aws.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {
	
	@RequestMapping(value = "/")
	public String homePage(ModelMap m) {
		m.addAttribute("name", getUsername());
		m.addAttribute("role", getRole());
		m.addAttribute("login", isLogin());
		return "home";
	}

	@RequestMapping(value = "/author")
	public String dbaPage(ModelMap m) {
		m.addAttribute("name", getUsername());
		m.addAttribute("role", getRole());
		m.addAttribute("login", isLogin());
		return "author";
	}

	@RequestMapping(value = "/admin")
	public String adminPage(ModelMap m) {
		m.addAttribute("name", getUsername());
		m.addAttribute("role", getRole());
		m.addAttribute("login", isLogin());
		return "admin";
	}

	@RequestMapping(value = "/login")
	public String login(ModelMap m) {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)  new SecurityContextLogoutHandler().logout(request, response, auth);
		return "redirect:/";
	}

	@RequestMapping(value = "/403")
	public String accessDenied(ModelMap m) {
		m.addAttribute("message", "accessDenied , " + getUsername());
		return "403";
	}
	
	private boolean isLogin(){
		return SecurityContextHolder.getContext().getAuthentication().getName()=="anonymousUser"?false:true;
	}
	
	private String getRole(){
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
	}
	
	private String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails)	return ((UserDetails) principal).getUsername();
		else									return principal.toString();
	}
	//return SecurityContextHolder.getContext().getAuthentication().getName();
	//inject Principal principal//then call principal.getName()

}
