package ro.nexttech.internship.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import ro.nexttech.internship.dto.UserDto;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerTest {

    //TODO mock service
    @LocalServerPort
    int serverPort;

    @Test
   public void testAddUserSuccess () throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + serverPort + "/rest/users";
        URI uri = new URI(baseUrl);

        UserDto userDto = new UserDto();
        userDto.setUserId(6);
        userDto.setUserName("Alex");
        userDto.setUserPassword("passalex");
        userDto.setFirmId(1);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, userDto, String.class);

        //Verify request succeed
        Assert.assertEquals(201, result.getStatusCodeValue());
    }
}