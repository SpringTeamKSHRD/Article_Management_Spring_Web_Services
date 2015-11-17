package com.aws.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aws.entities.User;

@Controller
public class PagesController {

	@RequestMapping(value = "/")
	public String homePage(ModelMap m) {
		m.addAttribute("name", getUsername());
		m.addAttribute("role", getRole());
		m.addAttribute("login", isLogin());
		return "home";
	}

	@RequestMapping(value = "/successlogin")
	public String successlogin(ModelMap m) {
		m.addAttribute("name", getUsername());
		System.out.println("successlogin");
		if (getRole().equals("[ROLE_ADMIN]"))
			return "redirect:/admin";
		if (getRole().equals("[ROLE_AUTHOR]"))
			return "redirect:/author";
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
	public String loginPage(HttpServletRequest request, Model model) {
		String referrer = request.getHeader("Referer");
		request.getSession().setAttribute("url_prior_login", referrer);
		// System.out.println(referrer);
		// System.out.println(isLogin());
		/*
		 * if(isLogin()) return "redirect:/"; else return "login";
		 */
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
			new SecurityContextLogoutHandler().logout(request, response, auth);
		return "redirect:/";
	}

	@RequestMapping(value = "/clogin", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Object>> login(@RequestBody User usr, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			request.login(usr.getUsername(), usr.getPassword());
			map.put("MESSAGE", "LOG IN SUCCESS");
			status = HttpStatus.OK;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			map.put("MESSAGE", "LOG IN NOT SUCCESS");
			status = HttpStatus.NOT_FOUND;
			e.printStackTrace();
		}
		System.out.println(isLogin());
		System.out.println(getRole());
		System.out.println(getUsername());
		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	@RequestMapping(value = "/clogout", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Object>> login(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			request.logout();
			map.put("MESSAGE", "LOG OUT SUCCESS");
			status = HttpStatus.OK;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			map.put("MESSAGE", "LOG OUT NOT SUCCESS");
			status = HttpStatus.NOT_FOUND;
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Object>> login1(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;
		System.out.println(isLogin());
		System.out.println(getRole());
		System.out.println(getUsername());
		map.put("MESSAGE", "THIS is HOME :V");
		status = HttpStatus.OK;
		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	@RequestMapping(value = "/403")
	public String accessDenied(ModelMap m) {
		m.addAttribute("message", "accessDenied , " + getUsername());
		return "403";
	}

	private boolean isLogin() {
		return SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser" ? false : true;
	}

	private String getRole() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
	}

	private String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();
		else
			return principal.toString();
	}
	// return SecurityContextHolder.getContext().getAuthentication().getName();
	// inject Principal principal//then call principal.getName()

}
