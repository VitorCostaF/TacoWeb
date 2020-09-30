package br.com.vitor.studies.spring.tacos.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.vitor.studies.spring.tacos.model.Ingredient;
import br.com.vitor.studies.spring.tacos.model.Order;
import br.com.vitor.studies.spring.tacos.model.Ingredient.Type;
import br.com.vitor.studies.spring.tacos.model.Taco;
import br.com.vitor.studies.spring.tacos.persistence.IngredientRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Data
public class DesignTacoController {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	private Iterable<Ingredient> ingredients;
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@GetMapping
	public String showDesignForm(Model model) {

		log.info("rendering design form");
		
		if(ingredients == null)
			ingredients = ingredientRepository.findAll();
		
		Type[] types = Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterbyType(ingredients, type));
		}
		model.addAttribute("taco", new Taco());

		return "design";
	}

	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {

		if (errors.hasErrors()) {
			return "redirect:/design";
		}
		
		order.getTacos().add(taco);
		
		if(ingredients == null)
			ingredients = ingredientRepository.findAll();
		
		for(String ingredientId : taco.getIngredientsId()) {
			taco.getIngredients().add(filterByIngredientId(ingredients, ingredientId));
		}

		log.info("processing taco: " + taco);
		return "redirect:/orders/current";

	}
	
	private Ingredient filterByIngredientId(Iterable<Ingredient> list, String id) {
		Ingredient foundIngredient = null;
		for(Ingredient ingredient : list) {
			if(ingredient.getId().equals(id)) {
				foundIngredient = ingredient;
				break;
			}
		}
		return foundIngredient;
	}

	private List<Ingredient> filterbyType(Iterable<Ingredient> ingredientList, Type type) {
		List<Ingredient> filteredList = new ArrayList<>();

		for (Ingredient ingredient : ingredientList) {
			if (ingredient.getType() == type) {
				filteredList.add(ingredient);
			}
		}

		return filteredList;

	}
}
