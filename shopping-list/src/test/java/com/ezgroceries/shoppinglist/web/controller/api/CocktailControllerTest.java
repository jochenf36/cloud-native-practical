package com.ezgroceries.shoppinglist.web.controller.api;


import com.ezgroceries.shoppinglist.repository.cocktail.CocktailRepository;
import com.ezgroceries.shoppinglist.repository.shoppingList.ShoppingListRepository;
import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.CocktailServiceDummyImpl;
import com.ezgroceries.shoppinglist.thirdPartyClients.cocktail.CocktailDBClient;
import com.ezgroceries.shoppinglist.web.controller.api.cocktail.CocktailController;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(CocktailController.class)
public class CocktailControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CocktailDBClient cocktailDBClient;

	@MockBean
	private CocktailRepository cocktailRepository;

	@MockBean
	private ShoppingListRepository shoppingListRepository;

	@MockBean
	private CocktailService service;

	@Test
	public void testHandleGetCocktails() throws Exception {
		String search = "russian";
		CocktailServiceDummyImpl serviceDummy = new CocktailServiceDummyImpl();
		when(service.searchCocktails(search)).thenReturn(serviceDummy.searchCocktails(search));
		this.mockMvc
				.perform(get("/cocktails").param("search", search))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(
						jsonPath("$._embedded.cocktailResourceList[0]",
						jsonPath("name"),
						jsonPath("glass"),
						jsonPath("instructions"),
						jsonPath("image"),
						jsonPath("ingredients[0]")).exists());
	}
}
