package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.repository.cocktail.CocktailEntity;
import com.ezgroceries.shoppinglist.repository.cocktail.CocktailRepository;
import com.ezgroceries.shoppinglist.thirdPartyClients.cocktail.CocktailDBClient;
import com.ezgroceries.shoppinglist.thirdPartyClients.cocktail.model.CocktailDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CocktailServiceImpl implements CocktailService {
	private CocktailDBClient cocktailDBClient;
	private CocktailRepository cocktailRepository;

	@Autowired
	public CocktailServiceImpl(CocktailDBClient cocktailDBClient, CocktailRepository cocktailRepository){
		this.cocktailDBClient = cocktailDBClient;
		this.cocktailRepository = cocktailRepository;
	}

	public List<CocktailResource> searchCocktails(String search) {
		CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
		List<CocktailDBResponse.DrinkResource> drinks = cocktailDBResponse.getDrinks();

		mergeCocktails(drinks);

		return drinks.stream().map(
				c -> new CocktailResource(
						UUID.randomUUID(),
						c.getStrDrink(),
						c.getStrGlass(),
						c.getStrInstructions(),
						c.getStrDrinkThumb(),
						Arrays.asList(c.getStrIngredient1(), c.getStrIngredient2(), c.getStrIngredient3()))).collect(Collectors.toList());
	}

	public List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
		//Get all the idDrink attributes
		List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());

		//Get all the ones we already have from our DB, use a Map for convenient lookup
		Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream().collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

		//Stream over all the drinks, map them to the existing ones, persist a new one if not existing
		Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
			CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
			if (cocktailEntity == null) {
				CocktailEntity newCocktailEntity = new CocktailEntity();
				newCocktailEntity.setId(UUID.randomUUID());
				newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
				newCocktailEntity.setName(drinkResource.getStrDrink());
				newCocktailEntity.setIngredients(new HashSet(getIngredients(drinkResource)));
				cocktailEntity = cocktailRepository.save(newCocktailEntity);
			}
			return cocktailEntity;
		}).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

		//Merge drinks and our entities, transform to CocktailResource instances
		return mergeAndTransform(drinks, allEntityMap);
	}

	private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
		return drinks.stream().map(drinkResource -> new CocktailResource(
				allEntityMap.get(drinkResource.getIdDrink()).getId(),
				drinkResource.getStrDrink(),
				drinkResource.getStrGlass(),
				drinkResource.getStrInstructions(),
				drinkResource.getStrDrinkThumb(),
				getIngredients(drinkResource))).collect(Collectors.toList());
	}

	private List<String> getIngredients(CocktailDBResponse.DrinkResource drinkResource){
		return Arrays.asList(drinkResource.getStrIngredient1(), drinkResource.getStrIngredient2(), drinkResource.getStrIngredient3());
	}
}
