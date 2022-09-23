package com.spring.mydiv.Repository;

import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author 12nov
 */
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByPerson_Id(Long id);
    List<Participant> findByEvent_Id(Long id);
    void delete(Participant participant);

    long deleteByEvent(Event event);



    Participant findByEvent_IdAndEventRole(Long id, Boolean eventRole);
}
