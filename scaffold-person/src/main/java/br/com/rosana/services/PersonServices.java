package br.com.rosana.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.rosana.model.Person;

    //@service é usado para que o spring cuide da injeção de dependência dessa classe.
	//quando for usar essa classe em outra classe, nao precisa dar um new, nem deixar isso static
	@Service
	public class PersonServices{
		
		private final AtomicLong counter = new AtomicLong();
		
		public Person create(Person person) {
			return person;
			/*lá no controller é chamado o create person, e aqui então é feita a persistência do dado na base de dados,
			 * e depois o resultado é retornado de volta ao controller, que retorna ao json
			 * como ainda nao vimos a base de dados, estamos por enquanto simulando essa parte*/
		}
		
		public Person update(Person person) {
			//precisa do ID para ele conseguir fazer o update
			return person;
			/*lá no controller é chamado o update person, e aqui então é feita a persistência do dado na base de dados,
			 * e depois o resultado é retornado de volta ao controller, que retorna ao json
			 * como ainda nao vimos a base de dados, estamos por enquanto simulando essa parte*/
		}
		
		public void delete (String id) {
			//recbe o id, vai até a base de dados, se existir o id, deleta, se não, retorna uma exceção
		}
		
		public Person findById(String id) {
			//aqui é só um mock, porque não temos ainda acesso ao BD
			Person person = new Person();
			person.setId(counter.incrementAndGet());	
			person.setFirstName("Leandro");
			person.setLastName("Costa");
			person.setAddress("Uberlândia - Minas Gerais - Brasil");
			person.setGender("Male");
			return person ;
		}
		
		public List<Person> findAll() {
			//aqui é só um mock, porque não temos ainda acesso ao BD
			List<Person> persons = new ArrayList<Person>();
			for (int i=0; i<8;i++) {
				Person person = mockPerson(i);
				persons.add(person);
			}
			return persons ;
		}
		
		public Person mockPerson(int i) {
			//aqui é só um mock, porque não temos ainda acesso ao BD
			Person person = new Person();
			person.setId(counter.incrementAndGet());	
			person.setFirstName("Fulano de tal" + i);
			person.setLastName("da Silvaaaaa" + i);
			person.setAddress("Algum lugar - Brasil" + i);
			person.setGender("Male");
			return person ;
		}
	}

