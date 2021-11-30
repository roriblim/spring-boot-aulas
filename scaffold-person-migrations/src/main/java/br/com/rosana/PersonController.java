package br.com.rosana;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rosana.data.vo.PersonVO;
import br.com.rosana.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//documentacao: localhost:8080/swagger-ui.html#/person-controller

//@CrossOrigin  //descomente essa linha para usar a annotation
//a gente pode tanto fazer o cors por aqui, utilizando @CrossOrigin na classe ou nos métodos que a gente quiser,
//ou adicionar globalmente, fazendo a configuração no WebConfig
@Tag(name = "Person Endpoint",description = "Endpoint de pessoas")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

		@Autowired
		private PersonServices services;
		
		 @Autowired
		private PagedResourcesAssembler<PersonVO> pagedResourcesAssembler;
		
		
		//com o uso da paginação, posso fazer o request por exemplo com a seguinte url: {{host}}/api/person/v1?page=10&limit=5
		@Operation (summary = "find all people")
		@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
		public ResponseEntity<PagedModel<EntityModel<PersonVO>>> mostraTodos(@RequestParam(value = "page", defaultValue="0") int page,
										  @RequestParam(value = "limit", defaultValue="20") int limit,
										  @RequestParam(value = "direction", defaultValue="asc") String direction
										) {
			
			var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC :Direction.ASC;
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"firstName"));
			
			Page<PersonVO> people = services.findAll(pageable);
			
			people.stream().forEach(p->p.add(linkTo(methodOn(PersonController.class).mostraUm(p.getKey())).withSelfRel()));
			
			PagedModel<EntityModel<PersonVO>> peoplePagedModel = pagedResourcesAssembler.toModel(people);
					
			 return ResponseEntity.ok(peoplePagedModel);
			 
			
		}
		
		@CrossOrigin
		@Operation (summary = "find one specific person")
		@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
		public PersonVO mostraUm(@PathVariable("id")Long id) {
			PersonVO personVO = services.findById(id);
			personVO.add(linkTo(methodOn(PersonController.class).mostraUm(id)).withSelfRel());
			return personVO;
		}
		
		@Operation (summary = "Disable one specific person")
		@PatchMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
		public PersonVO desabilitaUm(@PathVariable("id")Long id) {
			PersonVO personVO = services.disablePerson(id);
			personVO.add(linkTo(methodOn(PersonController.class).mostraUm(id)).withSelfRel());
			return personVO;
		}
		
		@Operation (summary = "post one specific person")
		@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
				consumes = {"application/json", "application/xml", "application/x-yaml"})
		public PersonVO poeNoBanco(@RequestBody PersonVO person) {
			PersonVO personVO = services.create(person);
			personVO.add(linkTo(methodOn(PersonController.class).mostraUm(personVO.getKey())).withSelfRel());
			return personVO;
		}
		
		@Operation (summary = "update one specific person")
		@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
				consumes = {"application/json", "application/xml", "application/x-yaml"})	
		public PersonVO atualizaNoBanco(@RequestBody PersonVO person) {
			PersonVO personVO = services.update(person);
			personVO.add(linkTo(methodOn(PersonController.class).mostraUm(personVO.getKey())).withSelfRel());
			//obs. aqui em cima, poderia colocar tb person.getkey() no findbyid, pois o parametro de entrada person é do tipo personvo e já tem o id nesse caso.
			return personVO;
		}
		
		@Operation (summary = "delete one specific person")
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deletaUm(@PathVariable("id") Long id) {
			services.delete(id);
			return ResponseEntity.ok().build();
}
		
}