package com.ezgroceries.shoppinglist.web.controller.api.shoppinglist;

import com.ezgroceries.shoppinglist.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import com.ezgroceries.shoppinglist.web.controller.api.cocktail.model.CocktailReference;
import com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.model.ShoppingListReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
public class ShoppingListController {

	private ShoppingListService shoppingListService;

	@Autowired
	public ShoppingListController(ShoppingListService shoppingListService) {
		this.shoppingListService = shoppingListService;
	}

	@GetMapping(value = "/shopping-lists/{shoppingListId}")
	public Resource<ShoppingList> get(@PathVariable String shoppingListId) {
		return new Resource<>(shoppingListService.getShoppingList(shoppingListId));
	}

	@GetMapping(value = "/shopping-lists")
	public Resources<ShoppingList> getAll() {
		return new Resources<>(shoppingListService.getAllShoppingLists());
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/shopping-lists")
	public Resource<ShoppingListResource> post(@RequestBody ShoppingListReference shoppingList) {
		return new Resource<>(shoppingListService.createShoppingList(shoppingList.name));
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
	public Resources<CocktailReference> addCocktails(@PathVariable String shoppingListId, @RequestBody List<CocktailReference> cocktailReferences) {
		return new Resources<>(shoppingListService.addCocktails(shoppingListId, cocktailReferences));
	}
}
