package com.spring.mydiv.Service;

import com.spring.mydiv.Dto.UserDetailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;


@SpringBootTest
class UserServiceTest {
    @Autowired(required=true)
    private UserService userService;

//    @Test
//    @Commit
//    @DisplayName("회원가입")
//    void createUser() {
//        //given
//        UserCreateDto.Request request = UserCreateDto.Request.builder()
//                .Name("이하은")
//                .Email("12novel30@naver.com")
//                .Password("20205149")
//                .Account("000-000-00")
//                .build();
//        //when
//        UserCreateDto.Response response = userService.createUser(request);
//        //then
//        System.out.print("name = " + response.getName());
//        //fail("Not yet implemented"); // TODO
//    }
//
//    @Test
//    @Commit
//    @DisplayName("로그인")
//    void login() { //for ver2.
//        //given
//        UserCreateDto.Login login = UserCreateDto.Login.builder()
//                .Email("bittersweet141230@gmail.com")
//                .Password("20205149")
//                .build();
//        //when
//        String response = userService.login(login);
//
//        //then
//        System.out.println(response);
//        //fail("Not yet implemented"); // TODO
//    }

//    @Test
//    @Commit
//    @DisplayName("유저 정보 리턴")
//    void getUserInfo() {
//        //given
//        int userid = 4;
//
//        //when
//        UserDetailDto info = userService.getUserInfo(userid);
//
//        //then
//        System.out.println("Name: "+info.getName());
//        System.out.println("Email: "+info.getEmail());
//        System.out.println("Account: "+info.getAccount());
//    }

//    @Test
//    @Commit
//    @DisplayName("여행 리스트 리턴")
//    void getUserJoinedTravel() {
//        //given
//        //when
//        List<String> list = userService.getUserJoinedTravel(11);
//
//        //then
//        for (String n : list){
//            System.out.println("Name: "+n);
//        }
//    }

    @Test
    @Commit
    @DisplayName("유저 정보 리턴 + 여행리스트까지")
    void getUserInfo() {
        //given
        int userid = 11;

        //when
        UserDetailDto.WithTravel info = userService.getUserInfoWithTravel(userid);

        //then
        System.out.println("Name: "+info.getName());
        System.out.println("Email: "+info.getEmail());
        System.out.println("Account: "+info.getAccount());
        for(String travel : info.getTravelList()){
            System.out.println("Travel Name: "+travel);
        }
    }
}