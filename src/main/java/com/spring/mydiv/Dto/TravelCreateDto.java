package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HomeView {
        @NotNull
        private Long TravelId;
        @NotNull
        private String TravelName;

        private List<PersonCreateDto.HomeView> PersonList;
        private int PersonCount;

        private List<EventCreateDto.HomeView> EventList;
        private int EventCount;
        private String Period;

        public static HomeView fromEntity(Travel travel) {
            return HomeView.builder()
                    .TravelId(travel.getId())
                    .TravelName(travel.getName())
                    .build();
        }
    }

}
