package com.spring.mydiv.Entity;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 12nov
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "person")
public class Person implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id", length = 20)
	private Long id;
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
			@JoinColumn(name = "user_name", referencedColumnName = "user_name")
	})
	private User user;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "travel_id", referencedColumnName = "travel_id"),
			@JoinColumn(name = "travel_name", referencedColumnName = "travel_name")
	})
	private Travel travel;
	
	@Column(name = "person_sumsend", nullable = false)
	private Double sumSend;
	
	@Column(name = "person_sumget", nullable = false)
	private Double sumGet;
	
	@Column(name = "person_difference", nullable = false)
	private Double difference;
	
	@Column(name = "person_travelrole", length = 50)
	private String role;
	
}