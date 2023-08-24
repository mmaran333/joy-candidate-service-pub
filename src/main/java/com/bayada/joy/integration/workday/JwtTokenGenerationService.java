package com.bayada.joy.integration.workday;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenGenerationService {

	private static final String KEYSTORE_INSTANCE = "JKS";
	
	
	@Value("${integration.workday.isu-username}")
	private String isuUserName;
	
	@Value("${integration.workday.client-id}")
	private String workdayClientId;
	
	@Value("${integration.workday.keystore.password}")
	private String keystorePassword;
	
	@Value("${integration.workday.keystore.alias}")
	private String keystoreAlias;
	
	@Value("${integration.workday.jwt-bearer-grant.jks}")
	private String jwtBearerJKSLocation;
	
	public String constructJwtToken() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		
		KeyStore keystore = KeyStore.getInstance(KEYSTORE_INSTANCE);
		
		ClassPathResource cpr = new ClassPathResource(jwtBearerJKSLocation);
		
		try (InputStream fileInputStream = cpr.getInputStream()) {
			keystore.load(fileInputStream, keystorePassword.toCharArray());
		}
		PrivateKey privateKey = (PrivateKey) keystore.getKey(keystoreAlias, keystorePassword.toCharArray());
		return generateJWTToken(privateKey, keystore.getCertificate(keystoreAlias));
	}

	private String generateJWTToken(PrivateKey privateKey, Certificate certificate) {
		
		JwtBuilder jwtBuilder = Jwts.builder();
        
        jwtBuilder.setHeaderParam("alg", "RS256");
        jwtBuilder.setHeaderParam("typ", "JWT");
        
        jwtBuilder.claim("iss", workdayClientId);
        jwtBuilder.claim("sub", isuUserName);
        jwtBuilder.claim("aud", "wd");
        jwtBuilder.claim("exp", Long.toString((System.currentTimeMillis()/1000 ) + 300));

        // Sign the token using the private key
        jwtBuilder.signWith(SignatureAlgorithm.RS256, (PrivateKey) privateKey);

        // Generate the JWT token
        return jwtBuilder.compact();
	}
	
}
