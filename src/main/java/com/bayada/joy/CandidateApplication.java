package com.bayada.joy;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;


//@SpringBootApplication
//@ConfigurationPropertiesScan
//@EnableFeignClients
public class CandidateApplication {

  public static void main(String[] args) {
    //SpringApplication.run(CandidateApplication.class, args);
	  
	  Map<String, Integer> sortedMap = new TreeMap<>();
	  
	 sortedMap.put("Manimran123", 4);
	 sortedMap.put("Manimran456", 10);
	 sortedMap.put("Manimran234", 10);
	 sortedMap.put("Manimran312", 4);
	 sortedMap.put("Manimran124", 4);
	 
	 System.out.println(sortedMap);
  }

}
