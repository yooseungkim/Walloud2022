package com.spring.mydiv.Dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.spring.mydiv.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class UserDto {
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

		public static Response fromEntity(User user) {
			return Response.builder()
					.Id(user.getId())
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

		private List<TravelDto.Response> TravelList;

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