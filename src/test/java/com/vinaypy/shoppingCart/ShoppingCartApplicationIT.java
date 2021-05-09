package com.vinaypy.shoppingCart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinaypy.shoppingCart.model.CartDto;
import com.vinaypy.shoppingCart.model.CartRepository;
import com.vinaypy.shoppingCart.model.ItemDto;
import com.vinaypy.shoppingCart.model.ItemRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ShoppingCartApplicationIT {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ItemRepository itemRepository;

	@Test
	public void addCart() throws Exception {

		CartDto cartDto = new CartDto("cart1");

		mockMvc.perform(post("/carts").content(objectMapper.writeValueAsString(cartDto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mockMvc.perform(get("/carts")).andExpect(status().isOk()).andExpect(jsonPath("[0].cartName").value("cart1"));

	}

	@Test
	public void addItem() throws Exception {

		CartDto cartDto = new CartDto("cart1");
		ItemDto itemDto = new ItemDto("item1", cartDto);

		mockMvc.perform(post("/carts/cart1/load").content(objectMapper.writeValueAsBytes(itemDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());


		mockMvc.perform(get("/carts/items")).andExpect(status().isOk())
				.andExpect(jsonPath("[0].itemName").value("item1"))
				.andExpect(jsonPath("[0].cart.cartName").value("cart1"))
		;
	}

}
