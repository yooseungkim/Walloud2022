package com.spring.mydiv.Service;

import com.spring.mydiv.Dto.*;
import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Participant;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Repository.EventRepository;
import com.spring.mydiv.Repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipantService participantService;

    public EventDto.Response createEvent(EventDto.Request request, boolean isPayerinParti){
        Double dividePrice = (double)request.getPrice()/request.getPartiCount();
        Double takePrice = (double)request.getPrice() - dividePrice;
        if (!isPayerinParti){
            takePrice = (double)request.getPrice();
        }

        Event event = Event.builder()
                .name(request.getName())
                .date(request.getDate())
                .price(request.getPrice())
                .travel(Travel.builder()
                        .id(request.getTravel().getId())
                        .name(request.getTravel().getName())
                        .build())
                .dividePrice(dividePrice)
                .takePrice(takePrice)
                .build();
        eventRepository.save(event);
        return EventDto.Response.fromEntity(event);
    }

    public boolean checkPayerInParticipant(List<Map> partiList, Long payerId){
        List<Long> partiIds = new ArrayList<>();
        for (Map parti : partiList){
            partiIds.add(Long.valueOf(parti.get("id").toString()));
        }
        return partiIds.contains(payerId);
    }

    public List<EventDto.HomeView> getEventInfoInTravel(int travelId){
        List<Event> list = eventRepository.findByTravel_Id(Long.valueOf(travelId));
        List<EventDto.HomeView> result = new ArrayList<>();
        for (Event e : list){
            EventDto.HomeView event = EventDto.HomeView.fromEntity(e);
            Participant payer = participantRepository.findByEvent_IdAndEventRole(event.getId(),true); // payer가 participant에 포함되어 있지 않을 시 에러
            event.setPayer(payer.getPerson().getUser().getName());
            result.add(event);
        }
        return result;
    } //ing - payer가 parti에 없을 경우 ifpresentorelse로 person에서 찾도록 수정

    public int getEventCountInTravel(int travelId){
        return eventRepository.countByTravel_Id(Long.valueOf(travelId));
    } //fin

    public String getTravelPeriod(int travelId, int eventCount){
        if (eventCount == 0) return null;
        else {
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
    } //fin

    public Optional<Event> getEventEntityByEventId(Long id){
        return eventRepository.findById(id);
    }

    public EventDto.deleteRequest getEventDetailforDelete(int eventId){
        ParticipantDto.peopleList peopleList = participantService.getJoinedPeopleInEvent(eventId);
        Optional<Event> event = eventRepository.findById(Long.valueOf(eventId));

        return EventDto.deleteRequest.builder()
                .joinedPerson(peopleList.getJoinedPerson())
                .payerId(peopleList.getPayer().getId())
                .DividePrice(event.get().getDividePrice())
                .TakePrice(event.get().getTakePrice()).build();
    }

    public void deleteEvent(int eventId){
        List<Participant> participantList = participantRepository.findByEvent_Id(Long.valueOf(eventId));
        for(Participant participant : participantList){
            participantRepository.delete(participant);
        }
        eventRepository.deleteById(Long.valueOf(eventId));
    }

}
