package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Person;
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
    public static class Simple {
        private Long Id;
        private String Name;

        public static Simple fromEntity(Person person) {
            return Simple.builder()
                    .Id(person.getId())
                    .build();
        }
    }
}
