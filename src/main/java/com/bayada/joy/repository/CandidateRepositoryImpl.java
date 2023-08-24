package com.bayada.joy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bayada.joy.domain.Candidate;
import com.bayada.joy.exception.CandidateNotFoundException;

@Repository
public class CandidateRepositoryImpl implements CandidateRepository {

  @Autowired
  private CandidateMongoRepository candidateMongoRepository;

  @Autowired
  private CandidateMapper mapper;


  @Override
  public Candidate createCandidate(Candidate candidate) {
    return mapper.toModel(candidateMongoRepository.save(mapper.toEntity(candidate)));
  }

  @Override
  public Candidate getCandidateById(String id) {
    return mapper.toModel(
        candidateMongoRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException()));
  }


  @Override
  public void updateCandidate(Candidate candidate) {

    CandidateEntity existingCandidate = candidateMongoRepository.findById(candidate.getId())
        .orElseThrow(() -> new CandidateNotFoundException());
    existingCandidate.setRole(candidate.getRole());
    existingCandidate.setFirstName(candidate.getFirstName());
    existingCandidate.setLastName(candidate.getLastName());
    existingCandidate.setEmail(candidate.getEmail());
    existingCandidate.setPhone(candidate.getPhone());
    existingCandidate.setPrimaryCommunication(candidate.getPrimaryCommunication());
    existingCandidate.setJobs(candidate.getJobs());
    existingCandidate.setScreeningQuestions(candidate.getScreeningQuestions());
    existingCandidate.setInterviewType(candidate.getInterviewType());
    existingCandidate.setInterviewEligibility(candidate.getInterviewEligibility());
    existingCandidate.setZip(candidate.getZip());
    existingCandidate.setAccountVerified(candidate.getAccountVerified());
    existingCandidate.setAppLanguagePreference(candidate.getAppLanguagePreference());
    candidateMongoRepository.save(existingCandidate);
  }

  @Override
  public void deleteById(String id) {
    candidateMongoRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException());
    candidateMongoRepository.deleteById(id);
  }

}
