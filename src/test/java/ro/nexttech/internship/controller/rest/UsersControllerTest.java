package ro.nexttech.internship.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.dto.UserDto;
import ro.nexttech.internship.filters.firms.FirmRepository;
import ro.nexttech.internship.filters.users.UserRepository;
import ro.nexttech.internship.filters.users.UserSpecificationBuilder;

import javax.servlet.ServletContext;

import java.awt.*;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @Autowired
    private UserRepository userRepository;

   @Autowired
   private FirmRepository firmRepository;



    @Test
    void searchUsers() {
        Firm firm = new Firm();
        firm.setFirmId(1);
        firm.setFirmName("TestFirm");
        User user = new User();
        user.setUserId(3);
        user.setUserName("UserName");
        user.setFirm(firm);
        firmRepository.save(firm);
        firmRepository.flush();
        userRepository.save(user);
        userRepository.flush();
        String search = "?search=userName:UserName,userId>2";

    }
}