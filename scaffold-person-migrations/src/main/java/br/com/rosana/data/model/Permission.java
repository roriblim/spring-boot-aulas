package br.com.rosana.data.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority, Serializable{
//note que é uma entidade normal do HIBERNATE, com a particularidade de que devemos implementar
	//a interface GrantedAuthority do SpringSecurity.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") //essa linha aqui não é mandatória, nesse caso
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description") 
	private String description;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.description;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

}
