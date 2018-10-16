package org.nvisia.openliberty.health;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.security.cert.X509Certificate;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Health
@ApplicationScoped
public class SystemHealth implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		HealthCheckResponseBuilder builder = HealthCheckResponse.named(SystemHealth.class.getSimpleName());

		boolean pass = true;
		
		builder.withData("is chuck down, dwight?", "false, chuck never goes down");
		
		builder.withData("Best Chuck Norris joke", "Jesus may walk on water, but Chuck Norris can swim through land");

		String serverName = System.getProperty("wlp.server.name");
		pass = serverName.equals("chuck");
		builder.withData("server name", "is " + serverName);
		
		try {
			HttpClient client = getClient();
			HttpResponse response = client.execute(new HttpGet("https://localhost:8443"));
			pass = response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
			
			builder.withData("chuck-service context returned", response.getStatusLine().getStatusCode());
		} catch (IOException e) {
			pass = false;
			builder.withData("chuck-service get resulted in exception", e.getMessage());
			e.printStackTrace(System.err);
		}

		if (pass) {
			builder.up();
		} else {
			builder.down();
		}

		return builder.build();

	}

	/*
	 * Kindly ignore this mess.  I didn't want to deal with getting the self-signed
	 * cert to pass the default cert validation, so I disabled it instead.
	 */
	@SuppressWarnings("deprecation")
	private HttpClient getClient() {
		try {
			HttpClientBuilder builder = HttpClientBuilder.create();

			builder.setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
					return true;
				}
			}).build());

			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
					new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
						@Override
						public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
								throws CertificateException {
							return true;
						}
					}).build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			builder.setConnectionManager(new PoolingHttpClientConnectionManager(RegistryBuilder
					.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory())
					.register("https", sslSocketFactory).build()));

			return builder.build();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		}
	}

}