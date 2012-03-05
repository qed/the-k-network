package org.knetwork.webapp.controller;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public String loginSuccess(final HttpSession session,
			final HttpServletRequest request, final Model model)
			throws MalformedURLException {
		
		session.setAttribute("isLoggedIn", true);
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login/login";
	}
	
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginFailed(final HttpSession session,
			final HttpServletRequest request, final Model model)
			throws MalformedURLException {
		
		session.setAttribute("isLoggedIn", false);
		model.addAttribute("failureMessage", "<div class='alert alert-error'><h3>Incorrect user name or password.</h3></div>");
		return "login/login";
	}
}
