package com.educandoweb.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.dto.UserDTO;
import com.educandoweb.workshopmongo.services.UserService;

@RestController // esta classe é um recurso rest
@RequestMapping(value = "/users") // qual o caminho do endpoint
public class UserResource {

	// controlador Rest conversa com serviço então injetamos o servico

	@Autowired
	private UserService service;

	// @RequestMapping(method = RequestMethod.GET) // metodo que vai ser um endpoint
	// do user
	//@GetMapping
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() { // criar um metodo que carrega uma lista
		// ResponseEntity<List<User> encapsular toda uma estrutura necessaria para
		// resposta http

		List<User> list = service.findAll();

		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		// UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);// instancia o ResponseEntity com o codigo de resposta
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) { // criar um metodo que carrega uma lista
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));// instancia o ResponseEntity com o codigo de resposta
	}
}
