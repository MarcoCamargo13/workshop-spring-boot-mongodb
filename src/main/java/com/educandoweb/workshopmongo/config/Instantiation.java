package com.educandoweb.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.repository.PostRepository;
import com.educandoweb.workshopmongo.repository.UserRepository;

@Configuration // para indicar que esta interface é uma classe de configuração
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		//userRepository.deleteAll(); // para deletar todos os registros de ususario do bD
		postRepository.deleteAll(); // para deletar todos os registros de ususario do bD
		
		// usuario para quando inicalizarmos a plicação os usuarios serão carregados no
		// BD
		//User maria = new User(null, "Maria Brown", "maria@gmail.com");
		//User alex = new User(null, "Alex Green", "alex@gmail.com");
		//User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2018 13:00"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", "Maria Brown");
		Post post2 = new Post(null, sdf.parse("19/08/2017 03:00"), "Bom dia!!", "Acordei ", "Maria Brown");
		//userRepository.save(Arrays.asList(maria, alex, bob));

		//userRepository.save(maria);
		//userRepository.save(alex);
		//userRepository.save(bob);
		postRepository.saveAll((Iterable<Post>) Arrays.asList(post1, post2));
		
		//postRepository.save(post1);
		//postRepository.save(post2);
		
		
		
		
	}

}
