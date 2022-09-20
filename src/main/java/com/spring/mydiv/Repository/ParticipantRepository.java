package com.spring.mydiv.Repository;

import com.spring.mydiv.Entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 12nov
 */
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
