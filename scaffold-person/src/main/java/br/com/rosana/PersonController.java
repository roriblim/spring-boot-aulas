package br.com.rosana;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rosana.exception.UnsuportedMathOperationException;
import br.com.rosana.model.Person;
import br.com.rosana.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

		@Autowired
		private PersonServices services;
		
		@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public Person findById(@PathVariable("id")String id) {
			return services.findById(id);
		}
		
		@RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Person> findAll() {
			return services.findAll();
		}
		
}