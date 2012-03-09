package org.knetwork.webapp.oauth;

import org.scribe.model.Token;
import org.scribe.model.Verifier;

public interface KhanOAuthService {
    
    /**
     * Retrieve the request token url.
     * 
     * @return request token url
     */
    public String getRequestTokenUrl(String callback);

    /**
     * Retrieve the access token
     * 
     * @param requestToken request token (obtained previously)
     * @param verifier verifier code
     * @return access token
     */
    public Token getAccessToken(Token requestToken, Verifier verifier);

    /**
     * Sends an signed OAuth request
     * 
     * @param accessToken access token (obtained previously)
     * @param apiEndpoint the api endpoint path
     */
    public String sendSignedRequest(Token accessToken, String apiEndpoint);

    public String getSignedRequestUrl(Token accessToken, String apiEndpoint);

    /**
     * Returns the OAuth version of the service.
     * 
     * @return oauth version as string
     */
    public String getVersion();
}
