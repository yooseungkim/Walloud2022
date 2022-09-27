package com.spring.mydiv.Repository;

import java.util.List;

import com.spring.mydiv.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 12nov
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
	List<Person> findByUser_Id(@Param(value = "user_id") Long id);

	Person findByTravel_IdAndRole(Long id, Boolean role);
	List<Person> findByTravel_Id(Long id);
	void deleteByUser_IdAndTravel_Id(Long userid, Long travelid);

	void deleteById(Long id);

	void delete(Person person);
	int countDistinctByTravel_Id(Long id);

	@Transactional
	@Modifying
	@Query("update Person p set p.sumSend = ?1, p.sumGet = ?2, p.difference = ?3, p.role = ?4 where p.id = ?5")
	int updateSumSendAndSumGetAndDifferenceAndRoleById(Double sumSend, Double sumGet, Double difference, Boolean role, Long id);

}


//public class PersonRepositoryImpl implements PersonRepository{
//	EntityManager em;
////    @Override
////    public List<Integer> findTravelByUser(Long id) {
////        return em.createQuery("select p.travel.id from Person p where p.user.id = :user")
////                .setParameter("user", id)
////                .getResultList();
////    }
////    @Override
////    public List<Travel> findTravelByUser(User user) {
////        return em.createQuery("select p.travel from Person p where p.user.id = :user")
////                .setParameter("user", user)
////                .getResultList();
////    }
//
//	@Override
//	public List<Person> findTravelByUser(Long id) {
//		return em.createQuery("select p from Person p where p.id =:id")
//				.setParameter("id", id)
//				.getResultList();
//	}
