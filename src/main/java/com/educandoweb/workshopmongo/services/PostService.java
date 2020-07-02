package com.educandoweb.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.repository.PostRepository;
import com.educandoweb.workshopmongo.services.exception.ObjectNotFoundException;

// para falar para o Spring que esta classe será um serviço e sera enjetada em
@Service // outras classe
public class PostService {// classe responsavel para trabalhar com ususario

	// instanciar automaticamento, com isso o Spring vai procurar o objeto e fazer
	@Autowired // tudo automatico
	private PostRepository repo;// instanciar um objeto userRepository //injeção de dependencia

	public List<Post> findAll() {// reponsavel por retornar todos os ususario do BD
		return repo.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findTitle (String text){
		return repo.findByTitleContainingIgnoreCase(text);//metodo no javadoc pronto https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
	}

}