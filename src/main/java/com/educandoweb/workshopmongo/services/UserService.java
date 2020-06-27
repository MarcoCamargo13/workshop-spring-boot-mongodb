package com.educandoweb.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.repository.UserRepository;

@Service //para falar para o Spring que esta classe será um serviço e sera enjetada em outras classe
public class UserService {//classe responsavel para trabalhar com ususario
	
	@Autowired //instanciar automaticamento, com isso o Spring vai procurar o objeto e fazer tudo automatico
	private UserRepository repo;//instanciar um objeto userRepository //injeção de dependencia
	
	public List<User> findAll(){//reponsavel por retornar todos os ususario do BD
		return repo.findAll();
	}

}