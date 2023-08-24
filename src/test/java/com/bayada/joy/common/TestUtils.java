package com.bayada.joy.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * The Class TestUtils.
 * 
 * @author Bhagyashree.P
 */
public class TestUtils {

  /**
   * Convert object to json.
   *
   * @param object the object
   * @return the string
   * @throws JsonProcessingException the json processing exception
   */
  public static String convertObjectToJson(Object object) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(object);
    return json;
  }

}
