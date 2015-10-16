package hello.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.core.model.Person;
import hello.core.service.PersonService;

@Controller
@RequestMapping(value = "/people")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Person>> list() {
		return new ResponseEntity<List<Person>>(personService.list(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> show(@PathVariable Integer id) {
		Person person = personService.get(id);
		if (person != null) {
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Person> create(@RequestBody Person person) {
		return new ResponseEntity<Person>(personService.save(person), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person person) {
		return new ResponseEntity<Person>(personService.update(id, person), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Person> delete(@PathVariable Integer id) {
		if (personService.delete(id)) {
			return new ResponseEntity<Person>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}
	}
}
