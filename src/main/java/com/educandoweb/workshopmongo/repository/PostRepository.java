package com.educandoweb.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.educandoweb.workshopmongo.domain.Post;

@Repository // implementar o respository utilizando o spring data
public interface PostRepository extends MongoRepository<Post, String> { // extends ela vai herdar extender da classe
																		// mongodb
	// MongoRepository<T, ID> T --> tipo da classe de dominio aqui é o User
	// ID --> tipo do id String
	// com isso vai herdar varios metodos ou fazer varos operações

}
