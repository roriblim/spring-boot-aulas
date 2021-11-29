package br.com.rosana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rosana.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	//QUERY QUE PODEMOS USAR NO SPRING DATA:
	//JPA QUERY LANGUAGE (JPQL)
	@Modifying
	@Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")// vai buscar as entradas de User em que userName seja o userName recebido como parâmetro (abaixo)
	void disablePerson(@Param("id") Long id);
	
	//note que outro jeito que eu poderia fazer seria utilizar o método de update que já criamos no PersonService, ou parecido, mas queremos mostrar outro jeito aqui
}
