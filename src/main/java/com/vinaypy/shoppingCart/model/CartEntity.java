package com.vinaypy.shoppingCart.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class CartEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	String cartName;


	/**
	 * @param cartName
	 */
	public CartEntity(String cartName) {
		super();
		this.cartName = cartName;
	}

	@OneToMany(mappedBy = "cart")
	private List<ItemsEntity> items;

	@Override
	public String toString() {
		return "CartEntity [id=" + id + ", cartName=" + cartName + "]";
	}

}
