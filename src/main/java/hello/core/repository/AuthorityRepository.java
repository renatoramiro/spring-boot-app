package hello.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.core.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
	Authority findOneByName(String name);
}
	