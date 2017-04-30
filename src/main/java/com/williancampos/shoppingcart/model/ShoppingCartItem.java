package com.williancampos.shoppingcart.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serializable;
import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Represents the association between a {@link Product} and a
 * {@link ShoppingCart}. In other words, it is an abstraction of the quantity of
 * products in a shopping cart.
 * 
 * @author Willian Campos
 * @see Product
 * @see ShoppingCart
 *
 */
public class ShoppingCartItem implements Serializable {

	private static final long serialVersionUID = 7934069585812471894L;

	private Product product;
	private int quantity;

	public ShoppingCartItem(final Product product, final int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * Returns the associated {@link Product}.
	 * 
	 * @return the associated {@link Product}
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Returns the quantity of the {@link Product}.
	 * 
	 * @return the quantity of the {@link Product}
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void addQuantity(final int quantity) {
		checkArgument(quantity > 0, "The quantity must to be positive.");
		this.quantity = this.quantity + quantity;
	}

	public void removeQuantity(final int quantity) {
		checkArgument(quantity > 0, "The quantity must to be positive.");
		checkArgument(quantity > this.quantity,
				"The quantity to be removed can not be higher than the existing quantity.");
		this.quantity = this.quantity - quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(product);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ShoppingCartItem other = (ShoppingCartItem) obj;
		return Objects.equals(product, other.product);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("product", product).add("quantity", quantity).toString();
	}
}
