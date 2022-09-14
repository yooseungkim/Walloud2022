package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;

public class PersonCreateDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private UserDetailDto User;
        @NotNull
        private TravelCreateDto.Response Travel;
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
        private String Email;
        @NotNull
        private String Password;
        @NotNull
        private String Account;

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
