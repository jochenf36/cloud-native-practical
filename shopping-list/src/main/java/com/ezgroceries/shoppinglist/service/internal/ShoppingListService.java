package com.ezgroceries.shoppinglist.service.internal;

import com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.web.controller.api.cocktail.dto.CocktailReference;
import com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.dto.ShoppingList;

import java.util.List;

public interface ShoppingListService {
	ShoppingListResource createShoppingList(String shoppingList);

	List<CocktailReference> addCocktails(String shoppingListId, List<CocktailReference> cocktailReferences);

	ShoppingList getShoppingList(String id);

	List<ShoppingList> getAllShoppingLists();
}
