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
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PagesController {

	@RequestMapping(value = "/")
	public String homePage(ModelMap m) {
		m.addAttribute("message", "Home , " + getPrincipal());
		return "home";
	}

	@RequestMapping(value = "/admin**" )
	public String adminPage(ModelMap m) {
		m.addAttribute("message", "Admin , " + getPrincipal());
		return "home";
	}

	@RequestMapping(value = "/login")
	public String login(ModelMap m) {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		return userName;
	}

	
	/* @RequestMapping(value={"/author"}) public String dbaPage(ModelMap m){
	 * m.addAttribute("login", true ); m.addAttribute("message","author , " +
	 * getPrincipal() ); return "home"; }
	 

	
	 * @RequestMapping(value="/accessDenied") public String
	 * accessDenied(ModelMap m){ m.addAttribute("message","accessDenied , " +
	 * getPrincipal() ); return "accessDenied"; }
	 */

}
