package com.ezgroceries.shoppinglist.service.external;

import com.ezgroceries.shoppinglist.persistence.entity.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.repository.CocktailRepository;
import com.ezgroceries.shoppinglist.web.controller.api.cocktail.dto.CocktailDBResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1", fallback = CocktailDBClient.CocktailDBClientFallback.class)
public interface CocktailDBClient {

	@GetMapping(value = "search.php")
	CocktailDBResponse searchCocktails(@RequestParam("s") String search);


	@Component
	class CocktailDBClientFallback implements CocktailDBClient {

		private final CocktailRepository cocktailRepository;

		public CocktailDBClientFallback(CocktailRepository cocktailRepository) {
			this.cocktailRepository = cocktailRepository;
		}

		@Override
		public CocktailDBResponse searchCocktails(String search) {
			List<CocktailEntity> cocktailEntities = cocktailRepository.findByNameContainingIgnoreCase(search);

			CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
			cocktailDBResponse.setDrinks(cocktailEntities.stream().map(cocktailEntity -> {
				CocktailDBResponse.DrinkResource drinkResource = new CocktailDBResponse.DrinkResource();
				drinkResource.setIdDrink(cocktailEntity.getIdDrink());
				drinkResource.setStrDrink(cocktailEntity.getName());
				drinkResource.setStrDrinkThumb(cocktailEntity.getImagelink());
				drinkResource.setStrGlass(cocktailEntity.getGlass());
				drinkResource.setStrInstructions(cocktailEntity.getInstructions());

				List<String> ingredients = new ArrayList(cocktailEntity.getIngredients());

				drinkResource.setStrIngredient1(ingredients.get(0));
				drinkResource.setStrIngredient2(ingredients.get(1));
				drinkResource.setStrIngredient3(ingredients.get(2));

				return drinkResource;
			}).collect(Collectors.toList()));

			return cocktailDBResponse;
		}
	}
}
