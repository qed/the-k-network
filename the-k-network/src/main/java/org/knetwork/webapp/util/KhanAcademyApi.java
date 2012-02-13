package org.knetwork.webapp.util;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;
import org.scribe.model.Verb;

public class KhanAcademyApi extends DefaultApi10a {
    
    private static final String ACADEMY_URL = "http://www.khanacademy.org";

	@Override
	public String getRequestTokenEndpoint() {
		return getEndpointUrl("/api/auth/request_token");
	}

	@Override
	public String getAccessTokenEndpoint() {
		return getEndpointUrl("/api/auth/access_token");
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return null;
	}

	@Override
	public Verb getRequestTokenVerb() {
		return Verb.GET;
	}

	@Override
	public Verb getAccessTokenVerb() {
		return Verb.GET;
	}
	
	public String getBadgesEndpoint() {
	    return getEndpointUrl("/api/v1/badges");
	}
	
	public String getExercisesEndpoint() {
		return getEndpointUrl("/api/v1/user/exercises");
	}
	
	public String getUserEndpoint() {
		return getEndpointUrl("/api/v1/user");
	}
	
	private String getEndpointUrl(String path) {
	    return String.format("%s%s", ACADEMY_URL, path);
	}

}
