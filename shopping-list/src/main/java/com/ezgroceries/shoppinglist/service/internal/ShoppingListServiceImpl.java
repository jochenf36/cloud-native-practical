package com.ezgroceries.shoppinglist.service.internal;

import com.ezgroceries.shoppinglist.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.persistence.entity.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.repository.CocktailRepository;
import com.ezgroceries.shoppinglist.persistence.entity.ShoppingListEntity;
import com.ezgroceries.shoppinglist.persistence.repository.ShoppingListRepository;
import com.ezgroceries.shoppinglist.web.controller.api.cocktail.dto.CocktailReference;
import com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.dto.ShoppingList;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShoppingListServiceImpl implements  ShoppingListService{

	private final ShoppingListRepository shoppingListRepository;
	private final CocktailRepository cocktailRepository;

	public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository, CocktailRepository cocktailRepository) {
		this.shoppingListRepository = shoppingListRepository;
		this.cocktailRepository = cocktailRepository;
	}

	@Override
	public ShoppingListResource createShoppingList(String shoppingList) {
		ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
		shoppingListEntity.setId(UUID.randomUUID());
		shoppingListEntity.setName(shoppingList);
		ShoppingListEntity shoppingListEntityNew = shoppingListRepository.save(shoppingListEntity);

		ShoppingListResource shoppingListResource = new ShoppingListResource(shoppingListEntityNew.getId(), shoppingListEntityNew.getName());

		return shoppingListResource;
	}

	@Override
	public List<CocktailReference> addCocktails(String shoppingListId, List<CocktailReference> cocktailReferences) {
		ShoppingListEntity shoppingListEntity = shoppingListRepository.findById(UUID.fromString(shoppingListId));
		List<CocktailEntity> cocktails = cocktailRepository.findByIdDrinkIn(cocktailReferences.stream().map(ref -> ref.cocktailId).collect(Collectors.toList()));
		shoppingListEntity.getCocktails().addAll(cocktails);

		shoppingListRepository.save(shoppingListEntity);

		return shoppingListEntity.getCocktails().stream().map(c -> new CocktailReference(c.getIdDrink())).collect(Collectors.toList());
	}

	@Override
	public ShoppingList getShoppingList(String id) {
		ShoppingListEntity shoppingListEntity = shoppingListRepository.findById(UUID.fromString(id));
		return createShoppingList(shoppingListEntity);
	}

	@Override
	public List<ShoppingList> getAllShoppingLists() {
		return shoppingListRepository.findAll().stream().map(x -> createShoppingList(x)).collect(Collectors.toList());
	}

	private ShoppingList createShoppingList(ShoppingListEntity shoppingListEntity){
		Set<CocktailEntity> cocktails = shoppingListEntity.getCocktails();
		List<String> ingredients = cocktails.stream().flatMap(x -> x.getIngredients().stream()).collect(Collectors.toList()); ;
		return new ShoppingList(shoppingListEntity.getId().toString(), shoppingListEntity.getName(), ingredients);
	}
}
