package org.knetwork.webapp.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.opentok.exception.OpenTokException;

@Service
public interface TokboxService {
	public Map<String, String> createSession(String learningSessionId) throws OpenTokException;
	public Map<String, String> getUserTokens(String tokboxSessionId) throws OpenTokException;
	public int getApiKey();
}