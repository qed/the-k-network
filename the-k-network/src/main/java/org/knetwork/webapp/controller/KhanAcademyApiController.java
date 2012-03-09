package org.knetwork.webapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.oauth.KhanOAuthService;
import org.scribe.model.OAuthConstants;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KhanAcademyApiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final KhanOAuthService oAuthService;

    @Inject
    public KhanAcademyApiController(final KhanOAuthService service) {
        oAuthService = service;
    }

    /*
    @RequestMapping(OAuthConstants.CALLBACK)
    public String handleCallback(final HttpSession session, @RequestParam("oauth_token") final String token,
            @RequestParam("oauth_token_secret") final String secret, @RequestParam("oauth_verifier") final String verifier) {
        final Token requestToken = new Token(token, secret);
        logger.debug("Received request token: " + requestToken);
        final Token accessToken = oAuthService.getAccessToken(requestToken, new Verifier(verifier));
        session.setAttribute("accessToken", accessToken);
        logger.debug("Received access token:" + accessToken);
        return "redirect:/";
    }
    */

}
