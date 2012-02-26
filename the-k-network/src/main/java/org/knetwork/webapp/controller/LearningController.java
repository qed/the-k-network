package org.knetwork.webapp.controller;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.util.SessionMapUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

//@Controller
public class LearningController {
    
    @Value("${twiddla.username}")
    private String username;
    
    @Value("${twiddla.password}")
    private String password;

    @RequestMapping("/")
    public String createNewMeeting(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
        // RestTemplate template = new RestTemplate();
        // String sessionId = template.postForObject("http://www.twiddla.com/new.aspx?username={username}&password={password}", null, String.class, username, password);
    	String sessionId = SessionMapUtil.generateLearningSessionId();
    	model.addAttribute("learningSessionId", sessionId);
        return String.format("redirect:learn/%s", sessionId);
    }
    
    @RequestMapping("learn/{sessionId}")
    public String displayLearningSession(@PathVariable String sessionId) {
        return "learning/view";
    }
}
