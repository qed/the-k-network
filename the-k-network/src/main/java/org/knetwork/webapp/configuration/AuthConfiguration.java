package org.knetwork.webapp.configuration;

import org.knetwork.webapp.oauth.KhanOAuth10ServiceImpl;
import org.knetwork.webapp.oauth.KhanOAuthService;
import org.knetwork.webapp.util.KhanAcademyApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AuthConfiguration {
	
	@Bean
	public KhanAcademyApi khanAcademyScribeApi() {
		return new KhanAcademyApi();
	}
	
	@Bean
	public KhanOAuthService oauthService(KhanAcademyApi api, @Value("${oauth.consumer.key}") String key, @Value("${oauth.consumer.secret}") String secret) {
		return new KhanOAuth10ServiceImpl(api, key, secret);
	}
}
