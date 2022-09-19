package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Travel;
import lombok.*;

import javax.validation.constraints.NotNull;

public class ParticipateCreateDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private Long personId;
        private Long eventId;
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
        public static TravelCreateDto.Response fromEntity(Travel travel) {
            return TravelCreateDto.Response.builder()
                    .Id(travel.getId())
                    .Name(travel.getName())
                    .build();
        }
    }
}
