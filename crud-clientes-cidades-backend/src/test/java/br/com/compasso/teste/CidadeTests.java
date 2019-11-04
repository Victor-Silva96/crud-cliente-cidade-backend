package br.com.compasso.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;


import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import br.com.compasso.teste.model.Cidade;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CidadeTests {
	
	@LocalServerPort
	int randomServerPort;

	@Test 
	  public void testeNomeObrigatorio() throws URISyntaxException { 
			final String baseUrl = "http://localhost:"+randomServerPort+"/cidade";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
			Cidade cidade = new Cidade();
			cidade.setEstado("Pernambuco");
			HttpHeaders headers = new HttpHeaders();
			try {
		    HttpEntity<Cidade> request = new HttpEntity<>(cidade, headers);
		    restTemplate.postForEntity(uri, request, Cidade.class);
			}catch(HttpClientErrorException ex) {
				assertEquals(400, ex.getRawStatusCode());
		        assertEquals(true, ex.getResponseBodyAsString().contains("Nome obrigatorio"));
			}
	  }
	
	
	  @Test 
	  public void testeEstadoObrigatorio() throws URISyntaxException { 
			final String baseUrl = "http://localhost:"+randomServerPort+"/cidade";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
			Cidade cidade = new Cidade();
			cidade.setNome("Recife");
			HttpHeaders headers = new HttpHeaders();
			try {
		    HttpEntity<Cidade> request = new HttpEntity<>(cidade, headers);
		    restTemplate.postForEntity(uri, request, Cidade.class);
			}catch(HttpClientErrorException ex) {
				assertEquals(400, ex.getRawStatusCode());
				System.out.println(ex.getResponseBodyAsString());
		        assertEquals(true, ex.getResponseBodyAsString().contains("Estado obrigatorio"));
			}
	  }
	  
	  @Test 
	  public void testeObjetoCidadeNull() throws URISyntaxException{ 
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cidade";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
			Cidade cidade = null;
			HttpHeaders headers = new HttpHeaders();
			try {
		    HttpEntity<Cidade> request = new HttpEntity<>(cidade, headers);
		    restTemplate.postForEntity(uri, request, Cidade.class);
			}catch(HttpClientErrorException ex) {
				assertEquals(415, ex.getRawStatusCode());
			}
	  }
	  
	  @Test 
	  public void testeObjetoCidadeEmpty() throws URISyntaxException{ 
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cidade";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
			Cidade cidade = new Cidade();
			HttpHeaders headers = new HttpHeaders();
			try {
		    HttpEntity<Cidade> request = new HttpEntity<>(cidade, headers);
		    restTemplate.postForEntity(uri, request, Cidade.class);
			}catch(HttpClientErrorException ex) {
				assertEquals(400, ex.getRawStatusCode());
			}
	  }
	  
	  @Test
	  public void cadastrarCidade() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cidade";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
			Cidade cidade = new Cidade();
			cidade.setEstado("PE");
			cidade.setNome("Olinda");
			HttpHeaders headers = new HttpHeaders();
			
		    HttpEntity<Cidade> request = new HttpEntity<>(cidade, headers);
		    ResponseEntity<Cidade> cidadeResponse = restTemplate.postForEntity(uri, request, Cidade.class);
		    assertEquals(201, cidadeResponse.getStatusCode().value());
			
	  }
	  
	  @Test 
	  public void consultarCidadePorNome() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cidade/nome/Gravata";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<Cidade> cidadeResponse = restTemplate.getForEntity(uri, Cidade.class);
		    assertEquals(200, cidadeResponse.getStatusCode().value());
	  }
	  
	  @Test 
	  public void consultarCidadePorNomeNotFound() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cidade/nome/NNNNNN";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    try {
		    restTemplate.getForEntity(uri, Cidade.class);
		    }catch(HttpClientErrorException ex) {
				assertEquals(404, ex.getRawStatusCode());
			}
	  }
	  
	  @Test 
	  public void consultarCidadePorEstado() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cidade/estado/PE";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<String> cidadeResponse = restTemplate.getForEntity(uri, String.class);
		    assertEquals(200, cidadeResponse.getStatusCode().value());
	  }
	  
	  @Test 
	  public void consultarCidadePorEstadoNotFound() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cidade/estado/NNNNNN";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    try {
		    restTemplate.getForEntity(uri, Cidade.class);
		    }catch(HttpClientErrorException ex) {
				assertEquals(404, ex.getRawStatusCode());
			}
	  }
	  
	  
	  
	  
	 
}
