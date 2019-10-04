package com.ezgroceries.shoppinglist.web.controller.api.cocktail;

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {
	private CocktailService cocktailService;

	@Autowired
	public CocktailController(CocktailService cocktailService) {
		this.cocktailService = cocktailService;
	}

	@GetMapping
	public Resources<CocktailResource> get(@RequestParam String search) {
		return new Resources<>(cocktailService.searchCocktails(search));
	}
}