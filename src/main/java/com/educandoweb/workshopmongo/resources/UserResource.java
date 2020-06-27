package com.educandoweb.workshopmongo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.workshopmongo.domain.User;

@RestController //esta classe é um recurso rest
@RequestMapping(value="/users")//qual o caminho do endpoint
public class UserResource {

	@RequestMapping(method = RequestMethod.GET)//metodo que vai ser um endpoint do user
	public ResponseEntity<List<User>> findAll(){ //criar um metodo que carrega uma lista
		//ResponseEntity<List<User> encapsular toda uma estrutura necessaria para resposta http
		List<User> list = new ArrayList<>();
		User maria = new User(1, "Maria Brown", "maria@gmail.com");
		User jose = new User(1, "Jose Luis", "jose@gmail.com");
		User alex = new User(1, "Alex", "alex@gmail.com");
		//list.add(Arrays.asList(maria));//não funcionou acredito que seja versão
		list.add(maria);
		list.add(jose);
		list.add(alex);
		//return list;
		return ResponseEntity.ok().body(list);//instancia o ResponseEntity
				}
}
