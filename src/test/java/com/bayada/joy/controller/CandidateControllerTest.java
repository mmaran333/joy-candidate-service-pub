package com.bayada.joy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.bayada.joy.common.BaseMockController;
import com.bayada.joy.common.TestUtils;
import com.bayada.joy.constant.InterviewEligibility;
import com.bayada.joy.constant.InterviewType;
import com.bayada.joy.constant.PrimaryCommunication;
import com.bayada.joy.constant.Role;
import com.bayada.joy.domain.Candidate;
import com.bayada.joy.domain.ScreeningQuestion;
import com.bayada.joy.service.CandidateService;


/**
 * The Class CandidateControllerTest.
 * 
 * @author Bhagyashree.P
 */
public class CandidateControllerTest extends BaseMockController {

  private static final String CANDIDATE_URL = "/v1/candidates";
  private static final String GET_CANDIDATE_BY_ID_URL = "/v1/candidates/Test";
  private static final String DELETE_CANDIDATE_BY_ID_URL = "/v1/api/candidate/Test";


  /** The candidate controller. */
  @InjectMocks
  CandidateController candidateController;

  /** The candidate service. */
  @Mock
  CandidateService candidateService;

  /**
   * Inits the.
   */
  @BeforeEach
  public void init() {
    super.buildStandaloneMockMvc(candidateController);
  }

  /**
   * Gets the candidate by id test.
   *
   * @return the candidate by id test
   * @throws Exception the exception
   */
  @Test
  public void getCandidateByIdTest() throws Exception {
    Candidate candidate = generateCandidateObject();
    String json = TestUtils.convertObjectToJson(candidate);
    Mockito.when(candidateService.getById(Mockito.anyString())).thenReturn(candidate);
    mockMvc.perform(get(GET_CANDIDATE_BY_ID_URL).contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
  }

  /**
   * Creates the candidate.
   *
   * @throws Exception the exception
   */
  @Test
  public void createCandidateTest() throws Exception {
    Candidate candidate = generateCandidateObject();
    String json = TestUtils.convertObjectToJson(candidate);
    Mockito.when(candidateService.createCandidate(Mockito.any())).thenReturn(candidate);
    mockMvc.perform(post(CANDIDATE_URL).contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
  }

  /**
   * Creates the candidate object.
   *
   * @return the candidate
   */
  private Candidate generateCandidateObject() {
    return Candidate.builder().id("Test").email("TestCandidate@gmail.com").firstName("kichu")
        .lastName("Smith").phone("1234567890").primaryCommunication(PrimaryCommunication.EMAIL)
        .jobs(Arrays.asList("Job A")).screeningQuestions(Arrays.asList(new ScreeningQuestion()))
        .interviewType(InterviewType.CALL).interviewEligibility(InterviewEligibility.NO)
        .zip("12345").role(Role.CANDIDATE).build();
  }

  /**
   * Delete candidate by id test.
   *
   * @throws Exception the exception
   */
  @Test
  public void deleteCandidateByIdTest() throws Exception {
    mockMvc.perform(delete(DELETE_CANDIDATE_BY_ID_URL).contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));
  }

  /**
   * Update candidate test.
   *
   * @throws Exception the exception
   */
  @Test
  public void updateCandidateTest() throws Exception {
    ResponseEntity<HttpStatus> entity = new ResponseEntity<>(HttpStatus.OK);
    String json = TestUtils.convertObjectToJson(entity);
    mockMvc.perform(patch(CANDIDATE_URL).contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isNoContent());
  }

}
