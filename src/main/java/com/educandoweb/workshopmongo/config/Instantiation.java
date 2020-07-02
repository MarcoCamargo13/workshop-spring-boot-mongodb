package com.educandoweb.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.dto.AuthorDTO;
import com.educandoweb.workshopmongo.dto.CommentsDTO;
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

		userRepository.deleteAll(); // para deletar todos os registros de ususario do bD
		postRepository.deleteAll(); // para deletar todos os registros de ususario do bD
		
		// usuario para quando inicalizarmos a plicação os usuarios serão carregados no
		// BD
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//userRepository.save(maria);
		//userRepository.save(alex);
		//userRepository.save(bob);
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018 13:00"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("19/08/2017 03:00"), "Bom dia!!", "Acordei ", new AuthorDTO(alex));
		
		CommentsDTO c1 = new CommentsDTO("Boa viagem mano!", sdf.parse("2018/03/21 01:00") , new AuthorDTO(alex));
		CommentsDTO c2 = new CommentsDTO("Aproveite!", sdf.parse("2019/03/22 02:00") , new AuthorDTO(bob));
		CommentsDTO c3 = new CommentsDTO("Tenha um otimo dia!", sdf.parse("2020/03/24 05:00") , new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		
		//postRepository.save(post1);
		//postRepository.save(post2);
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		
		
	}

}
