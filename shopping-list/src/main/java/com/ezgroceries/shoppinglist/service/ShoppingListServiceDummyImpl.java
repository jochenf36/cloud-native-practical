package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.web.controller.api.cocktail.model.CocktailReference;
import com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.model.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListServiceDummyImpl implements ShoppingListService {
	public ShoppingListResource createShoppingList(String shoppingList) {
		return new ShoppingListResource(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), shoppingList);
	}

	public List<CocktailReference> addCocktails(String shoppingListId, List<CocktailReference> cocktailReferences) {
		return cocktailReferences;
	}

	public ShoppingList getShoppingList(String id) {
		return new ShoppingList(
				"90689338-499a-4c49-af90-f1e73068ad4f",
				"Stephanie's birthday",
				Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"));
	}

	public List<ShoppingList> getAllShoppingLists() {
		return Arrays.asList(new ShoppingList(
						"90689338-499a-4c49-af90-f1e73068ad4f",
						"Stephanie's birthday",
						Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao")),
				new ShoppingList(
						"6c7d09c2-8a25-4d54-a979-25ae779d2465",
						"My Birthday",
						Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao")));
	}
}
