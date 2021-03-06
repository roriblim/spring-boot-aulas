package br.com.rosana.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		//as manipulações de dados padrão do Spring Data, o Spring Data vai cuidar da transação
		//para dizer para ele que uma operação deve ser transacionada, colocamos a annotation: @Transactional
		//essa annotation permite um controle de transações robusto com o BD, conforme os princípios ACID (atomicidade, consistência, isolação, durabilidade)
		@Transactional		
		public PersonVO disablePerson(Long id) {
		repository.disablePerson(id);
		//a linha de cima é suficiente, mas podemos retornar o registro que foi alterado ao usuário:
		var entity = repository.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		//OUTRO JEITO DE FAZER SERIA:
		/*
		 * 	entity.setEnabled(false);			
			var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
			return vo;
		 */
		
		return DozerConverter.parseObject(entity, PersonVO.class);
			
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
		
		public Page<PersonVO> findAll(Pageable pageable) {
			var entities = repository.findAll(pageable); //vai ser uma page assim
			return entities.map(this::convertToPersonVO); //converte uma page de Person para uma page de PersonVO?
		}
		private PersonVO convertToPersonVO(Person entity) {
			return DozerConverter.parseObject(entity, PersonVO.class);
		}
		

	}

