package br.com.rosana;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rosana.data.vo.PersonVO;
import br.com.rosana.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//documentacao: localhost:8080/swagger-ui.html#/person-controller

//@Api(value = "PersonEndpoint", description = "REST API for Person", tags = { "PersonEndpoint" })
@Tag(name = "Person Endpoint",description = "Endpoint de pessoas")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

		@Autowired
		private PersonServices services;
		
		@Operation (summary = "find all people")
		@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
		public List<PersonVO> mostraTodos() {
			List<PersonVO> people = services.findAll();
			people.stream().forEach(p->p.add(linkTo(methodOn(PersonController.class).mostraUm(p.getKey())).withSelfRel()));
			return people;
		}
		
		@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
		public PersonVO mostraUm(@PathVariable("id")Long id) {
			PersonVO personVO = services.findById(id);
			personVO.add(linkTo(methodOn(PersonController.class).mostraUm(id)).withSelfRel());
			return personVO;
		}
		
		@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
				consumes = {"application/json", "application/xml", "application/x-yaml"})
		public PersonVO poeNoBanco(@RequestBody PersonVO person) {
			PersonVO personVO = services.create(person);
			personVO.add(linkTo(methodOn(PersonController.class).mostraUm(personVO.getKey())).withSelfRel());
			return personVO;
		}
		
		@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
				consumes = {"application/json", "application/xml", "application/x-yaml"})	
		public PersonVO atualizaNoBanco(@RequestBody PersonVO person) {
			PersonVO personVO = services.update(person);
			personVO.add(linkTo(methodOn(PersonController.class).mostraUm(personVO.getKey())).withSelfRel());
			//obs. aqui em cima, poderia colocar tb person.getkey() no findbyid, pois o parametro de entrada person é do tipo personvo e já tem o id nesse caso.
			return personVO;
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deletaUm(@PathVariable("id") Long id) {
			services.delete(id);
			return ResponseEntity.ok().build();
}
		
}