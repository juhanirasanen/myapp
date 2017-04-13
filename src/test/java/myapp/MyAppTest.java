package myapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MyAppTest {
	private TestRestTemplate restTemplate;
    public MyAppTest() {
    	restTemplate = new TestRestTemplate();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    	
    }
    
    @After
    public void tearDown() {
    }

    //this is a dummy test just testing that the test are run
    @Test
    public void hello() {
        assertEquals(1, 1);
    }
    
    //This is just an example test that will always work
    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void testByCallingControllersMethod() {
    	MyController mc = new MyController();
    	String answer = mc.test("");
    	assertEquals("{\"id\":123}",answer);
    }
    
    @Test
    public void testViaRestUsingHttpClient() throws ClientProtocolException, IOException {
    	String url = "http://localhost:8080/test";
    	HttpClient client = HttpClientBuilder.create().build();
    	HttpGet request = new HttpGet(url);
    	HttpResponse response = client.execute(request);
    	System.out.println("Response Code : "
    	                + response.getStatusLine().getStatusCode());
    	BufferedReader rd = new BufferedReader(
    		new InputStreamReader(response.getEntity().getContent()));
    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    		result.append(line);
    	}    	
    	assertEquals("{\"id\":124}",result.toString());
    }    
    
    @Test
    public void testViaRestUsingRestTempate() { 
  		Hello hello = restTemplate.getForObject("http://localhost:8080/test", Hello.class);
		Long l = new Long(123);
		assertEquals(l, hello.getId());
    }
    //https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-tomcat/src/test/java/sample/tomcat/SampleTomcatApplicationTests.java
	@Test
	public void testTHatHttpStatusOkAndBodyCOrrect() throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8080/test", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("{\"id\":123}");
	}    
}
