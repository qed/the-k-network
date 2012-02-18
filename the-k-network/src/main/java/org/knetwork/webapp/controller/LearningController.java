package org.knetwork.webapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class LearningController {
    
    @Value("${twiddla.username}")
    private String username;
    
    @Value("${twiddla.password}")
    private String password;

    @RequestMapping("/")
    public String createNewMeeting() {
        RestTemplate template = new RestTemplate();
        String sessionId = template.postForObject("http://www.twiddla.com/new.aspx?username={username}&password={password}", null, String.class, username, password);
        return String.format("redirect:learn/%s", sessionId);
    }
    
    @RequestMapping("learn/{sessionId}")
    public String displayLearningSession(@PathVariable String sessionId) {
        return "learning/view";
    }
}
