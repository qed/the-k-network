package org.knetwork.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opentok.api.OpenTokSDK;
import com.opentok.api.OpenTokSession;
import com.opentok.api.constants.RoleConstants;
import com.opentok.exception.OpenTokException;

@Service
public class TokboxServiceImpl implements TokboxService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private OpenTokSession oSession;
	private OpenTokSDK sdk;
	
	private String publisherToken;
	private String subscriberToken;
	private String moderatorToken;
	
	private String sessionId;
	private int apiKey;
	private String apiSecret;
	
	/* (non-Javadoc)
	 * @see org.knetwork.webapp.service.TokboxService#getTokboxSessionId()
	 */
	@Override
	public String getTokboxSessionId() {
		if(oSession==null) {
			try {
			createSession();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return oSession.getSessionId();
	}
	
	public int getApiKey() {
		return apiKey;
	}
	
	public TokboxServiceImpl() {
		super();
		this.apiKey = Integer.parseInt("12109012");
		this.apiSecret = "52d0de2b49af6597a54f8a4f0b3195c01c57f6b5";
	}
	
	/* (non-Javadoc)
	 * @see org.knetwork.webapp.service.TokboxService#createSession()
	 */
	@Override
	public void createSession() throws OpenTokException {
		this.sdk = new OpenTokSDK(apiKey, apiSecret);
		
		String s = sdk.generate_token("session");
		logger.info("Tokbox token: " + s);
		
		publisherToken = sdk.generate_token("session",RoleConstants.PUBLISHER);
		subscriberToken = sdk.generate_token("session",RoleConstants.SUBSCRIBER);
		moderatorToken = sdk.generate_token("session",RoleConstants.MODERATOR);
	
		logger.info("Token, publish:    " + publisherToken);
		logger.info("Token, subscriber: " + subscriberToken);
		logger.info("Token, moderator:  " + moderatorToken);
	
		//Generate a basic session
		oSession = sdk.create_session();
		logger.info("Creating Tokbox session: " + oSession.session_id);
	}

	public String getPublisherToken() {
		return publisherToken;
	}

	public String getSubscriberToken() {
		return subscriberToken;
	}

	public String getModeratorToken() {
		return moderatorToken;
	}
	
	
}
