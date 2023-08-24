package com.bayada.joy.feign.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.bayada.joy.config.FeignConfiguration;
import com.bayada.joy.domain.TokenDto;
import com.bayada.joy.domain.WorkdayProspect;

@FeignClient(value = "workday", url = "${integration.workday.endpoint}", configuration = FeignConfiguration.class, fallback = WorkdayClientFallback.class)
public interface WorkdayClient {
	
	@PostMapping(value = "/oauth2/ust_dpt1/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public TokenDto getAccessToken(Map<String, ?> params);
	
	@PostMapping(value = "/api/recruiting/v2/ust_dpt1/prospects")
	public Object createProspect(@RequestHeader("Authorization") String bearerToken, @RequestBody WorkdayProspect prospect);
	
	@GetMapping(value = "/api/recruiting/v2/ust_dpt1/prospects/{id}")
	public Object getProspect(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") String id);
	
}
