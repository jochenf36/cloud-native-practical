package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.web.controller.api.cocktail.model.CocktailReference;
import com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.model.ShoppingList;

import java.util.List;

public interface ShoppingListService {
	ShoppingListResource createShoppingList(String shoppingList);

	List<CocktailReference> addCocktails(String shoppingListId, List<CocktailReference> cocktailReferences);

	ShoppingList getShoppingList(String id);

	List<ShoppingList> getAllShoppingLists();
}
