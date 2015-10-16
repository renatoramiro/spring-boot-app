package hello.rest.model;

import java.io.Serializable;

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

	@Override
	public String toString() {
		return "PersonRest [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
