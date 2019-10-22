package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.repository.cocktail.CocktailEntity;
import com.ezgroceries.shoppinglist.repository.cocktail.CocktailRepository;
import com.ezgroceries.shoppinglist.repository.shoppingList.ShoppingListEntity;
import com.ezgroceries.shoppinglist.repository.shoppingList.ShoppingListRepository;
import com.ezgroceries.shoppinglist.web.controller.api.cocktail.model.CocktailReference;
import com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.model.ShoppingList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListServiceTest {

	private ShoppingListRepository shoppingListRepository;
	private CocktailRepository cocktailRepository;

	private ShoppingListService shoppingListService;

	@Before
	public void setUp(){
		shoppingListRepository = Mockito.mock(ShoppingListRepository.class);
		cocktailRepository = Mockito.mock(CocktailRepository.class);
		shoppingListService = new ShoppingListServiceImpl(shoppingListRepository, cocktailRepository);
	}

	@Test
	public void testHandleCreateShoppingList(){
		String shoppingListName = "shoppingListName";
		UUID uuid = UUID.randomUUID();
		ShoppingListEntity  shoppingListEntity = new ShoppingListEntity();
		shoppingListEntity.setId(uuid);
		shoppingListEntity.setName(shoppingListName);
		Mockito.when(shoppingListRepository.save(any(ShoppingListEntity.class))).thenReturn(shoppingListEntity);

		ShoppingListResource  shoppingListResource = shoppingListService.createShoppingList(shoppingListName);

		assertEquals(shoppingListName, shoppingListResource.name);
		assertEquals(uuid, shoppingListResource.shoppingListId);
	}

	@Test
	public void testHandleAddCocktails(){
		// setup
		String shoppingListId = "123e4567-e89b-12d3-a456-556642440000";
		List<CocktailReference> cocktailReferences = new ArrayList<>();
		CocktailReference cocktailReference1 = new CocktailReference("cocktail1");
		CocktailReference cocktailReference2 = new CocktailReference("cocktail2");
		CocktailReference cocktailReference3 = new CocktailReference("cocktail3");
		cocktailReferences.add(cocktailReference1);
		cocktailReferences.add(cocktailReference2);
		cocktailReferences.add(cocktailReference3);

		Set<CocktailEntity> cocktailsSet = new HashSet();
		CocktailEntity cocktailEntity1 = new CocktailEntity();
		cocktailEntity1.setIdDrink("cocktail1");
		CocktailEntity cocktailEntity2 = new CocktailEntity();
		cocktailEntity2.setIdDrink("cocktail2");
		CocktailEntity cocktailEntity3 = new CocktailEntity();
		cocktailEntity3.setIdDrink("cocktail3");

		cocktailsSet.add(cocktailEntity1);
		cocktailsSet.add(cocktailEntity2);
		cocktailsSet.add(cocktailEntity3);

		List<CocktailEntity> cocktailsList = new ArrayList<>();
		cocktailsList.add(cocktailEntity1);
		cocktailsList.add(cocktailEntity2);
		cocktailsList.add(cocktailEntity3);

		ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
		shoppingListEntity.setCocktails(cocktailsSet);
		Mockito.when(shoppingListRepository.findById(any(UUID.class))).thenReturn(shoppingListEntity);
		Mockito.when(cocktailRepository.findByIdDrinkIn(any(List.class))).thenReturn(cocktailsList);

		//test
		List<CocktailReference> renewedCocktailreferences = shoppingListService.addCocktails(shoppingListId, cocktailReferences);

		// verification
		assertEquals(cocktailReferences.size(), renewedCocktailreferences.size());
	}

	@Test
	public void testHandleGetShoppingList(){
		String shoppingListId = "123e4567-e89b-12d3-a456-556642440000";
		UUID uuid = UUID.randomUUID();
		ShoppingListEntity shoppingListEntity = createShoppingListEntityDummy();

		Mockito.when(shoppingListRepository.findById(any(UUID.class))).thenReturn(shoppingListEntity);

		ShoppingList shoppingList = shoppingListService.getShoppingList(shoppingListId);

		assertNotNull(shoppingList);
	}

	@Test
	public void testHandleGetAllShoppingLists(){
		List<ShoppingListEntity> shoppingListEntities = new ArrayList<>();
		ShoppingListEntity shoppingListEntity1 = createShoppingListEntityDummy();
		ShoppingListEntity shoppingListEntity2 = createShoppingListEntityDummy();
		ShoppingListEntity shoppingListEntity3 = createShoppingListEntityDummy();
		shoppingListEntities.add(shoppingListEntity1);
		shoppingListEntities.add(shoppingListEntity2);
		shoppingListEntities.add(shoppingListEntity3);

		Mockito.when(shoppingListRepository.findAll()).thenReturn(shoppingListEntities);

		List<ShoppingList> shoppingLists = shoppingListService.getAllShoppingLists();

		assertEquals(shoppingListEntities.size(), shoppingLists.size());
	}

	private ShoppingListEntity createShoppingListEntityDummy(){
		UUID uuid = UUID.randomUUID();

		ShoppingListEntity shoppingListEntity = new ShoppingListEntity();

		shoppingListEntity.setId(uuid);

		Set<String> ingredients = new HashSet<>();
		ingredients.add("melk");
		ingredients.add("gin");
		ingredients.add("vodka");

		Set<CocktailEntity> cocktailsSet = new HashSet();
		CocktailEntity cocktailEntity1 = new CocktailEntity();
		cocktailEntity1.setIdDrink("cocktail1");
		cocktailEntity1.setIngredients(ingredients);
		CocktailEntity cocktailEntity2 = new CocktailEntity();
		cocktailEntity2.setIdDrink("cocktail2");
		cocktailEntity2.setIngredients(ingredients);
		CocktailEntity cocktailEntity3 = new CocktailEntity();
		cocktailEntity3.setIdDrink("cocktail3");
		cocktailEntity3.setIngredients(ingredients);
		cocktailsSet.add(cocktailEntity1);
		cocktailsSet.add(cocktailEntity2);
		cocktailsSet.add(cocktailEntity3);
		shoppingListEntity.setCocktails(cocktailsSet);
		return shoppingListEntity;
	}
}
