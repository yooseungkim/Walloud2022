package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Participant;
import com.spring.mydiv.Entity.Person;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ParticipantDetailDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class peopleList {
        @NotNull
        private List<Person> joinedPerson;
        @NotNull
        private Person payer;
    }
}
