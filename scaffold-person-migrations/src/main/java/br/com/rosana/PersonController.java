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

import br.com.rosana.data.vo.PersonVO;
import br.com.rosana.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

		@Autowired
		private PersonServices services;
		

		@GetMapping
		public List<PersonVO> mostraTodos() {
			return services.findAll();
		}
		
		@GetMapping("/{id}")
		public PersonVO mostraUm(@PathVariable("id")Long id) {
			return services.findById(id);
		}
		
		@PostMapping
		public PersonVO poeNoBanco(@RequestBody PersonVO person) {
			return services.create(person);
		}
		
		@PutMapping	
		public PersonVO atualizaNoBanco(@RequestBody PersonVO person) {
			return services.update(person);
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deletaUm(@PathVariable("id") Long id) {
			services.delete(id);
			return ResponseEntity.ok().build();
}
		
}