package br.com.rosana;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rosana.model.Person;
import br.com.rosana.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

		@Autowired
		private PersonServices services;
		
		@RequestMapping( method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Person> mostraTodos() {
			return services.findAll();
		}
		
		@RequestMapping(value = "/{id}", 
						method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
		public Person mostraUm(@PathVariable("id")String id) {
			return services.findById(id);
		}
		
		//NOTA: O POST NAO PODE SER USADO DIRETAMENTE NO BROWSER SEM OUTRA TECNOLOGIA.
		//PARA TESTÁ-LO, USAMOS O POSTMAN
		@RequestMapping(method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE) //vai nao apenas produzir, mas tbm consumir json
		public Person poeNoBanco(@RequestBody Person person) {
			return services.create(person);
		}
		
		//NOTA: O PUT NAO PODE SER USADO DIRETAMENTE NO BROWSER SEM OUTRA TECNOLOGIA.
		//PARA TESTÁ-LO, USAMOS O POSTMAN
		@RequestMapping(method = RequestMethod.PUT, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE) //vai nao apenas produzir, mas tbm consumir json
		public Person atualizaNoBanco(@RequestBody Person person) {
			return services.update(person);
		}
		
		@RequestMapping(value = "/{id}", 
				method = RequestMethod.DELETE)
		public void deletaUm(@PathVariable("id")String id) {
			services.delete(id);
}
		
}