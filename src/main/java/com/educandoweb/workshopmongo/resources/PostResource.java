package com.educandoweb.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.services.PostService;

@RestController // esta classe é um recurso rest
@RequestMapping(value = "/posts") // qual o caminho do endpoint
public class PostResource {

	// controlador Rest conversa com serviço então injetamos o servico

	@Autowired
	private PostService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findPost(@PathVariable String id) { // criar um metodo que carrega uma lista
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);// instancia o ResponseEntity com o codigo de resposta
	}

}
