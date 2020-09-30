package br.com.vitor.studies.spring.tacos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	private Date createdAt;

	@Transient
	private List<Ingredient> ingredients = new ArrayList<>();
	
	@Transient
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<String> ingredientsId;

	@ManyToOne(cascade = CascadeType.ALL)
	private Order order;
	
	@Transient
	private boolean exclude = false;

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
	
	public static Taco findTacoByName(List<Taco> tacoList, String tacoName) {
		Taco foundTaco = null;
		for(Taco taco : tacoList) {
			if(taco.getName().equals(tacoName)) {
				foundTaco = taco;
				break;
			}
		}
		return foundTaco;
	}

}
