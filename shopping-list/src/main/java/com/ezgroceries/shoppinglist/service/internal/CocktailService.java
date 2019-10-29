package com.ezgroceries.shoppinglist.service.internal;

import com.ezgroceries.shoppinglist.model.CocktailResource;

import java.util.List;

public interface CocktailService {
	List<CocktailResource> searchCocktails(String search);
}
