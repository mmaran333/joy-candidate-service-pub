package com.bayada.joy.domain;

import java.util.List;
import com.bayada.joy.constant.AppLanguagePreference;
import com.bayada.joy.constant.InterviewEligibility;
import com.bayada.joy.constant.InterviewType;
import com.bayada.joy.constant.PrimaryCommunication;
import com.bayada.joy.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

  private String id;
  private Role role;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private PrimaryCommunication primaryCommunication;
  private List<String> jobs;
  private List<ScreeningQuestion> screeningQuestions;
  private InterviewType interviewType;
  private InterviewEligibility interviewEligibility;
  private String zip;
  private Boolean accountVerified;
  private AppLanguagePreference appLanguagePreference;

}
