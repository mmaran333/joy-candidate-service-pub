package com.bayada.joy.integration.workday;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Component
@Data
@AllArgsConstructor
public class TokenHolder {

	private String accessToken;
	private String tokenType;
	
}
