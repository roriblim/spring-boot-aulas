package br.com.rosana.data.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person") //essa anotation pemite indicar a qual tabela no bando essa entidade corresponde
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;

	//vou identificar essa variável id como o Id
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //SIGNIFICA que o próprio hibernate vai gerar os valores de ID para mim.
	private long id;
	
	@Column(name = "first_name", nullable = false, length = 50) //para identificar a qual coluna da tabela no banco de dados cada atributo se refere
	private String firstName;
	
	@Column(name = "lastt_name", nullable = false, length = 50) 
	private String lastName;
	
	@Column(nullable = false, length=100) 
	private String address;
	
	@Column(nullable = false, length=6) 
	private String gender;
	
	//obs.: se a coluna já não existir no banco, ele vai criar, então pode ser uma boa prática colocar um length
	
	
	public Person() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public int hashCode() {
		return Objects.hash(address, firstName, gender, id, lastName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && id == other.id && Objects.equals(lastName, other.lastName);
	}
	
	
}
