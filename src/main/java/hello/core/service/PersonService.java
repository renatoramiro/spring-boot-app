package hello.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.core.model.Person;
import hello.core.repository.AvatarRepository;
import hello.core.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AvatarRepository avatarRepository;

	public List<Person> list() {
		return personRepository.findAll();
	}

	public Person get(Integer id) {
		Person person = personRepository.findOne(id);
		person.setAvatar(avatarRepository.findOneByPersonId(id));
		return person;
	}
	
	public Person getByName(String name) {
		return personRepository.findFirst2ByFirstNameOrderByLastNameDesc(name);
	}

	public Person save(Person person) {
		return personRepository.saveAndFlush(person);
	}

	public Person update(Integer id, Person person) {
		Person updatePerson = personRepository.findOne(id);
		updatePerson.setFirstName(person.getFirstName());
		updatePerson.setLastName(person.getLastName());
		updatePerson.setAvatar(person.getAvatar());
		return personRepository.saveAndFlush(updatePerson);
	}

	public boolean delete(Integer id) {
		boolean result = false;
		if (personRepository.exists(id)) {
			result = true;
			personRepository.delete(id);
		}
		
		return result;
	}

}
