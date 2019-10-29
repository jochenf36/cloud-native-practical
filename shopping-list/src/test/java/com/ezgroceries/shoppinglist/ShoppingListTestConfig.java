package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.CocktailServiceDummyImpl;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import com.ezgroceries.shoppinglist.service.ShoppingListServiceDummyImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ShoppingListTestConfig {
	@Bean
	public CocktailService cocktailService(){
		return new CocktailServiceDummyImpl();
	}

	@Bean
	public ShoppingListService shoppingListService(){
		return new ShoppingListServiceDummyImpl();
	}
}
