package config;

import com.ezgroceries.shoppinglist.repository.cocktail.CocktailRepository;
import com.ezgroceries.shoppinglist.repository.shoppingList.ShoppingListRepository;
import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.CocktailServiceImpl;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import com.ezgroceries.shoppinglist.service.ShoppingListServiceImpl;
import com.ezgroceries.shoppinglist.thirdPartyClients.cocktail.CocktailDBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingListConfig {

	private CocktailDBClient cocktailDBClient;
	private CocktailRepository cocktailRepository;
	private ShoppingListRepository shoppingListRepository;

	@Autowired
	ShoppingListConfig(CocktailDBClient cocktailDBClient, CocktailRepository cocktailRepository, ShoppingListRepository shoppingListRepository){
		this.cocktailRepository = cocktailRepository;
		this.cocktailDBClient = cocktailDBClient;
		this.shoppingListRepository = shoppingListRepository;
	}

	@Bean
	public CocktailService cocktailService(){
		return new CocktailServiceImpl(cocktailDBClient, cocktailRepository);
	}

	@Bean
	public ShoppingListService shoppingListService(){
		return new ShoppingListServiceImpl(shoppingListRepository, cocktailRepository);
	}
}
