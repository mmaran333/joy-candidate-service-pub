package com.bayada.joy.config;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import feign.Client;
import feign.Feign;
import feign.codec.Encoder;
import feign.form.FormEncoder;

@Configuration
public class FeignConfiguration {
	
	@Value("${integration.workday.pem}")
	private String workdayPem;
	
	@Autowired
	private ObjectFactory<HttpMessageConverters> messageConverters;
	
	@Bean
	@Primary
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Encoder feignFormEncoder() {
	  return new FormEncoder(new SpringEncoder(this.messageConverters));
	}
	
	@Bean
    public Feign.Builder feignBuilder() throws NoSuchAlgorithmException, KeyStoreException, FileNotFoundException, CertificateException, IOException, KeyManagementException {
		
		TrustManagerFactory tmf = 
				  TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				tmf.init(getTrustStore());
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(null, tmf.getTrustManagers(), null);
				SSLSocketFactory sslFactory = ctx.getSocketFactory();
		
        Client trustSSLSockets = new Client.Default(sslFactory, new NoopHostnameVerifier());

        return Feign.builder().client(trustSSLSockets);
    }
	
	private KeyStore getTrustStore() throws FileNotFoundException, IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
		
		ClassPathResource cpr = new ClassPathResource(workdayPem);
	
		try (InputStream pemInputStream = cpr.getInputStream(); FileOutputStream keystoreOutputStream = new FileOutputStream("keystore.jks")) {
			
			byte[] pemBytes = pemInputStream.readAllBytes();

        // Create CertificateFactory and generate Certificate from PEM
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(pemBytes));

        // Create a new KeyStore
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);

        // Add the certificate to the KeyStore
        keyStore.setCertificateEntry("workday-truststore", certificate);

        keyStore.store(keystoreOutputStream, "secret".toCharArray());
        return keyStore;
		}
	}


}
