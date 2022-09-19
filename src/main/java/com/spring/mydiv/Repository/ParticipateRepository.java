package com.spring.mydiv.Repository;

import com.spring.mydiv.Entity.Participant;
import com.spring.mydiv.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 12nov
 */
public interface ParticipateRepository extends JpaRepository<Participant, Long> {
}
