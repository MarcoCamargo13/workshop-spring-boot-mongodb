package com.educandoweb.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.resources.util.URL;
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

	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET) //busca com caminha titlesearch
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) { //define os parametros para a busca do metodo findByTitle
		text = URL.decodeParam(text); //codifica o texto
		List<Post> list = service.findTitle(text); //procura o texto ignorando minuscula e maiscula IgnoreCase
		return ResponseEntity.ok().body(list);// instancia o ResponseEntity com o codigo de resposta
	}

	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET) //busca com caminha titlesearch
	public ResponseEntity<List<Post>> fullsearch(
			@RequestParam(value="text", defaultValue="") String text, 
	        @RequestParam(value="minDate", defaultValue="") String minDate,
            @RequestParam(value="maxDate", defaultValue="") String maxDate) 
	{ //define os parametros para a busca do metodo findByTitle
		text = URL.decodeParam(text); //codifica o texto
		Date min = URL.convertDate(minDate, new Date(0L)); //conversão de data minima
		Date max = URL.convertDate(maxDate, new Date()); //conversão de data minima
		List<Post> list = service.fullSearch(text, min, max); //procura o texto ignorando minuscula e maiscula IgnoreCase
		return ResponseEntity.ok().body(list);// instancia o ResponseEntity com o codigo de resposta
	}

}
