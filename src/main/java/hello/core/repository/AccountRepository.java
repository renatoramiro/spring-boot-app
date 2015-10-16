package hello.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.core.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	Account findOneByUsername(String username);

}
