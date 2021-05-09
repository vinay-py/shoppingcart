package com.vinaypy.shoppingCart.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
	CartEntity findByCartName(String cartName);
}
