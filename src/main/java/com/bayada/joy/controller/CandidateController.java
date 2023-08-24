package com.bayada.joy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bayada.joy.domain.Candidate;
import com.bayada.joy.domain.WorkdayProspect;
import com.bayada.joy.service.CandidateService;

@RestController
@RequestMapping("v1/candidates")
public class CandidateController {

  @Autowired
  private CandidateService candidateService;

  @PostMapping
  public Candidate createCandidate(@RequestBody Candidate candidate) {
    return candidateService.createCandidate(candidate);
  }

  @PostMapping("/wd-integ/prospects")
  public Object createProspect(@RequestBody WorkdayProspect candidate) {
    return candidateService.createProspect(candidate);
  }

  @GetMapping("/wd-integ/prospects/{id}")
  public Object getProspectById(@PathVariable("id") String id) {
    return candidateService.getProspectById(id);
  }

  @GetMapping("/{id}")
  public Candidate getCandidateById(@PathVariable String id) {
    return candidateService.getById(id);
  }

  @PatchMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateCandidate(@RequestBody Candidate candidate) {
    candidateService.update(candidate);
  }

  @PostMapping("/wd-integ/access-tokens")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void refreshAccessToken() {
    this.candidateService.refreshAccessToken();
  }


  @DeleteMapping(value = "/{id}")
  public void deleteCandidateById(@PathVariable String id) {
    candidateService.deleteCandidateById(id);
  }

}
