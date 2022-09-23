package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import com.spring.mydiv.Dto.TravelCreateDto;
import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Repository.EventRepository;
import com.spring.mydiv.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Repository.TravelRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class TravelService {
    private final EventService eventService;
	private final TravelRepository travelRepository;
    private final EventRepository eventRepository;
    private final PersonRepository personRepository;

    @Transactional
    public TravelCreateDto.Response createTravel(TravelCreateDto.Request request) {
        Travel travel = Travel.builder()
                .name(request.getName())
                .build();
        travelRepository.save(travel);
        return TravelCreateDto.Response.fromEntity(travel);
    }

    public TravelCreateDto.Response getTravelInfo(int no){
        Optional<Travel> info = travelRepository.findById(Long.valueOf(no));
        return TravelCreateDto.Response.fromEntity(info.get());
    }

    public TravelCreateDto.HomeView getTravelToMainView(int travelId){
        Optional<Travel> info = travelRepository.findById(Long.valueOf(travelId));
        return TravelCreateDto.HomeView.fromEntity(info.get());
    }
    @Transactional
    public void deleteTravel(int travelId){
        List<Event> eventList = eventRepository.findByTravel_Id(Long.valueOf(travelId));
        List<Person> personList = personRepository.findByTravel_Id(Long.valueOf(travelId));
        for(Event event : eventList){
            eventService.deleteEvent(event.getId().intValue());
        }
        for(Person person : personList){
            personRepository.delete(person);
        }
        travelRepository.deleteById(Long.valueOf(travelId));
    }
}
