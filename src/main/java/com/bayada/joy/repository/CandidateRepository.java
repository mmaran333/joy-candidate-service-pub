package com.bayada.joy.repository;

import com.bayada.joy.domain.Candidate;

public interface CandidateRepository {

  public Candidate createCandidate(Candidate candidate);

  public Candidate getCandidateById(String id);

  public void deleteById(String id);

  public void updateCandidate(Candidate candidate);

}
