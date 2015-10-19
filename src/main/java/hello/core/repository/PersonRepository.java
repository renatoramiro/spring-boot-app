package hello.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hello.core.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	Person findOneByFirstNameOrderByLastNameDesc(String name);

	List<Person> queryFirst2ByFirstNameOrderByLastNameAsc(String name);

	List<Person> findByFirstNameContainingOrderByFirstNameDesc(String name);

	@Query("select p from Person p where p.firstName like %?1% order by p.firstName desc")
	List<Person> buscaPeloPrimeiroNome(String name);
}