package br.com.rosana.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.rosana.data.model.Person;
import br.com.rosana.data.vo.v2.PersonVOv2;

@Service
public class PersonConverter {
		
		public PersonVOv2 convertEntityToVOv2(Person person) {
			PersonVOv2 vo = new PersonVOv2();
			vo.setId(person.getId());
			vo.setAddress(person.getAddress());
			vo.setBirthday(new Date());  
			//aqui é apenas um exemplo. na vida real, se eu adicionasse uma coluna no BD, adicionaria tb o atributo na classe Person
			//e assim poderia setar o birthday de vo como o getbirthday de person.
			vo.setFirstName(person.getFirstName());
			vo.setLastName(person.getLastName());
			vo.setGender(person.getGender());
			return vo;
		}	
		
		public Person convertVOv2ToEntity(PersonVOv2 person) {
			Person entity = new Person();
			entity.setId(person.getId());
			entity.setAddress(person.getAddress());
			entity.setFirstName(person.getFirstName());
			entity.setLastName(person.getLastName());
			entity.setGender(person.getGender());
			return entity;
		}


}
