package com.spring.mydiv.Controller;

import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Dto.PersonDto;
import com.spring.mydiv.Dto.UserDetailDto;
import com.spring.mydiv.Service.PersonService;
import com.spring.mydiv.Service.TravelService;
import com.spring.mydiv.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 12nov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PersonController {
    private final UserService userService;
    private final TravelService travelService;
    private final PersonService personService;

    @PostMapping("/{userid}/{travelid}/invitePerson") //프론트 테스트중
    public String createPerson2Travel(@PathVariable int travelid,
                                      @RequestBody Map map){
        String user_email = map.get("user_email").toString();
        UserDetailDto userDetailDto = userService.getUserInfoByEmail(user_email);
        if (userDetailDto == null){
            return "-1";
        } else {
            PersonCreateDto.Request request = new PersonCreateDto.Request(
                    userDetailDto,
                    travelService.getTravelInfo(travelid));
            PersonDto personDto = personService.createPerson(request);
            if (personDto != null)
                return "200"; //success
            else return "-2"; //fail
        }
    }
}
