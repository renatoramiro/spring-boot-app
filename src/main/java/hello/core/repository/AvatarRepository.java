package hello.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.core.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
	
	Avatar findOneByPersonId(Integer personId);

}
