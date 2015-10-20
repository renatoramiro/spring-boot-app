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
import org.springframework.web.bind.annotation.RequestParam;

import hello.core.model.Person;
import hello.core.service.PersonService;
import hello.rest.model.PersonRest;

@Controller
@RequestMapping(value = "/people")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PersonRest>> list(@RequestParam(value = "name", required = false) String name) {
		if (name == null || name.isEmpty()) {
			return new ResponseEntity<List<PersonRest>>(PersonRest.fromCore(personService.list()), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PersonRest>>(PersonRest.fromCore(personService.queryByFirstName(name)),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PersonRest> show(@PathVariable Integer id) {
		Person person = personService.get(id);
		if (person != null) {
			return new ResponseEntity<PersonRest>(PersonRest.fromCore(person), HttpStatus.OK);
		} else {
			return new ResponseEntity<PersonRest>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PersonRest> create(@RequestBody Person person) {
		return new ResponseEntity<PersonRest>(PersonRest.fromCore(personService.save(person)), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PersonRest> update(@PathVariable Integer id, @RequestBody Person person) {
		return new ResponseEntity<PersonRest>(PersonRest.fromCore(personService.update(id, person)), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PersonRest> delete(@PathVariable Integer id) {
		if (personService.delete(id)) {
			return new ResponseEntity<PersonRest>(HttpStatus.OK);
		} else {
			return new ResponseEntity<PersonRest>(HttpStatus.BAD_REQUEST);
		}
	}
}
