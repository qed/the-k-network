package org.knetwork.webapp.service;

import org.springframework.stereotype.Service;

import com.opentok.exception.OpenTokException;

@Service
public interface TokboxService {

	public String getTokboxSessionId();

	public void createSession() throws OpenTokException;
	
	public int getApiKey();
	
	public String getPublisherToken();

	public String getSubscriberToken();

	public String getModeratorToken();

}