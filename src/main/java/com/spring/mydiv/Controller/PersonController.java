package com.spring.mydiv.Controller;

import com.spring.mydiv.Dto.*;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Service.ParticipantService;
import com.spring.mydiv.Service.PersonService;
import com.spring.mydiv.Service.TravelService;
import com.spring.mydiv.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;

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
    private final ParticipantService participantService;

    @PostMapping("/{userid}/{travelid}/createUser")
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
            PersonDto personDto = personService.createPerson(request, FALSE);
            if (personDto != null)
                return "200"; //success
            else return "-2"; //fail
        }
    }

    @PostMapping("/{userid}/{travelid}/deleteUser")
    public String deletePerson2Travel(@PathVariable int travelid,
                                      @RequestBody Map map){
        int person_id = Integer.parseInt(map.get("person_id").toString());
        if (participantService.getSizeOfJoinedEventList(person_id) == 0){
            personService.deleteJoinTravel(person_id);
            return "200";
        }
        else {
            return "-1";
        }
    }

    @GetMapping("/{userid}/{travelid}/{personid}/personDetail")
    public PersonCreateDto.Detail getPersonToDetailView(@PathVariable("travelid") int travelid,
                                                        @PathVariable("personid") int personid){
        PersonCreateDto.Detail detailView = personService.getPersonToDetailView(personid);
        detailView.setEventList(participantService.getEventListThatPersonJoin(personid));
        //이 여행에서 해야하는 order 프린트를 위한 list(travelrole, diff에 따라)
        if (detailView.getTravelRole()==true){ // =총무 -> (여행 참여 전원) id, name, 이사람에게(받을/줄)돈
            detailView.setPersonInTravelList(personService.getPersonInfoInTravel(travelid));
        } else { // ~총무 -> 총무id, 총무name, 내가총무에게(받을/줄)돈
            List<PersonCreateDto.HomeView> PersonInTravelList = new ArrayList<>();
            PersonCreateDto.HomeView tmp = personService.getPayerInTravel(travelid);
            tmp.setDifference(detailView.getDifference());
            PersonInTravelList.add(tmp);
            detailView.setPersonInTravelList(PersonInTravelList);
        }
        return detailView;
    }
}
