package org.knetwork.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.oauth.KhanOAuthService;
import org.knetwork.webapp.util.KhanAcademyApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A simple controller base class that gives every controller access to the auth service and api.
 * @author Chris
 *
 */
public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected KhanOAuthService oauthService;
	protected KhanAcademyApi api;

	@RequestMapping("**/favicon.ico")
    public String favIconForward(){
        return "forward:/resources/img/light-bulb-icon.png";
    }
	
	@Inject
	public void setOauthService(KhanOAuthService oauthService) {
		this.oauthService = oauthService;
	}

	@Inject
	public void setApi(KhanAcademyApi api) {
		this.api = api;
	}
}
