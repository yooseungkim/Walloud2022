package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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
                    .Name(person.getUser().getName())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HomeView {
        private Long Id;
        private String Name;
        private Boolean Role;
        private Double Difference;
        public static HomeView fromEntity(Person person) {
            return HomeView.builder()
                    .Id(person.getId())
                    .Name(person.getUser().getName())
                    .Role(person.getRole())
                    .Difference(person.getDifference())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Detail {
        private Long PersonId;
        private Double SumSend;
        private Double SumGet;
        private Double Difference;
        private Boolean TravelRole;
        private String UserName;
        private String UserEmail;
        private String UserAccount;

        private List<EventCreateDto.PersonView> EventList;
        private List<HomeView> PersonInTravelList;

        public static Detail fromEntity(Person person) {
            return Detail.builder()
                    .PersonId(person.getId())
                    .SumSend(person.getSumSend())
                    .SumGet(person.getSumGet())
                    .Difference(person.getDifference())
                    .TravelRole(person.getRole())
                    .UserName(person.getUser().getName())
                    .UserEmail(person.getUser().getEmail())
                    .UserAccount(person.getUser().getAccount())
                    .build();
        }
    }
}
