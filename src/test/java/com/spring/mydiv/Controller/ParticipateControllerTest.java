package com.spring.mydiv.Controller;

import com.spring.mydiv.Service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParticipateControllerTest {

    @Autowired(required=true)
    private ParticipateController participateController;

    @Test
    @Commit
    @DisplayName("회원 초대")
    void createPerson2Travel() {
        //given
        int travelId = 57;
        Map<String, Object> map = new HashMap<>();
        map.put("user_email", "star4007lg@gm.gist.ac.kr");

        //when
        String answer = participateController.createPerson2Travel(travelId, map);

        //then
        System.out.println(answer);
    }
}