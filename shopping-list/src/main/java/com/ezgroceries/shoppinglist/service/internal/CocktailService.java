package com.ezgroceries.shoppinglist.service.internal;

import com.ezgroceries.shoppinglist.web.controller.api.cocktail.dto.CocktailResource;

import java.util.List;

public interface CocktailService {
	List<CocktailResource> searchCocktails(String search);
}
