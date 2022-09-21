package com.spring.mydiv.Service;

import com.spring.mydiv.Dto.ParticipantCreateDto;
import com.spring.mydiv.Dto.ParticipantDto;
import com.spring.mydiv.Entity.Participant;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

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
}
