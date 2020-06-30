package com.educandoweb.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.repository.UserRepository;

@Configuration // para indicar que esta interface é uma classe de configuração
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll(); // para deletar todos os registros de ususario do bD

		// usuario para quando inicalizarmos a plicação os usuarios serão carregados no
		// BD
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		//userRepository.save(Arrays.asList(maria, alex, bob));

		userRepository.save(maria);
		userRepository.save(alex);
		userRepository.save(bob);
	}

}
