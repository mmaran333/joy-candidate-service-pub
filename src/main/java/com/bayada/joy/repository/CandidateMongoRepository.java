package com.bayada.joy.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidateMongoRepository extends MongoRepository<CandidateEntity, String> {

  Optional<CandidateEntity> findById(String id);

  void deleteById(String id);

}
