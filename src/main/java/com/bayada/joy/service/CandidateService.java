package com.bayada.joy.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.bayada.joy.domain.Candidate;
import com.bayada.joy.domain.TokenDto;
import com.bayada.joy.domain.WorkdayProspect;
import com.bayada.joy.event.listener.ApplicationEventListener;
import com.bayada.joy.exception.CandidateNotFoundException;
import com.bayada.joy.feign.client.WorkdayClient;
import com.bayada.joy.integration.workday.JwtTokenGenerationService;
import com.bayada.joy.integration.workday.TokenHolder;
import com.bayada.joy.repository.CandidateRepository;

@Service
public class CandidateService {

  @Autowired
  private CandidateRepository repository;

  @Autowired
  private ConfigurableApplicationContext applicationContext;

  @Autowired
  private WorkdayClient workdayClient;

  @Autowired
  private JwtTokenGenerationService jwtTokenGenerationService;

  public Candidate createCandidate(Candidate candidate) {
    return repository.createCandidate(candidate);
  }

  public Object createProspect(WorkdayProspect candidate) {
    TokenHolder tokenHolder = this.applicationContext.getBean(TokenHolder.class);
    return this.workdayClient.createProspect(
        String.format("%s %s", tokenHolder.getTokenType(), tokenHolder.getAccessToken()),
        candidate);
  }

  public Candidate getById(String id) {
    return repository.getCandidateById(id);
  }

  public Object getProspectById(String id) {
    TokenHolder tokenHolder = this.applicationContext.getBean(TokenHolder.class);
    return this.workdayClient.getProspect(
        String.format("%s %s", tokenHolder.getTokenType(), tokenHolder.getAccessToken()), id);
  }


  public void update(Candidate payload) throws CandidateNotFoundException {
    repository.updateCandidate(payload);
  }

  public void refreshAccessToken() {
    try {
      String jwtAccessToken = this.jwtTokenGenerationService.constructJwtToken();
      TokenDto tokenDto = this.workdayClient
          .getAccessToken(Map.of(ApplicationEventListener.ASSERTION, jwtAccessToken,
              ApplicationEventListener.GRANT_TYPE, ApplicationEventListener.GRANT_TYPE_VALUE));
      TokenHolder tokenHolder = this.applicationContext.getBean(TokenHolder.class);
      tokenHolder.setAccessToken(tokenDto.getAccessToken());
    } catch (Exception e) {
      throw new RuntimeException("An exception has occurred during the creation of access token",
          e);
    }
  }

  public void deleteCandidateById(String id) {
    repository.deleteById(id);
  }

}
