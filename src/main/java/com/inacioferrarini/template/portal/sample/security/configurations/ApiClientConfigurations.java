package com.inacioferrarini.template.portal.sample.security.configurations;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiClientConfigurations {

	@Value("${api.base.url.https}")
	private String apiUrl;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder)
		throws NoSuchAlgorithmException, KeyManagementException {

		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() {

				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}

				public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs,
					String authType
				) {
				}

				public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs,
					String authType
				) {
				}

			}
		};
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		CloseableHttpClient httpClient = HttpClients.custom()
			.setSSLContext(sslContext)
			.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
			.build();
		HttpComponentsClientHttpRequestFactory customRequestFactory = new HttpComponentsClientHttpRequestFactory();
		customRequestFactory.setHttpClient(httpClient);
		
		return builder
			.rootUri(apiUrl)
			.requestFactory(() -> customRequestFactory).build();
	}

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
////		builder
////			.rootUri(apiUrl)
////			.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
////			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//		ignoreCertificates();
//		return builder.build();
//	}

}
