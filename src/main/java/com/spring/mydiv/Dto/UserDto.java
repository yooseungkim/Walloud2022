package com.spring.mydiv.Dto;

import javax.persistence.Column;

import com.spring.mydiv.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 12nov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	private String Name;
	private String Email;
	private String Password;
	private String Account;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .Name(user.getName())
                .Email(user.getEmail())
                .Password(user.getPassword())
                .Account(user.getAccount())
                .build();
    }
}