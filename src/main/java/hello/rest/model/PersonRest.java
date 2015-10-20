package hello.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hello.core.model.Person;

public class PersonRest implements Serializable {

	private static final long serialVersionUID = 3061004681936744509L;
	private Integer id;
	private String firstName;
	private String lastName;

	public PersonRest() {
	}

	public PersonRest(Integer id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Person toCore() {
		Person person = new Person();
		person.setId(getId());
		person.setFirstName(getFirstName());
		person.setLastName(getLastName());
		return person;
	}

	public static PersonRest fromCore(Person person) {
		PersonRest rest = new PersonRest();
		rest.setId(person.getId());
		rest.setFirstName(person.getFirstName());
		rest.setLastName(person.getLastName());

		return rest;
	}

	public static List<PersonRest> fromCore(List<Person> people) {
		List<PersonRest> result = new ArrayList<PersonRest>();
		for (Person person : people) {
			result.add(fromCore(person));
		}
		return result;
	}

	@Override
	public String toString() {
		return "PersonRest [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
