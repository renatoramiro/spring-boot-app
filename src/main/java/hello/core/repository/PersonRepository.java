package hello.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.core.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	Person findFirst2ByFirstNameOrderByLastNameDesc(String name);
	List<Person> queryFirst2ByFirstNameOrderByLastNameAsc(String name);
}