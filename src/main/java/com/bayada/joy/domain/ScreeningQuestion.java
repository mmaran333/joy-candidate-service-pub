package com.bayada.joy.domain;

import java.util.List;
import lombok.Data;

@Data
public class ScreeningQuestion {
  private String Question;
  private List<String> answers;
}
