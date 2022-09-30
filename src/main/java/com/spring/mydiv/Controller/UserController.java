package com.spring.mydiv.Controller;

import java.util.List;
import java.util.Map;

import com.spring.mydiv.Dto.*;
import com.spring.mydiv.Service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Service.TravelService;
import com.spring.mydiv.Service.UserService;

import lombok.RequiredArgsConstructor;

import static java.lang.Boolean.TRUE;

/**
 * @author 12nov
 */
//@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userservice;
    private final TravelService travelservice;
    private final PersonService personservice;

    @GetMapping("/halo") //just for test
    public String ddd() {
        return "He";
    }

    @PostMapping(value = "/Register") //ResponseEntity.ok
    public ResponseEntity<UserCreateDto.Response> createUser(@RequestBody Map map) {
        UserCreateDto.Request request = new UserCreateDto.Request(map.get("user_name").toString(),
                map.get("user_email").toString(),
                map.get("user_password").toString(),
                map.get("user_account").toString());
        return ResponseEntity.ok(userservice.createUser(request));
    }

    @PostMapping(value = "/login") //return int -> orElseThrow (?)
    public int login(@RequestBody Map map) {
        UserCreateDto.Login loginUser = new UserCreateDto.Login(map.get("input_id").toString(),
                map.get("input_password").toString());
        return userservice.login(loginUser);
        /**success -> return [user id]
         * wrong email -> -2
         * wrong pw -> -1
         */
    }

    @GetMapping("/{userId}") //return empty
    public UserDetailDto.WithTravel getUserInfo(@PathVariable int userId){
        return userservice.getUserInfoWithTravel(userId);
    }

    @PostMapping("/{userId}/createTravel") //return int -> orElseThrow (?)
    public int joinTravel(@PathVariable int userId, @RequestBody Map map){
        TravelCreateDto.Request travelRequest = new TravelCreateDto.Request(map.get("travel_name").toString());
        PersonCreateDto.Request personRequest = new PersonCreateDto.Request(
                userservice.getUserInfo(userId),
                travelservice.createTravel(travelRequest));
        if (ResponseEntity.ok(personservice.createPerson(personRequest, TRUE)).getStatusCodeValue() == 200)
            return personRequest.getTravel().getId().intValue();
        else return -1;
    }

    // 여행을 생성한 user가 여행 자체를 삭제하는 메소드
    @DeleteMapping("/{userId}/{travelId}/delete")
    public void deleteTravel(@PathVariable int travelId) {
        travelservice.deleteTravel(travelId);
    }
}