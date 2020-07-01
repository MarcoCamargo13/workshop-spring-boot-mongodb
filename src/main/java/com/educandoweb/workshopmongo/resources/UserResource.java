package com.educandoweb.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.workshopmongo.domain.Post;
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
	// @GetMapping
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() { // criar um metodo que carrega uma lista
		// ResponseEntity<List<User> encapsular toda uma estrutura necessaria para
		// resposta http

		List<User> list = service.findAll();

		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		// UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);// instancia o ResponseEntity com o codigo de resposta
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) { // criar um metodo que carrega uma lista
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));// instancia o ResponseEntity com o codigo de resposta
	}

	// public ResponseEntity<void> response entity vai retornar vazio por isso void
	// @RequestMapping(method=RequestMethod.POST) //tb aceita a anotação abaixo
	// @PostMapping
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) { // para que este endpoint aceite o objDTO tem que
																		// usar RequestBody
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();// esta
																														// //
																														// pega
																														// o
																														// novo
																														// objeto
																														// que
																														// doi
																														// inserido
																														// //
																														// linha
		return ResponseEntity.created(uri).build();// create retorno o codigo de resposta http 201 , quando é criado um
													// novo recurso
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) { // para que este endpoint aceite o objDTO tem que usar
																	// RequestBody
		service.delete(id);
		return ResponseEntity.noContent().build();// create retorno o codigo de resposta http 201 , quando é criado um
													// novo recurso
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto,  @PathVariable String id) { // para que este
		User obj = service.fromDTO(objDto);
		obj.setId(id);//para garantir que o objeot vai ter o id da requisição
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
		@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
		public ResponseEntity<List<Post>> findPost(@PathVariable String id) { // criar um metodo que carrega uma lista
			User obj = service.findById(id);
			return ResponseEntity.ok().body(obj.getPosts());// instancia o ResponseEntity com o codigo de resposta
		}

}
