package com.spring.mydiv.Entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARTICIPANT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participant {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participant_id", length = 20)
	private Long id;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "person_id", referencedColumnName = "person_id"),
			@JoinColumn(name = "person_name", referencedColumnName = "user_name")
	})
	private Person person;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "event_id", referencedColumnName = "event_id"),
			@JoinColumn(name = "event_name", referencedColumnName = "event_name")
	})
	private Event event;
	
	@Column(name = "participant_eventRole", length = 1)
	private Boolean eventRole;
	/**Boolean eventRole
	 * 1, true: payer
	 * 0, false: -
	 */

}