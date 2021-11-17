package br.com.rosana;

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

import br.com.rosana.model.Person;
import br.com.rosana.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

		@Autowired
		private PersonServices services;
		
		 
		//produces = MediaType.APPLICATION_JSON_VALUE e consumes = MediaType.APPLICATION_JSON_VALUE
		//são feitos por default no requestMapping, entao posso tirá-los.
		
		//posso substituir  @RequestMapping( method = RequestMethod.GET) por @GetMapping
		//posso substituir @RequestMapping(value = "/{id}", method = RequestMethod.GET) por @GetMapping("/{id}")
		//POSSO SUBSTITUIR @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) POR @DeleteMapping("/{id}")		
		//posso substituir @RequestMapping(method = RequestMethod.POST)  por @PostMapping
		//posso substituir @RequestMapping(method = RequestMethod.PUT) por @PutMapping		
		
		@GetMapping
		public List<Person> mostraTodos() {
			return services.findAll();
		}
		
		@GetMapping("/{id}")
		public Person mostraUm(@PathVariable("id")Long id) {
			return services.findById(id);
		}
		
		@PostMapping
		public Person poeNoBanco(@RequestBody Person person) {
			return services.create(person);
		}
		
		@PutMapping	
		public Person atualizaNoBanco(@RequestBody Person person) {
			return services.update(person);
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deletaUm(@PathVariable("id") Long id) {
			services.delete(id);
			return ResponseEntity.ok().build();
}
		
}