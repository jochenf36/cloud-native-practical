package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.thirdPartyClients.cocktail.CocktailDBClient;
import com.ezgroceries.shoppinglist.thirdPartyClients.cocktail.model.CocktailDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CocktailServiceCocktailDBImpl implements CocktailService {

	@Autowired
	private CocktailDBClient cocktailDBClient;

	public List<CocktailResource> searchCocktails(String search) {
		CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
		return cocktailDBResponse.getDrinks().stream().map(
				c -> new CocktailResource(
						UUID.randomUUID(),
						c.getStrDrink(),
						c.getStrGlass(),
						c.getStrInstructions(),
						c.getStrDrinkThumb(),
						Arrays.asList(c.getStrIngredient1(), c.getStrIngredient2(), c.getStrIngredient3()))).collect(Collectors.toList());
	}
}
