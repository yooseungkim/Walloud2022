package com.spring.mydiv.Dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.spring.mydiv.Entity.Person;
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
public class PersonDto {
	private User User;
	private Travel Travel;
	private Double SumSend;
	private Double SumGet;
	private Double Difference;
	private String Role;
	
    public static PersonDto fromEntity(Person person) {
        return PersonDto.builder()
                .User(person.getUser())
                .Travel(person.getTravel())
                .SumSend(person.getSumSend())
                .SumGet(person.getSumGet())
                .Difference(person.getDifference())
                .build();
    }
}
