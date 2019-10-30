package config;

import com.ezgroceries.shoppinglist.persistence.repository.CocktailRepository;
import com.ezgroceries.shoppinglist.persistence.repository.ShoppingListRepository;
import com.ezgroceries.shoppinglist.service.external.CocktailDBClient;
import com.ezgroceries.shoppinglist.service.internal.CocktailService;
import com.ezgroceries.shoppinglist.service.internal.CocktailServiceImpl;
import com.ezgroceries.shoppinglist.service.internal.ShoppingListService;
import com.ezgroceries.shoppinglist.service.internal.ShoppingListServiceImpl;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
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

	//Custom tomcat configuration, we add an additional connector that allows http traffic next to https
	@Bean
	public ServletWebServerFactory servletContainer(@Value("${server.http.port}") int httpPort) {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setPort(httpPort);

		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(connector);
		return tomcat;
	}
}
