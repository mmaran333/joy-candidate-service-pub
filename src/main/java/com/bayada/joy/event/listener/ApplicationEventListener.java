package com.bayada.joy.event.listener;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bayada.joy.domain.TokenDto;
import com.bayada.joy.feign.client.WorkdayClient;
import com.bayada.joy.integration.workday.JwtTokenGenerationService;
import com.bayada.joy.integration.workday.TokenHolder;

@Component
public class ApplicationEventListener {
	
	public static final String ASSERTION = "assertion";
	
	public static final String GRANT_TYPE = "grant_type";
	
	public static final String GRANT_TYPE_VALUE = "urn:ietf:params:oauth:grant-type:jwt-bearer";
	
	@Autowired
	private WorkdayClient workdayClient;
	
	@Autowired
	private JwtTokenGenerationService jwtTokenGenerationService;

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady(ApplicationReadyEvent event) {
		
		try {
			String jwtAccessToken = this.jwtTokenGenerationService.constructJwtToken();
			TokenDto tokenDto = this.workdayClient.getAccessToken(Map.of(ASSERTION, jwtAccessToken, GRANT_TYPE, GRANT_TYPE_VALUE));
			TokenHolder tokenHolder = new TokenHolder(tokenDto.getAccessToken(), tokenDto.getTokenType());
			event.getApplicationContext().getBeanFactory().registerSingleton("tokenHolder", tokenHolder);
		} catch (Exception e) {
			throw new RuntimeException("An exception has occurred during the creation of access token", e);
		}
	}
	
}
