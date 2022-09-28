package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Person;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public class EventDetailDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class deleteRequest {
        @NotNull
        private List<Person> joinedPerson;
        @NotNull
        private Long payerId;
        private Double DividePrice;
        private Double TakePrice;
    }
}
