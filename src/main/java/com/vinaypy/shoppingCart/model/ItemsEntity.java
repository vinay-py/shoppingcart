package com.vinaypy.shoppingCart.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class ItemsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	String itemName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", nullable = true)
	private CartEntity cart;

	/**
	 * @param itemName
	 */
	public ItemsEntity(String itemName) {
		super();
		this.itemName = itemName;
	}

	/**
	 * @param itemName
	 * @param cart
	 */
	public ItemsEntity(String itemName, CartEntity cart) {
		super();
		this.itemName = itemName;
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "ItemsEntity [id=" + id + ", itemName=" + itemName + ", cart=" + cart + "]";
	}

}
