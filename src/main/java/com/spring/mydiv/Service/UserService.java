package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import com.spring.mydiv.Dto.UserDetailDto;
import com.spring.mydiv.Entity.Person;
import org.springframework.stereotype.Service;

import com.spring.mydiv.Dto.TravelDto;
import com.spring.mydiv.Dto.UserCreateDto;
import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.PersonRepository;
import com.spring.mydiv.Repository.TravelRepository;
import com.spring.mydiv.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PersonRepository personRepository;
	private final TravelRepository travelRepository;
	
    @Transactional
    public UserCreateDto.Response createUser(UserCreateDto.Request request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .account(request.getAccount())
                .build();
        userRepository.save(user);
        return UserCreateDto.Response.fromEntity(user);
    }








    UserCreateDto.Response answer = null;
    String result = "";
//    public UserCreateDto.Response login(UserCreateDto.Login loginUser) { //ver1. return info
//    	Optional<User> info = userRepository.findByEmail(loginUser.getEmail());
//    	info.ifPresent(user ->
//    					{if (loginUser.getPassword().toString().equals(user.getPassword().toString())) {
//    						answer = UserCreateDto.Response.fromEntity(user);}
//    					}
//    					);
//    	return answer;
//    }
    public String login(UserCreateDto.Login loginUser) { //ver2. return id
        Optional<User> info = userRepository.findByEmail(loginUser.getEmail());
        info.ifPresentOrElse(user ->
                                {if (loginUser.getPassword().toString().equals(user.getPassword().toString())) {
                                    result = "Login Success!";}
                                else{result = "Wrong Password!";}},
                                    ()-> {if(loginUser.getEmail()!=null){result = "Wrong Email!";}}
        );
        return result;
    }

    public UserDetailDto getUserInfo(int no){
        Optional<User> info = userRepository.findById(Long.valueOf(no));
        return UserDetailDto.fromEntity(info.get());
    }

    public List<String> getUserJoinedTravel(int no){
        List<Person> list = personRepository.findByUser_Id(Long.valueOf(no));
        List<String> result = new ArrayList<String>();
        for (Person p : list){
            Optional<Travel> info = travelRepository.findById(Long.valueOf(p.getTravel().getId()));
            result.add(info.get().getName());
        }
        return result;
    }

    public UserDetailDto.WithTravel getUserInfoWithTravel(int no){
        Optional<User> info = userRepository.findById(Long.valueOf(no));
        UserDetailDto.WithTravel dto = UserDetailDto.WithTravel.fromEntity(info.get());
        dto.setTravelList(getUserJoinedTravel(no));
        return dto;
    } //test yet







    @Transactional
    public UserCreateDto.Response createPerson(UserCreateDto.Request userinfo, TravelDto travelinfo) {
        User user = User.builder()
                .name(userinfo.getName())
                .email(userinfo.getEmail())
                .password(userinfo.getPassword())
                .account(userinfo.getAccount())
                .build();
        Travel travel = Travel.builder()
                .name(travelinfo.getName())
                .build();
        
        userRepository.save(user);
        return UserCreateDto.Response.fromEntity(user);
    }
    

}
