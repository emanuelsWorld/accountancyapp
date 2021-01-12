//package ro.nexttech.internship.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import ro.nexttech.internship.domain.Firm;
//import ro.nexttech.internship.domain.User;
//
//import static java.nio.file.Paths.get;

//@RunWith(SpringRunner.class)
//@WebMvcTest(UsersController.class)
//class UsersControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//   @Autowired
//    private UserRepository userRepository;
//
//   @Autowired
//   private FirmRepository firmRepository;
//
//
//
//    @Test
//    void searchUsers() {
//        Firm firm = new Firm();
//        firm.setFirmId(1);
//        firm.setFirmName("TestFirm");
//        User user = new User();
//        user.setUserId(3);
//        user.setUserName("UserName");
//        user.setFirm(firm);
//        firmRepository.save(firm);
//        firmRepository.flush();
//        userRepository.save(user);
//        userRepository.flush();
//        String search = "?search=userName:UserName,userId>2";
//
//    }
//}