package br.com.rosana.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rosana.data.model.Person;
import br.com.rosana.exception.ResourceNotFoundException;
import br.com.rosana.repository.PersonRepository;

    //@service é usado para que o spring cuide da injeção de dependência dessa classe.
	//quando for usar essa classe em outra classe, nao precisa dar um new, nem deixar isso static
	@Service
	public class PersonServices{
		
		@Autowired
		PersonRepository repository;
		
		public Person create(Person person) {
			return repository.save(person);
			/*lá no controller é chamado o create person, e aqui então é feita a persistência do dado na base de dados,
			 * e depois o resultado é retornado de volta ao controller, que retorna ao json*/
		}
		
		public Person update(Person person) {
			
			Person entity = repository.findById(person.getId())
			         .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));	
			entity.setFirstName(person.getFirstName());
			entity.setLastName(person.getLastName());
			entity.setAddress(person.getAddress());
			entity.setGender(person.getGender());
			return repository.save(entity);
			/*lá no controller é chamado o update person, e aqui então é feita a persistência do dado na base de dados,
			 * e depois o resultado é retornado de volta ao controller, que retorna ao json*/
		}
		
		public void delete (Long id){
			Person entity = repository.findById(id)
			         .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
			repository.delete(entity);
			//recbe o id, vai até a base de dados, se existir o id, deleta, se não, retorna uma exceção
		}
		
		public Person findById(Long id) {
		return repository.findById(id)
				         .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
			
		}
		
		public List<Person> findAll() {
			return repository.findAll();
		}
		

	}

