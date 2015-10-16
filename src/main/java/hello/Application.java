package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hello.core.model.Account;
import hello.core.model.Authority;
import hello.core.repository.AccountRepository;
import hello.core.repository.AuthorityRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(final AccountRepository accountRepository, final AuthorityRepository authRepository) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				Authority authority = new Authority();
				authority.setName("ROLE_USER");
				if (authRepository.findOneByName("ROLE_USER") == null) {
					authRepository.save(authority);
				}
				
				Account account = new Account();
				account.setPassword("password");
				account.setUsername("renato");
				account.addAuthority(authority);
				if (accountRepository.findOneByUsername("renato") == null) {
					accountRepository.save(account);
				}
			}

		};

	}
}
