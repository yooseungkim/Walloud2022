package com.spring.mydiv.Service;

import com.spring.mydiv.Dto.EventCreateDto;
import com.spring.mydiv.Dto.ParticipantCreateDto;
import com.spring.mydiv.Dto.ParticipantDetailDto;
import com.spring.mydiv.Dto.ParticipantDto;
import com.spring.mydiv.Entity.Participant;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Repository.EventRepository;
import com.spring.mydiv.Repository.ParticipantRepository;
import com.spring.mydiv.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;
    private final PersonRepository personRepository;

    @Transactional
    public ParticipantDto createParticipant(ParticipantCreateDto.Request request){
        Participant participant = Participant.builder()
                .person(request.getPerson())
                .event(request.getEvent())
                .eventRole(request.getRole())
                .build();
        participantRepository.save(participant);
        return ParticipantDto.fromEntity(participant);
    }

    public List<EventCreateDto.PersonView> getEventListThatPersonJoin(int personId){
        List<EventCreateDto.PersonView> result = new ArrayList<>();
        // personid가 있는 parti를 전부 찾아서 -> 그 parti의 event id, event role를 get
        List<Participant> partiList = participantRepository.findByPerson_Id(Long.valueOf(personId));
        for (Participant p : partiList){
            Long eventId = p.getEvent().getId();
            Boolean myEventRole = p.getEventRole();
            // -> event id를 통해 나머지 값 get(EventId, EventName, Date, Price, DividePrice, TakePrice)
            EventCreateDto.PersonView tmpEvent = EventCreateDto.PersonView.fromEntity(eventRepository.findById(eventId).get());
            // -> if event role = payer
            if (myEventRole == true){
                //      -> PayerId는 자기 것으로,
                tmpEvent.setPayerId(Long.valueOf(personId));
                //      -> PayerName은 person db에서 get
                tmpEvent.setPayerName(personRepository.findById(Long.valueOf(personId))
                        .get().getUser().getName());
            } else {
                //      -> event id & event role==1 을 조건으로
                Participant payer = participantRepository.findByEvent_IdAndEventRole(eventId, true);
                //          -> in parti db) 결제자의 person id
                tmpEvent.setPayerId(payer.getId());
                //          -> in person db) 결제자의 name
                tmpEvent.setPayerName(personRepository.findById(Long.valueOf(payer.getPerson().getId()))
                        .get().getUser().getName());
            }
            result.add(tmpEvent);
        }
        return result;
    }

    public int getSizeOfJoinedEventList(int personId){
        return participantRepository.findByPerson_Id(Long.valueOf(personId)).size();
    }

    public ParticipantDetailDto.peopleList getJoinedPeopleInEvent(int eventId){
        List<Participant> participantList = participantRepository.findByEvent_Id(Long.valueOf(eventId));
        List<Person> joinedPeople = new ArrayList<Person>();
        Person Payer = new Person();
        for(Participant participant : participantList){
            joinedPeople.add(participant.getPerson());
            if (participant.getEventRole() == Boolean.TRUE){
                Payer = participant.getPerson();
            }
        }
        ParticipantDetailDto.peopleList peopleList = new ParticipantDetailDto.peopleList(joinedPeople, Payer);
        return peopleList;
    }

    public List<ParticipantDetailDto.detailView> getParticipantInEvent(int eventId){
        List<Participant> participantList = participantRepository.findByEvent_Id(Long.valueOf(eventId));
        List<ParticipantDetailDto.detailView> participantDetailList = new ArrayList<>();

        for(Participant participant : participantList){
            Long personId = participant.getPerson().getId();
            String participantName = participant.getPerson().getUser().getName();
            Boolean eventRole = participant.getEventRole();
            ParticipantDetailDto.detailView participantDetail = new ParticipantDetailDto.detailView(personId, participantName, eventRole);
            participantDetailList.add(participantDetail);
        }

        return participantDetailList;
    }
}
