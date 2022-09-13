package com.spring.mydiv.Repository;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mydiv.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 12nov
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
	List<Integer> findTravelIdByUserId(int no);
}
