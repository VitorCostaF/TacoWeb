package br.com.vitor.studies.spring.tacos.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.vitor.studies.spring.tacos.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
