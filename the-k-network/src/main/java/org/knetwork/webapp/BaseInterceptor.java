package org.knetwork.webapp;

import java.net.URL;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.model.ExerciseContainer;
import org.knetwork.webapp.oauth.KhanOAuthService;
import org.knetwork.webapp.service.TokboxService;
import org.knetwork.webapp.util.ApiHelper;
import org.knetwork.webapp.util.KhanAcademyApi;
import org.knetwork.webapp.util.SessionMapUtil;
import org.scribe.model.OAuthConstants;
import org.scribe.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * This interceptor checks the session for whether a user is logged in or not, setting 
 * appropriate user information in the modelAndView before the view is rendered.
 * <p/>
 * The idea is that the user information will possibly be needed on every page or view, 
 * but that we don't necessarily want to put that information in the session.
 * @author Chris
 *
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private KhanOAuthService oauthService;
    private KhanAcademyApi api;
    private TokboxService tokboxService;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		
		HttpSession session = request.getSession();
		
		final Token accessToken = (Token) session.getAttribute("accessToken");
		if(modelAndView!=null) modelAndView.addObject("loggedIn", accessToken != null);
        if (accessToken == null) {
            String authority = new URL(request.getRequestURL().toString()).getAuthority();
            final String callbackUrl = String.format("http://%s/%s", authority, OAuthConstants.CALLBACK);
            logger.debug("Callback url for OAuth is: " + callbackUrl);
            final String requestTokenUrl = oauthService.getRequestTokenUrl(callbackUrl);
            if(modelAndView!=null) modelAndView.addObject("requestTokenUrl", requestTokenUrl);
        } else {
	        final ApiHelper apiHelper = new ApiHelper(accessToken, oauthService, api);
	        if(modelAndView!=null) modelAndView.addObject("exercises", new ExerciseContainer(apiHelper.getExercises(), apiHelper.getBadges()));
	        if(modelAndView!=null) modelAndView.addObject("user", apiHelper.getUser());
        }
        
        if(modelAndView!=null) modelAndView.addObject("learningSessions",SessionMapUtil.getLearningSessions());
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		boolean result = super.preHandle(request, response, handler);
        return result;
	}

	public KhanOAuthService getOauthService() {
		return oauthService;
	}

	@Inject
	public void setOauthService(KhanOAuthService oauthService) {
		this.oauthService = oauthService;
	}

	public KhanAcademyApi getApi() {
		return api;
	}

	@Inject
	public void setApi(KhanAcademyApi api) {
		this.api = api;
	}

	public TokboxService getTokboxService() {
		return tokboxService;
	}

	@Inject
	public void setTokboxService(TokboxService tokboxService) {
		this.tokboxService = tokboxService;
	}

}
