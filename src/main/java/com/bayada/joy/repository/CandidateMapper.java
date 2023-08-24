package com.bayada.joy.repository;

import java.util.UUID;
import org.springframework.stereotype.Component;
import com.bayada.joy.domain.Candidate;


@Component
public class CandidateMapper {

  public CandidateEntity toEntity(Candidate candidate) {
    return CandidateEntity.builder().id(UUID.randomUUID().toString().split("-")[0])
        .role(candidate.getRole()).firstName(candidate.getFirstName())
        .lastName(candidate.getLastName()).email(candidate.getEmail()).phone(candidate.getPhone())
        .primaryCommunication(candidate.getPrimaryCommunication()).jobs(candidate.getJobs())
        .screeningQuestions(candidate.getScreeningQuestions())
        .interviewEligibility(candidate.getInterviewEligibility())
        .interviewType(candidate.getInterviewType()).zip(candidate.getZip())
        .accountVerified(candidate.getAccountVerified())
        .appLanguagePreference(candidate.getAppLanguagePreference()).build();
  }

  public Candidate toModel(CandidateEntity entity) {
    return Candidate.builder().id(entity.getId()).role(entity.getRole())
        .firstName(entity.getFirstName()).lastName(entity.getLastName()).email(entity.getEmail())
        .phone(entity.getPhone()).primaryCommunication(entity.getPrimaryCommunication())
        .jobs(entity.getJobs()).interviewEligibility(entity.getInterviewEligibility())
        .interviewType(entity.getInterviewType()).screeningQuestions(entity.getScreeningQuestions())
        .zip(entity.getZip()).accountVerified(entity.getAccountVerified())
        .appLanguagePreference(entity.getAppLanguagePreference()).build();
  }

}
