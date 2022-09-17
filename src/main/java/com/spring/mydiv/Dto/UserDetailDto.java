package com.spring.mydiv.Dto;

import java.util.List;

import com.spring.mydiv.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author 12nov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDto {
	private Long Id;
	private String Name;
	private String Email;
	private String Password;
	private String Account;

    public static UserDetailDto fromEntity(User user) {
        return UserDetailDto.builder()
				.Id(user.getId())
                .Name(user.getName())
                .Email(user.getEmail())
                .Password(user.getPassword())
                .Account(user.getAccount())
                .build();
    }

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class WithTravel {
		@NotNull
		private Long Id;
		@NotNull
		private String Name;
		@NotNull
		private String Email;
		@NotNull
		private String Password;
		@NotNull
		private String Account;
		private List<TravelCreateDto.Response> TravelList;

		public static WithTravel fromEntity(User user) {
			return WithTravel.builder()
					.Id(user.getId())
					.Name(user.getName())
					.Email(user.getEmail())
					.Password(user.getPassword())
					.Account(user.getAccount())
					.build();
		}
	}
}