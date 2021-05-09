package com.vinaypy.shoppingCart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinaypy.shoppingCart.exception.CartExistException;
import com.vinaypy.shoppingCart.model.CartDto;
import com.vinaypy.shoppingCart.model.CartEntity;
import com.vinaypy.shoppingCart.model.CartRepository;
import com.vinaypy.shoppingCart.model.ItemDto;
import com.vinaypy.shoppingCart.model.ItemRepository;
import com.vinaypy.shoppingCart.model.ItemsEntity;

@Service
public class CartService {
	private final CartRepository cartRepository;
	private final ItemRepository itemRepository;

	@Autowired
	public CartService(CartRepository cartRepository, ItemRepository itemRepository) {
		this.cartRepository = cartRepository;
		this.itemRepository = itemRepository;
	}

	public void create(CartDto cartDto) throws CartExistException {
		Optional<CartEntity> cartExists = cartRepository.findAll().stream()
				.filter(cartEntity -> cartEntity.getCartName().equals(cartDto.getCartName())).findAny();

		if (cartExists.isPresent()) {
			throw new CartExistException();
		} else {
			cartRepository.save(new CartEntity(cartDto.getCartName()));
		}

	}

	public void addItem(ItemDto itemDto) {

		Optional<CartEntity> cartExists = cartRepository.findAll().stream()
				.filter(cartEntity -> cartEntity.getCartName().equals(itemDto.getCart().getCartName())).findAny();
		if (cartExists.isEmpty()) {
			cartRepository.save(new CartEntity(itemDto.getCart().getCartName()));
		}

		ItemsEntity itemsEntity = new ItemsEntity(itemDto.getItemName(),
				new CartEntity(itemDto.getCart().getCartName()));

		System.out.println(itemsEntity);
		itemRepository.save(itemsEntity);
	}

	public List<CartDto> fetchAll() {
		// TODO Auto-generated method stub
		return cartRepository.findAll().stream().map(CartEntity -> {
			return new CartDto(CartEntity.getCartName());
		}).collect(Collectors.toList());
	}

	public List<ItemDto> fetchAllItems() {
		// TODO Auto-generated method stub

		return itemRepository.findAll().stream().map(ItemEntity -> {
			System.out.println(ItemEntity.getCart().getCartName());
			return new ItemDto(ItemEntity.getItemName(), new CartDto(ItemEntity.getCart().getCartName()));
		}).collect(Collectors.toList());
	}

}
