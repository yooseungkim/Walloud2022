package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;

public class TravelCreateDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private String Name;
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
        public static Response fromEntity(Travel travel) {
            return TravelCreateDto.Response.builder()
                    .Id(travel.getId())
                    .Name(travel.getName())
                    .build();
        }
    }

}
