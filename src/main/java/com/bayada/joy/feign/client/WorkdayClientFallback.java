package com.bayada.joy.feign.client;

import java.util.Map;

import com.bayada.joy.domain.TokenDto;
import com.bayada.joy.domain.WorkdayProspect;

public class WorkdayClientFallback implements WorkdayClient {

	@Override
	public TokenDto getAccessToken(Map<String, ?> params) {
		return new TokenDto();
	}

	@Override
	public Object createProspect(String token, WorkdayProspect prospect) {
		return new Object();
	}

	@Override
	public Object getProspect(String bearerToken, String id) {
		return new Object();
	}

}
