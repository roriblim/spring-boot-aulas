package br.com.rosana.data.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * TABLE `books` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `author` longtext,
  `launch_date` datetime(6) NOT NULL,
  `price` decimal(65,2) NOT NULL,
  `title` longtext
) 
 * */

@Entity
@Table(name = "books") //essa anotation pemite indicar a qual tabela no bando essa entidade corresponde
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //SIGNIFICA que o próprio hibernate vai gerar os valores de ID para mim.
	private long id;
	
	@Column(name = "author", nullable = false, length = 180) //para identificar a qual coluna da tabela no banco de dados cada atributo se refere
	private String author;
	
	@Column(name = "launch_date", nullable = false, length = 50) 
	@Temporal(TemporalType.DATE)
	private Date launchDate;
	
	@Column(nullable = false) 
	private Double price;
	
	@Column(nullable = false, length=250) 
	private String title;
	
	
	public Book() {
		
	}


	public long getId() {
		return id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public Date getLaunchDate() {
		return launchDate;
	}


	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setId(long id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		return Objects.hash(author, id, launchDate, price, title);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && id == other.id && Objects.equals(launchDate, other.launchDate)
				&& Objects.equals(price, other.price) && Objects.equals(title, other.title);
	}



}
