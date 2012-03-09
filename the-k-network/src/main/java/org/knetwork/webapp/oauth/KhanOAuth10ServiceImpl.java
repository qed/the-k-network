package org.knetwork.webapp.oauth;

import java.util.Map.Entry;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.utils.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KhanOAuth10ServiceImpl implements KhanOAuthService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String VERSION = "1.0";

    private DefaultApi10a api;
    private String apiKey;
    private String apiSecret;

    public KhanOAuth10ServiceImpl(DefaultApi10a api, String apiKey, String apiSecret) {
        this.api = api;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @Override
    public Token getAccessToken(Token requestToken, Verifier verifier) {
        logger.debug("obtaining access token from " + api.getAccessTokenEndpoint());
        OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
        request.addOAuthParameter(OAuthConstants.TOKEN, requestToken.getToken());
        request.addOAuthParameter(OAuthConstants.VERIFIER, verifier.getValue());

        logger.debug("setting token to: " + requestToken + " and verifier to: " + verifier);
        addOAuthParams(request, requestToken);
        appendSignature(request);
        Response response = request.send();
        return api.getAccessTokenExtractor().extract(response.getBody());
    }

    @Override
    public String sendSignedRequest(Token accessToken, String apiEndpoint) {
        OAuthRequest request = prepareSignedRequest(accessToken, apiEndpoint);
        Response response = request.send();
        return response.getBody();
    }

    private OAuthRequest prepareSignedRequest(Token accessToken, String apiEndpoint) {
        OAuthRequest request = new OAuthRequest(Verb.GET, apiEndpoint);
        logger.debug("signing request: " + request.getCompleteUrl());
        request.addOAuthParameter(OAuthConstants.TOKEN, accessToken.getToken());

        logger.debug("setting token to: " + accessToken);
        addOAuthParams(request, accessToken);
        appendSignature(request);
        return request;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    private void addOAuthParams(OAuthRequest request, Token token) {
        request.addOAuthParameter(OAuthConstants.TIMESTAMP, api.getTimestampService().getTimestampInSeconds());
        request.addOAuthParameter(OAuthConstants.NONCE, api.getTimestampService().getNonce());
        request.addOAuthParameter(OAuthConstants.CONSUMER_KEY, apiKey);
        request.addOAuthParameter(OAuthConstants.SIGN_METHOD, api.getSignatureService().getSignatureMethod());
        request.addOAuthParameter(OAuthConstants.VERSION, getVersion());
        request.addOAuthParameter(OAuthConstants.SIGNATURE, getSignature(request, token));
        logger.debug("appended additional OAuth parameters: " + MapUtils.toString(request.getOauthParameters()));
    }

    private String getSignature(OAuthRequest request, Token token) {
        logger.debug("generating signature...");
        String baseString = api.getBaseStringExtractor().extract(request);
        String signature = api.getSignatureService().getSignature(baseString, apiSecret, token.getSecret());

        logger.debug("base string is: " + baseString);
        logger.debug("signature is: " + signature);
        return signature;
    }

    private void appendSignature(OAuthRequest request) {
        logger.debug("using Querystring signature");
        for (Entry<String, String> entry : request.getOauthParameters().entrySet()) {
            request.addQuerystringParameter(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String getRequestTokenUrl(String callback) {
        logger.debug("obtaining request token from " + api.getRequestTokenEndpoint());
        OAuthRequest request = new OAuthRequest(api.getRequestTokenVerb(), api.getRequestTokenEndpoint());

        logger.debug("setting oauth_callback to " + callback);
        request.addOAuthParameter(OAuthConstants.CALLBACK, callback);
        addOAuthParams(request, OAuthConstants.EMPTY_TOKEN);
        appendSignature(request);
        
        return request.getCompleteUrl();
    }

    @Override
    public String getSignedRequestUrl(Token accessToken, String apiEndpoint) {
        OAuthRequest request = prepareSignedRequest(accessToken, apiEndpoint);
        return request.getCompleteUrl();
    }

}
