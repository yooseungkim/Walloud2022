package com.spring.mydiv.Dto;

import java.util.List;

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
public class UserDetailDto {
	private String Name;
	private String Email;
	private String Password;
	private String Account;
	//private List Travel;

    public static UserDetailDto fromEntity(User user) {
        return UserDetailDto.builder()
                .Name(user.getName())
                .Email(user.getEmail())
                .Password(user.getPassword())
                .Account(user.getAccount())
                .build();
    }
}