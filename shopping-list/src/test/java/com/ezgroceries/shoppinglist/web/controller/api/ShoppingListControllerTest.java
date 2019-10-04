package com.ezgroceries.shoppinglist.web.controller.api;


import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import com.ezgroceries.shoppinglist.service.ShoppingListServiceImpl;
import com.ezgroceries.shoppinglist.web.controller.api.cocktail.model.CocktailReference;
import com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.ShoppingListController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingListController.class)
public class ShoppingListControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ShoppingListService shoppingListService;

	@MockBean
	private CocktailService cocktailService;

	private ShoppingListService shoppingListServiceDummy;

	@Before
	public void initialize() {
		shoppingListServiceDummy = new ShoppingListServiceImpl();
	}

	@Test
	public void testHandlePostShoppingList() throws Exception {
		String shoppingList = "Stephanie's birthday";
		when(shoppingListService.createShoppingList(shoppingList)).thenReturn(shoppingListServiceDummy.createShoppingList(shoppingList));
		this.mockMvc
				.perform(
						post("/shopping-lists")
								.contentType(MediaType.APPLICATION_JSON)
								.content("{\"name\": \"" + shoppingList + "\"}\n")
				)
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(
						jsonPath("shoppingListId",
						jsonPath("name")
				).exists());
	}

	@Test
	public void testHandleGetAllShoppingLists() throws Exception {
		when(shoppingListService.getAllShoppingLists()).thenReturn(shoppingListServiceDummy.getAllShoppingLists());
		this.mockMvc
				.perform(get("/shopping-lists"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$._embedded.shoppingListList").isArray());
	}

	@Test
	public void testHandleGetShoppingList() throws Exception {
		String shoppingListId = "blabla";
		when(shoppingListService.getShoppingList(shoppingListId)).thenReturn(shoppingListServiceDummy.getShoppingList(shoppingListId));
		this.mockMvc
				.perform(get("/shopping-lists/" + shoppingListId))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("shoppingListId").exists())
				.andExpect(jsonPath("name").exists())
				.andExpect(jsonPath("ingredients").exists());
	}

	@Test
	public void testHandleAddCocktails() throws Exception {
		String shoppingListId = "blabla";
		when(shoppingListService.addCocktails(any(String.class), any(List.class))).thenReturn(shoppingListServiceDummy.addCocktails(shoppingListId,
				Arrays.asList(
				new CocktailReference("23b3d85a-3928-41c0-a533-6538a71e17c4"),
				new CocktailReference("d615ec78-fe93-467b-8d26-5d26d8eab073"))));
		this.mockMvc
				.perform(
						post("/shopping-lists/"+shoppingListId+"/cocktails")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content("[\n" +
										"  {\n" +
										"    \"cocktailId\": \"23b3d85a-3928-41c0-a533-6538a71e17c4\"\n" +
										"  },\n" +
										"  {\n" +
										"    \"cocktailId\": \"d615ec78-fe93-467b-8d26-5d26d8eab073\"\n" +
										"  }\n" +
										"]")
				)
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$._embedded.cocktailReferenceList[0]").exists());
	}
}
