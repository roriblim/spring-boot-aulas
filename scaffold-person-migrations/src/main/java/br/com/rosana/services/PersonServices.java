package br.com.rosana.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rosana.converter.DozerConverter;
import br.com.rosana.data.model.Person;
import br.com.rosana.data.vo.PersonVO;
import br.com.rosana.exception.ResourceNotFoundException;
import br.com.rosana.repository.PersonRepository;

    //@service é usado para que o spring cuide da injeção de dependência dessa classe.
	//quando for usar essa classe em outra classe, nao precisa dar um new, nem deixar isso static
	@Service
	public class PersonServices{
		
		@Autowired
		PersonRepository repository;
		
		public PersonVO create(PersonVO person) {
			//note que eu posso declarar entity como Person e vo como PersonVO, m
			//as posso tbm usar o var, e o Java vai ajustar o tipo
			var entity = DozerConverter.parseObject(person, Person.class); //vai mudar person de PersonVO para a classe de Person e gravar em entity
			var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class); //salvo no repositório e converto novamente para retornar ao usuário como VO
			return vo;
			}
		
		public PersonVO update(PersonVO person) {
			
			var entity = repository.findById(person.getKey())
			         .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));	
			entity.setFirstName(person.getFirstName());
			entity.setLastName(person.getLastName());
			entity.setAddress(person.getAddress());
			entity.setGender(person.getGender());			
			var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
			return vo;
		}
		
		public void delete (Long id){
			Person entity = repository.findById(id)
			         .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
			repository.delete(entity);
			//recbe o id, vai até a base de dados, se existir o id, deleta, se não, retorna uma exceção
		}
		
		public PersonVO findById(Long id) {
		var entity = repository.findById(id)
				         .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonVO.class);
			
		}
		
		public List<PersonVO> findAll() {
			return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
		}
		

	}

