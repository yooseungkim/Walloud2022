package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Travel;
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
public class TravelDto {
	private String Name;
	
    public static TravelDto fromEntity(Travel travel) {
        return TravelDto.builder()
                .Name(travel.getName())
                .build();
    }
}
