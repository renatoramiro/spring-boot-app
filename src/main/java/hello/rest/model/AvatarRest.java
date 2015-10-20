package hello.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hello.core.model.Avatar;
import hello.core.service.PersonService;

public class AvatarRest implements Serializable {

	private static final long serialVersionUID = -9193140153474134796L;
	private Integer id;
	private String filePath;
	private Integer personId;

	public AvatarRest() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Avatar toCore(PersonService personService) {
		Avatar avatar = new Avatar();
		avatar.setId(getId());
		avatar.setFilePath(getFilePath());
		if (avatar.getPerson() != null) {
			avatar.setPerson(personService.get(getPersonId()));
		}
		return avatar;
	}

	public static AvatarRest fromCore(Avatar avatar) {
		AvatarRest rest = new AvatarRest();
		rest.setId(avatar.getId());
		rest.setFilePath(avatar.getFilePath());
		rest.setPersonId(avatar.getPerson().getId());
		return rest;
	}

	public static List<AvatarRest> fromCore(List<Avatar> avatars) {
		List<AvatarRest> result = new ArrayList<AvatarRest>();
		for (Avatar avatar : avatars) {
			result.add(fromCore(avatar));
		}
		return result;
	}

	@Override
	public String toString() {
		return "AvatarRest [id=" + id + ", filePath=" + filePath + ", personId=" + personId + "]";
	}

}
