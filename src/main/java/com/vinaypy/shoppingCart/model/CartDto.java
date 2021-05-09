package com.vinaypy.shoppingCart.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
	public CartDto(String cartName) {
		this.cartName = cartName;
	}
	String cartName;
	List<ItemDto> items;
}
