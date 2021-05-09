package com.vinaypy.shoppingCart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vinaypy.shoppingCart.exception.CartExistException;
import com.vinaypy.shoppingCart.model.CartDto;
import com.vinaypy.shoppingCart.model.ItemDto;
import com.vinaypy.shoppingCart.service.CartService;

@RestController
@RequestMapping("carts")
public class CartController {

	CartService cartService;

	/**
	 * @param cartsList
	 */
	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addCarts(@RequestBody CartDto cartDto) throws CartExistException {
		this.cartService.create(cartDto);

	}

	@GetMapping
	public List<CartDto> getCarts() {
		return this.cartService.fetchAll();
	}

	@PostMapping("{cartName}/load")
	@ResponseStatus(HttpStatus.CREATED)
	public void loadItem(@PathVariable String cartName, @RequestBody ItemDto itemDto) {
		this.cartService.addItem(itemDto);

	}

	@GetMapping("items")
	public List<ItemDto> getItems() {
		return this.cartService.fetchAllItems();
	}
}
