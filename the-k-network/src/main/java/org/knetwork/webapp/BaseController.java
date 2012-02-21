package org.knetwork.webapp;

import javax.inject.Inject;

import org.knetwork.webapp.oauth.KhanOAuthService;
import org.knetwork.webapp.util.KhanAcademyApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple controller base class that gives every controller access to the auth service and api.
 * @author Chris
 *
 */
public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected KhanOAuthService oauthService;
	protected KhanAcademyApi api;

	@Inject
	public void setOauthService(KhanOAuthService oauthService) {
		this.oauthService = oauthService;
	}

	@Inject
	public void setApi(KhanAcademyApi api) {
		this.api = api;
	}
}
