package com.vinaypy.shoppingCart.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemsEntity, Long> {
	ItemsEntity findByItemName(String itemName);

}
