package com.spring.mydiv.Dto;

import javax.validation.constraints.NotNull;

import com.spring.mydiv.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 12nov
 */
public class UserCreateDto {
	
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private String Name;
        @NotNull
        private String Email;
        @NotNull
        private String Password;
        @NotNull
        private String Account;
    }
	
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
    	private String Name;
    	private String Email;
    	private String Password;
    	private String Account;

        public static Response fromEntity(User user) {
            return Response.builder()
            		.Name(user.getName())
            		.Email(user.getEmail())
            		.Password(user.getPassword())
            		.Account(user.getAccount())
                    .build();
        }
    }
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Login {
        @NotNull
        private String Email;
        @NotNull
        private String Password;
    }
}