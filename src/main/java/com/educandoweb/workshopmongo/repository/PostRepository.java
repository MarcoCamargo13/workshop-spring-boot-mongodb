package com.educandoweb.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.educandoweb.workshopmongo.domain.Post;

@Repository // implementar o respository utilizando o spring data
public interface PostRepository extends MongoRepository<Post, String> { // extends ela vai herdar extender da classe
																		// mongodb
	// MongoRepository<T, ID> T --> tipo da classe de dominio aqui é o User
	// ID --> tipo do id String
	// com isso vai herdar varios metodos ou fazer varos operações
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	 //documento de referencia : https://docs.mongodb.com/manual/reference/operator/query/regex/
	//@Query("{ <field>: { $regex: /pattern/, $options: '<options>' } }")
	//field>: qual campo de busca
	//$regex: /pattern/ indica o parametro Ex: ?0 1ª paramentro
	 //$options: '<options>' no dicumento indica i para ignorar maiscula e minuscula
	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
	List<Post> findTitle(String text);
	
	
	//documento de referencia : https://docs.mongodb.com/manual/reference/operator/query/regex/
	//{field: {$gte: value} } //campo field Date e gte : párametro mindate ?1
	// $or: [ { <expression1> }, { <expression2> }, ... , { <expressionN> } ] vem da documentação vai procurar nos campos title, body e comments é uma lista comments.text indica q ira procura dentro do text
	@Query("{ $and: [ {date: {$gte: ?1} } , { date: { $lte: ?2} } , {  $or: [ { 'title' : { $regex: ?0, $options: 'i' } }, { 'body' : { $regex: ?0, $options: 'i' }  },  { 'comments.text' : { $regex: ?0, $options: 'i' } } ] ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	

}
