package br.com.compasso.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import br.com.compasso.teste.model.Cliente;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ClienteTests {
	
	@LocalServerPort
	int randomServerPort;
	
	@Test
	public void testNomeObrigatorio() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setCidade("Recife");
		cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
		cliente.setIdade(30);
		cliente.setSexo("MASCULINO");
		HttpHeaders headers = new HttpHeaders();
		try {
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    restTemplate.postForEntity(uri, request, Cliente.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
	        assertEquals(true, ex.getResponseBodyAsString().contains("Nome obrigatorio"));
		}
	}
	
	@Test
	public void testCidadeObrigatoria() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Lucas");
		cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
		cliente.setIdade(30);
		cliente.setSexo("MASCULINO");
		HttpHeaders headers = new HttpHeaders();
		try {
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    restTemplate.postForEntity(uri, request, Cliente.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
	        assertEquals(true, ex.getResponseBodyAsString().contains("Cidade obrigatoria"));
		}
	}
	
	@Test
	public void testSexoObrigatorio() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Pedro");
		cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
		cliente.setIdade(30);
		cliente.setCidade("Olinda");
		HttpHeaders headers = new HttpHeaders();
		try {
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    restTemplate.postForEntity(uri, request, Cliente.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
	        assertEquals(true, ex.getResponseBodyAsString().contains("Sexo obrigatorio"));
		}
	}
	
	@Test
	public void testDataObrigatoria() throws URISyntaxException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Pedro");
		cliente.setIdade(30);
		cliente.setCidade("Olinda");
		cliente.setSexo("MASCULINO");
		HttpHeaders headers = new HttpHeaders();
		try {
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    restTemplate.postForEntity(uri, request, Cliente.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
	        assertEquals(true, ex.getResponseBodyAsString().contains("Data obrigatoria"));
		}
	}
	
	@Test
	public void testIdadeObrigatoria() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Pedro");
		cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
		cliente.setCidade("Olinda");
		cliente.setSexo("MASCULINO");
		HttpHeaders headers = new HttpHeaders();
		try {
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    restTemplate.postForEntity(uri, request, Cliente.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			System.out.println(ex.getResponseBodyAsString());
	        assertEquals(true, ex.getResponseBodyAsString().contains("Idade Obrigatoria e maior que 0"));
		}
	}
	
	@Test
	public void testIdadeInvalida() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Pedro");
		cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
		cliente.setCidade("Olinda");
		cliente.setSexo("MASCULINO");
		cliente.setIdade(0);
		HttpHeaders headers = new HttpHeaders();
		try {
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    restTemplate.postForEntity(uri, request, Cliente.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
	        assertEquals(true, ex.getResponseBodyAsString().contains("Idade Obrigatoria e maior que 0"));
		}
	}
	
	@Test
	public void testSexoMasculino() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Joao");
		cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
		cliente.setCidade("Olinda");
		cliente.setSexo("MASCULINO");
		cliente.setIdade(30);
		HttpHeaders headers = new HttpHeaders();
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    ResponseEntity<Cliente> clienteResponse =restTemplate.postForEntity(uri, request, Cliente.class);
	    assertEquals(201, clienteResponse.getStatusCode().value());
	    
	}
	
	@Test
	public void testSexoFeminino() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Luana");
		cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
		cliente.setCidade("Olinda");
		cliente.setSexo("FEMININO");
		cliente.setIdade(30);
		HttpHeaders headers = new HttpHeaders();
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    ResponseEntity<Cliente> clienteResponse =restTemplate.postForEntity(uri, request, Cliente.class);
	    assertEquals(201, clienteResponse.getStatusCode().value());
	    
	}
	
	@Test
	public void testSexoInvalido() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Pedro");
		cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
		cliente.setIdade(30);
		cliente.setCidade("Olinda");
		cliente.setSexo("Outros");
		HttpHeaders headers = new HttpHeaders();
		try {
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    restTemplate.postForEntity(uri, request, Cliente.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
	        assertEquals(true, ex.getResponseBodyAsString().contains("Valor nao permitido escreva masculino ou feminino"));
		}
	}
	
	@Test
	public void testDataInvalida() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Pedro");
		cliente.setDataNascimento(new SimpleDateFormat("dd-MM-yyyy").parse("11-11-1994"));
		cliente.setIdade(30);
		cliente.setCidade("Olinda");
		cliente.setSexo("Outros");
		HttpHeaders headers = new HttpHeaders();
		try {
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    restTemplate.postForEntity(uri, request, Cliente.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Formato de data invalido: dd/MM/yyyy"));
		}
	}
	
	@Test
	public void testCidadeNotFound() throws URISyntaxException, ParseException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/cliente";
	    URI uri = new URI(baseUrl);
	    RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new Cliente();
		cliente.setNome("Pedro");
		cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
		cliente.setIdade(30);
		cliente.setCidade("Sao Paulo");
		cliente.setSexo("MASCULINO");
		HttpHeaders headers = new HttpHeaders();
		try {
	    HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);
	    restTemplate.postForEntity(uri, request, Cliente.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Cidade nao cadastrada"));
		}
	}
	
	 @Test 
	  public void consultarClientePorNome() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cliente/nome/Joao";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<Cliente> clienteResponse = restTemplate.getForEntity(uri, Cliente.class);
		    assertEquals(200, clienteResponse.getStatusCode().value());
	  }
	  
	  @Test 
	  public void consultarClientePorNomeNotFound() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cidade/nome/NNNNNN";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    try {
		    restTemplate.getForEntity(uri, Cliente.class);
		    }catch(HttpClientErrorException ex) {
				assertEquals(404, ex.getRawStatusCode());
			}
	  }
	  
	  @Test 
	  public void consultarClientePorId() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cliente/1";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<Cliente> clienteResponse = restTemplate.getForEntity(uri, Cliente.class);
		    assertEquals(200, clienteResponse.getStatusCode().value());
	  }
	  
	  @Test 
	  public void consultarClientePorIdNotFound() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cliente/99";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    try {
		    	restTemplate.getForEntity(uri, Cliente.class);
		    }catch (HttpClientErrorException ex) {
		    	assertEquals(404, ex.getRawStatusCode());
			}
	  }
	  
	  @Test
	  public void deleteCliente() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cliente/10";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    HttpHeaders headers = new HttpHeaders();
		    
		    HttpEntity<Object> request = new HttpEntity<Object>(headers);
		    ResponseEntity<Cliente> clienteResponse =restTemplate.exchange(uri,HttpMethod.DELETE,request,Cliente.class);
		    assertEquals(200, clienteResponse.getStatusCode().value());
		    
		  
	  }
	  
	  @Test
	  public void deleteClienteNotFound() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cliente/99";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    try {
		    restTemplate.delete(uri);
		    }catch(HttpClientErrorException ex) {
		    	assertEquals(404, ex.getRawStatusCode());
		    }
		  
	  }
	  
	  @Test
	  public void updateCliente() throws URISyntaxException, ParseException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cliente/1";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    HttpHeaders headers = new HttpHeaders();
		    Cliente cliente = new Cliente();
			cliente.setNome("Luis");
			cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
			cliente.setCidade("Olinda");
			cliente.setSexo("MASCULINO");
			cliente.setIdade(30);
		    
		    HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente,headers);
		    ResponseEntity<Cliente> clienteResponse =restTemplate.exchange(uri,HttpMethod.PUT,request,Cliente.class);
		    assertEquals(200, clienteResponse.getStatusCode().value());
		    
		  
	  }
	  
	  @Test
	  public void updateClienteNotFound() throws URISyntaxException, ParseException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/cliente/99";
		    URI uri = new URI(baseUrl);
		    RestTemplate restTemplate = new RestTemplate();
		    Cliente cliente = new Cliente();
			cliente.setNome("Luis");
			cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1994"));
			cliente.setCidade("Olinda");
			cliente.setSexo("MASCULINO");
			cliente.setIdade(30);
			try {
			restTemplate.put(uri, cliente);
			}catch(HttpClientErrorException ex) {
				assertEquals(404, ex.getRawStatusCode());
			}
		    
		  
	  }
}
