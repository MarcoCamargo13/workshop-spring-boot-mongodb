package com.educandoweb.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.dto.UserDTO;
import com.educandoweb.workshopmongo.repository.UserRepository;
import com.educandoweb.workshopmongo.services.exception.ObjectNotFoundException;

 // para falar para o Spring que esta classe será um serviço e sera enjetada em
@Service			// outras classe
public class UserService {// classe responsavel para trabalhar com ususario

	 // instanciar automaticamento, com isso o Spring vai procurar o objeto e fazer
	@Autowired			// tudo automatico
	private UserRepository repo;// instanciar um objeto userRepository //injeção de dependencia

	public List<User> findAll() {// reponsavel por retornar todos os ususario do BD
		return repo.findAll();
	}

	public User findById(String id) {
	/*	User user = repo.findById(id);

		if (user == null) {
			throw new ObjectNotFoundException("Object not found!!");
		}*/
		
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
		
	}

}