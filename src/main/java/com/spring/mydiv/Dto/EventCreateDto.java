package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EventCreateDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private String Name;
        @NotNull
        private TravelCreateDto.Response Travel;
        @NotNull
        private java.util.Date Date;
        @NotNull
        private int Price;
        private String Image;
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
        @NotNull
        private TravelCreateDto.Response Travel;
        @NotNull
        private java.util.Date Date;
        @NotNull
        private int Price;

        public static Response fromEntity(Event event) {
            return Response.builder()
                    .Id(event.getId())
                    .Name(event.getName())
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
        private Long Id;
        @NotNull
        private String Name;
        @NotNull
        private java.util.Date Date;
        @NotNull
        private int Price;

        private String Payer;

        public static HomeView fromEntity(Event event) {
            return HomeView.builder()
                    .Id(event.getId())
                    .Name(event.getName())
                    .Date(event.getDate())
                    .Price(event.getPrice())
                    .build();
        }
    }
}
