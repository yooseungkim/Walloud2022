package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.mydiv.Dto.TravelDto;
import com.spring.mydiv.Dto.UserCreateDto;
import com.spring.mydiv.Dto.UserDto;
import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.PersonRepository;
import com.spring.mydiv.Repository.TravelRepository;
import com.spring.mydiv.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

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
        		//.Id(userdto.getId())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .account(request.getAccount())
                .build();
        userRepository.save(user);
        return UserCreateDto.Response.fromEntity(user);
    }
    
    UserCreateDto.Response answer = null;
    public UserCreateDto.Response login(UserCreateDto.Login loginUser) {
    	Optional<User> info = userRepository.findByEmail(loginUser.getEmail());
    	info.ifPresent(user ->
    					{if (loginUser.getPassword().toString().equals(user.getPassword().toString())) {
    						answer = UserCreateDto.Response.fromEntity(user);}
    					}
    					);
    	return answer;
    }
    
    List<String> travelList = null;
    public List<String> getUserJoinedTravel(int no){
    	List<Integer> travelIdList = personRepository.findTravelIdByUserId(no);
    	for(int id : travelIdList) {
    		travelList.add(travelRepository.findNameIdById(id));
    	}
    	return travelList;
    }
    
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
