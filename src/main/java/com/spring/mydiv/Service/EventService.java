package com.spring.mydiv.Service;

import com.spring.mydiv.Dto.EventCreateDto;
import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Repository.EventRepository;
import com.spring.mydiv.Repository.ParticipateRepository;
import com.spring.mydiv.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.xml.stream.util.EventReaderDelegate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public ResponseEntity<EventCreateDto.Response> createEvent(EventCreateDto.Request request){
        Event event = Event.builder()
                .name(request.getName())
                .date(request.getDate())
                .price(request.getPrice())
                .travel(Travel.builder()
                        .id(request.getTravel().getId())
                        .name(request.getTravel().getName())
                        .build())
                .build();
        eventRepository.save(event);
        return EventDto.fromEntity(event); //해야함 - fromEntity 의 역할?
    }

    public List<EventCreateDto.HomeView> getEventInfoInTravel(int travelId){
        List<Event> list = eventRepository.findByTravel_Id(Long.valueOf(travelId));
        List<EventCreateDto.HomeView> result = new ArrayList<EventCreateDto.HomeView>();
        for (Event e : list){
            EventCreateDto.HomeView event = EventCreateDto.HomeView.fromEntity(e);
            result.add(event);
        }
        return result;
    }

    public int getEventCountInTravel(int travelId){
        return eventRepository.countByTravel_Id(Long.valueOf(travelId));
    }

    public String getTravelPeriod(int travelId){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date format1 = eventRepository.findFirstByTravel_IdOrderByDateDesc(Long.valueOf(travelId))
                .getDate(); //latest
        Date format2 = eventRepository.findFirstByTravel_IdOrderByDateAsc(Long.valueOf(travelId))
                .getDate(); //oldest
        long diffSec = (format1.getTime() - format2.getTime()) / 1000;
        long diffDays = diffSec / (24*60*60);
        String periodFormat = dateFormat.format(format2) + " ~ " + dateFormat.format(format1)
                + ", " + diffDays + " days";
        return periodFormat;
    }

}
