package com.bayada.joy.repository;


import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.bayada.joy.audit.Auditable;
import com.bayada.joy.constant.AppLanguagePreference;
import com.bayada.joy.constant.InterviewEligibility;
import com.bayada.joy.constant.InterviewType;
import com.bayada.joy.constant.PrimaryCommunication;
import com.bayada.joy.constant.Role;
import com.bayada.joy.domain.ScreeningQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "candidate")
public class CandidateEntity extends Auditable<String> {

  @Id
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
