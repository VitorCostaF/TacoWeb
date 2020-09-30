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
import br.com.vitor.studies.spring.tacos.model.Taco;
import br.com.vitor.studies.spring.tacos.persistence.OrderRepository;
import br.com.vitor.studies.spring.tacos.persistence.TacoRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@Data
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private TacoRepository tacoRepository;

	private Order order;

	@GetMapping("/current")
	public String orderForm(Model model, @ModelAttribute Order order) {
		model.addAttribute("order", order);
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {
		
		List<Taco> tacosList = order.getTacos();
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		for(String tacoName : order.getTacosExclude()) {
			tacosList.remove(Taco.findTacoByName(tacosList, tacoName));
		}
		
		orderRepository.save(order);

		for (Taco taco : order.getTacos()) {
			for (Ingredient ingredient : taco.getIngredients()) {
				tacoRepository.saveTacoRelationIngredient(taco.getId(), ingredient.getId());
			}
			taco.setOrder(order);
		}
		
		orderRepository.save(order);
		
		initOrder(order);

		log.info("processing order");
		return "redirect:/";
	}

	private void initOrder(Order order) {
		order.setTacos(new ArrayList<>());
		order.setTacosExclude(new ArrayList<>());
		order.setId(null);
		order.setCcCVV(null);
		order.setCcExpiration(null);
		order.setCcNumber(null);
		order.setCity(null);
		order.setCustomer(null);
		order.setState(null);
		order.setStreet(null);
		order.setZip(null);
	}
}
