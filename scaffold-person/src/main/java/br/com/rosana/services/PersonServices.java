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

