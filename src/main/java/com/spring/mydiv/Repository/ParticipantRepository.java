package com.spring.mydiv.Repository;

import com.spring.mydiv.Entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 12nov
 */
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByPerson_Id(Long id);

    Participant findByEvent_IdAndEventRole(Long id, Boolean eventRole);
}
