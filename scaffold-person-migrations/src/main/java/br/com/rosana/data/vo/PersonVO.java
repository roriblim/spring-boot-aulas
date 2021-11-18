package br.com.rosana.data.vo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//a seguinte annotation permite fazer a customização da ordem de como o JSon vai chegar no usuário final
@JsonPropertyOrder({"id", "address", "lastName","gender", "firstName"})
public class PersonVO implements Serializable{

	private static final long serialVersionUID = 1L;

	//tudo que for determinado aqui vai ser da forma que chega no usuário final!!!
	private long id;
	
	//essa anotation permite mudar como o json é serializado e aparece para o usuário
	@JsonProperty("Primeiro_Nome")
	private String firstName;
	
	@JsonProperty("Ultimo_Nome")
	private String lastName;
	
	@JsonProperty("Endereço")
	private String address;
	
	//essa anotation permite ocultar a variável no json
	@JsonIgnore
	private String gender;
	
	public PersonVO() {
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
		PersonVO other = (PersonVO) obj;
		return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && id == other.id && Objects.equals(lastName, other.lastName);
	}
	
	
}
