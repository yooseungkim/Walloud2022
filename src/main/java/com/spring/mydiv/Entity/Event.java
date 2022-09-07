package com.spring.mydiv.Entity;

import javax.persistence.*;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EVENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {
	
	// fk에 대하여 두 테이블에 모두 어노테이션 필요하다. (관계 명시)
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id", length = 20)
	private Long Id;
	
	@Column(name = "travel_id",  length = 20)
	private Long Travel_Id;
	
	@Column(name = "event_name", length = 200)
	private String Name;
	
	@Column(name = "event_date")
	private Date Date;
	
	@Column(name = "event_price", length = 11)
	private int Price;
	
	@Column(name = "event_divideprice")
	private Double Divideprice;
	
	@Column(name = "event_getprice")
	private Double Getprice;
	
	@Column(name = "event_image")
	private String Image;
	
	@Builder
	public Event(Long Travel_Id, String Name, Date Date, int Price, int Parti_size, String Image) { // Parti_size: corresponding participant size
		this.Travel_Id = Travel_Id;
		this.Name = Name;
		this.Date = Date;
		this.Price = Price;
		this.Divideprice = (double) Price / Parti_size;
		this.Getprice = (double) Price * ((Parti_size - 1) / Parti_size);
		this.Image = Image;
	}
}