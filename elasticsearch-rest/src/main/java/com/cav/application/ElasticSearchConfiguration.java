package com.cav.application;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@Configuration
public class ElasticSearchConfiguration {

	/**
	 * configure the host and port on which the elastic search is running on
	 * @return
	 */
	/*
	 * Elastic search version 6
	 */
	@Bean
	public RestClient getRestClient() {
		RestClient restClient = RestClient.builder(new HttpHost("elasticsearch",9200)).build();
		return restClient;
	}
	
	/*
	@Bean
	public RestClient getRestClient() {
		 final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "Y45f41ogIaxG592zr22u4XPv"));

	        HttpHost host = new HttpHost("elasticsearch", 9200);

	        RestClient restClient = RestClient.builder(host)
	                .setHttpClientConfigCallback(new HttpClientConfigCallback() {

	                    @Override
	                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
	                        // TODO Auto-generated method stub
	                        httpClientBuilder.disableAuthCaching();
	                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
	                    }
	                }).build();
	        return restClient;
	}*/
	
	
	@Bean
	public ElasticsearchTransport getElasticsearchTransport() {
		return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
	}
	
	@Bean
	public ElasticsearchClient getElasticsearchClient() {
		ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
		return client;
	}
}
