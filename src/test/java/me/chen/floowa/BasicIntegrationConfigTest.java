package me.chen.floowa;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicIntegrationConfigTest {

    TestRestTemplate testRestTemplate;
    URL base;
    @LocalServerPort int port;

    @Before
    public void setUp() throws MalformedURLException{
        testRestTemplate = new TestRestTemplate("user", "password");
        base = new URL("http://localhost:" + port);

    }

    public void whenLoggedUserRequestsHomePage_ThenSuccess() throws IllegalStateException, IOException {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().contains("home"));
    }
}
