package com.spring.mydiv.Dto;

import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Travel;
import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private Travel Travel;
    private String Name;
    private Date Date;
    private int Price;
    private Double DividePrice;
    private Double TakePrice;

    public static EventDto fromEntity(Event event){
        return EventDto.builder()
                .Travel(event.getTravel())
                .Name(event.getName())
                .Date(event.getDate())
                .Price(event.getPrice())
                .DividePrice(event.getDividePrice())
                .TakePrice(event.getTakePrice())
                .build();
    }
}
