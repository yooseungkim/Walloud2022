package com.spring.mydiv.Dto;

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
        private String Name;
        @NotNull
        private TravelCreateDto.Response Travel;
        @NotNull
        private java.util.Date Date;
        @NotNull
        private int Price;

        public static UserCreateDto.Response fromEntity(User user) {
            return UserCreateDto.Response.builder()
                    .Name(user.getName())
                    .Email(user.getEmail())
                    .Password(user.getPassword())
                    .Account(user.getAccount())
                    .build();
        }
    }
}
