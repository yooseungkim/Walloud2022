package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantDto {
    private Person person;
    private Event event;
    private Boolean eventRole;

    public static ParticipantDto fromEntity(Participant participant) {
        return ParticipantDto.builder()
                .person(participant.getPerson())
                .event(participant.getEvent())
                .eventRole(participant.getEventRole())
                .build();
    }

}