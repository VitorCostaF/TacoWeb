package br.com.vitor.studies.spring.tacos.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.vitor.studies.spring.tacos.model.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long>{
	
	@Modifying
	@Query(value = "insert into taco_ingredient (taco_id, ingredient_id) values ( ?1 , ?2 )", nativeQuery = true)
	@Transactional
	public void saveTacoRelationIngredient(Long tacoId, String ingredientId);
	
}
