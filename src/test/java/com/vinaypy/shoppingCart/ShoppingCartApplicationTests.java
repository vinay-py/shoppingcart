package com.vinaypy.shoppingCart;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vinaypy.shoppingCart.exception.CartExistException;
import com.vinaypy.shoppingCart.model.CartDto;
import com.vinaypy.shoppingCart.model.CartEntity;
import com.vinaypy.shoppingCart.model.CartRepository;
import com.vinaypy.shoppingCart.model.ItemRepository;
import com.vinaypy.shoppingCart.service.CartService;

@ExtendWith(MockitoExtension.class)
class ShoppingCartApplicationTests {

	@Mock
	CartRepository mockCartRepository;

	@Mock
	ItemRepository mockItemRepository;

	@InjectMocks
	CartService subject;

	@Test
	void create() throws CartExistException {
		CartDto cartDto = new CartDto("cart1");
//		ItemDto itemDto = new ItemDto("item1", cartDto);

		subject.create(cartDto);
//		subject.addItem(itemDto);

		verify(mockCartRepository).save(new CartEntity("cart1"));
//		verify(mockItemRepository).save(new ItemsEntity("item1", new CartEntity("cart1")));

	}

	@Test
	void create_conflict () {
		when(mockCartRepository.findAll()).thenReturn(List.of(new CartEntity("cart1")));

		CartDto cartDto = new CartDto("cart1");
		assertThatThrownBy(() -> subject.create(cartDto)).isInstanceOf(CartExistException.class);

		verify(mockCartRepository, never()).save(any());
	}
}
