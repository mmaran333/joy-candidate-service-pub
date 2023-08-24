package com.bayada.joy.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * The Class BaseMockController.
 * 
 * @author Bhagyashree.P
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BaseMockController.class})
public class BaseMockController {

  /** The mock mvc. */
  protected MockMvc mockMvc;

  /**
   * Setup.
   */
  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Builds the standalone mock mvc.
   *
   * @param controllers the controllers
   */
  protected void buildStandaloneMockMvc(Object... controllers) {
    this.mockMvc = MockMvcBuilders.standaloneSetup(controllers).build();
  }

}
