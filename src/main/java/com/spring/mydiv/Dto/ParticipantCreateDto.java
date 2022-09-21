package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Participant;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.Travel;
import lombok.*;

import javax.validation.constraints.NotNull;

public class ParticipantCreateDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private Person person;
        @NotNull
        private Event event;
        @NotNull
        private Boolean role;
        /**Boolean eventRole
         * 1, true: payer
         * 0, false: -
         */
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        @NotNull
        private Long Id;
        @NotNull
        private String Name;
        public static Response fromEntity(Participant participant) {
            return Response.builder()

                    .build();
        }
    }
}
