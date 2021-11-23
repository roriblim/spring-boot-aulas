package br.com.rosana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rosana.data.model.Person;
import br.com.rosana.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

	//o spring data consegue fazer a busca para a gente apenas com isso:
	//JPA QUERY LANGUAGE (JPQL)
	@Query("SELECT u FROM User u where u.userName =:userName")// vai buscar as entradas de User em que userName seja o userName recebido como parâmetro (abaixo)
	User findByUserName(@Param("userName") String userName);
	
}
