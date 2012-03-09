package org.knetwork.webapp;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    public String display(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
    	model.addAttribute("returnTo", "/");
        return "home";
    }
    
    @RequestMapping("home")
    public String home(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
    	model.addAttribute("returnTo", "/");
        return "home";
    }
    
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("accessToken");
        return "home";
    }

}
