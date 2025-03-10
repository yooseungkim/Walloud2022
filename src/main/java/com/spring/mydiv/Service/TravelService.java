package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import com.spring.mydiv.Dto.TravelDto;
import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Exception.DefaultException;
import com.spring.mydiv.Repository.EventRepository;
import com.spring.mydiv.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Repository.TravelRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.spring.mydiv.Code.ErrorCode.*;

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
    public TravelDto.Response createTravel(TravelDto.Request request) {
        Travel travel = Travel.builder()
                .name(request.getName())
                .build();
        travelRepository.save(travel);
        return TravelDto.Response.fromEntity(travel);
    } //fin

    public TravelDto.Response getTravelInfo(int no){
        Optional<Travel> info = travelRepository.findById(Long.valueOf(no));
        return TravelDto.Response.fromEntity(info.get());
    }

    public TravelDto.HomeView getTravelToMainView(int travelId){
        return travelRepository.findById(Long.valueOf(travelId))
                .map(TravelDto.HomeView::fromEntity)
                .orElseThrow(()-> new DefaultException(NO_TRAVEL));
    } //fin

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
