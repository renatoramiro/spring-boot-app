package hello.rest.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import hello.core.model.Avatar;
import hello.core.repository.AvatarRepository;
import hello.core.repository.PersonRepository;
import hello.rest.model.AvatarRest;
import utils.AppHelper;

@Controller
@RequestMapping(value = "/avatar")
public class AvatarController {

	@Autowired
	private AvatarRepository avatarRepository;

	private static final String PATH = "/home/renato/Documents/workspace-sts-3.7.1.RELEASE/gs-spring-boot-initial/avatar/";

	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
	public ResponseEntity<byte[]> getAvatar(@PathVariable Integer id) {
		Avatar avatar = avatarRepository.findOne(id);
		if (avatar != null) {
			try {
				return new ResponseEntity<byte[]>(Files.readAllBytes(Paths.get(avatar.getFilePath())), HttpStatus.OK);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/bd", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> create(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "personId", required = true) Integer personId) {
		Avatar avatar = new Avatar();

		try {
			avatar.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		avatar.setPerson(personRepository.findOne(personId));
		return new ResponseEntity<byte[]>(avatarRepository.saveAndFlush(avatar).getFile(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AvatarRest> createWithFolder(
			@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "personId", required = true) Integer personId) {
		
		this.createAvatarFolder();
		Avatar avatar = new Avatar();
		String imageName = AppHelper.crypt(Integer.toString(avatar.hashCode()));
		String path = PATH + imageName + this.getFileExtension(file.getOriginalFilename());
		File ff = new File(path);

		try {
			file.transferTo(ff);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new ResponseEntity<AvatarRest>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<AvatarRest>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		avatar.setFilePath(ff.getAbsolutePath());
		avatar.setPerson(personRepository.findOne(personId));
		avatarRepository.saveAndFlush(avatar);
		return new ResponseEntity<AvatarRest>(AvatarRest.fromCore(avatar), HttpStatus.OK);
	}

	private void createAvatarFolder() {
		File dirPath = new File(PATH);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	private String getFileExtension(String filename) {
		try {
			return "." + filename.substring(filename.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}

}
