package org.knetwork.webapp.controller;

import java.net.MalformedURLException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.service.TokboxService;
import org.knetwork.webapp.util.SessionMapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opentok.exception.OpenTokException;

@Controller
public class LearningController {
    
	private final TokboxService tokboxService;

	@Inject
	public LearningController(TokboxService tokboxService) {
		super();
		this.tokboxService = tokboxService;
	}

    @RequestMapping("learn/createMeeting")
    public String createNewMeeting(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
    	String learningSessionId = SessionMapUtil.generateLearningSessionId();
    	model.addAttribute("learningSessionId", learningSessionId);
    	session.setAttribute("learningSessionId",learningSessionId);
    	String sessionTitle = (String)request.getParameter("sessionTitle");
    	try {
			Map<String, String> tokboxMap = tokboxService.createSession();
			String tokboxSessionId = tokboxMap.get("tokboxSessionId");
			String moderatorToken  = tokboxMap.get("moderatorToken");
			SessionMapUtil.addTokboxSessionId(learningSessionId, tokboxSessionId);
			SessionMapUtil.setSessionTitle(learningSessionId, sessionTitle);
			
			System.out.println("Adding tokbox session id:" + tokboxSessionId + " to learning session:" + learningSessionId);
			
			model.addAttribute("tokboxSessionId", tokboxSessionId);
			session.setAttribute("moderatorToken", moderatorToken);
			session.setAttribute("tokboxSessionId", tokboxSessionId);
		} catch (OpenTokException e) {
			e.printStackTrace();
		}
    	
        return String.format("redirect:/learn/join?learningSessionId=%s", learningSessionId);
    }
    
    @RequestMapping("learn/setNickName")
    public String setNickName(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
    	session.setAttribute("nickName", request.getParameter("nickName"));
        return String.format("redirect:/");
    }
    
    @RequestMapping("learn/join")
    public String displayLearningSession(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
    	String newLearningSessionId = (String)request.getParameter("learningSessionId");
    	String learningSessionId = (String)session.getAttribute("learningSessionId");
    	if(!newLearningSessionId.equals(learningSessionId)) {
    		session.setAttribute("learningSessionId", newLearningSessionId);
    		learningSessionId = newLearningSessionId;
    	}
    	System.out.println("Loading pages with learning session: " + learningSessionId);
    	model.addAttribute("sessionTitle", SessionMapUtil.getSessionTitle(learningSessionId));
    	model.addAttribute("learningSessionId", learningSessionId);
    	model.addAttribute("meetingExists",true);
        return "learning/view";
    }
    
    @RequestMapping("learn/whiteboard")
    public String showWhiteboard(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
    	String learningSessionId = (String)session.getAttribute("learningSessionId");
    	model.addAttribute("learningSessionId", learningSessionId);
    	model.addAttribute("sessionTitle", SessionMapUtil.getSessionTitle(learningSessionId));
        return "learning/whiteboard";
    }
    
    
}
