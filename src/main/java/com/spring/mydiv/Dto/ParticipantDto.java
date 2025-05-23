package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ParticipantDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class basic {
        private Person person;
        private Event event;
        private Boolean eventRole;

        public static basic fromEntity(Participant participant) {
            return basic.builder()
                    .person(participant.getPerson())
                    .event(participant.getEvent())
                    .eventRole(participant.getEventRole())
                    .build();
        }

    }

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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class detailView {
        @NotNull
        private Long Id;
        @NotNull
        private String Name;
        @NotNull
        private boolean eventRole;
    }

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