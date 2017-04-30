package com.williancampos.shoppingcart.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.common.base.MoreObjects;

/**
 * Representation of the shopping cart. A customer can add and remove products
 * (in the desired quantity) of its shopping cart.
 * 
 * @author Willian Campos
 *
 */
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 6504467589777675910L;

	private final Set<ShoppingCartItem> items = new HashSet<>();
	private final Map<Product, Integer> itemMap = new HashMap<>();

	/**
	 * Returns the {@link ShoppingCartItem}s currently in the shopping cart.
	 * 
	 * @return the {@link ShoppingCartItem}s
	 * @see ShoppingCartItem
	 */
	public Set<ShoppingCartItem> getItems() {
		return Collections.unmodifiableSet(items);
	}

	/**
	 * Add into the cart the specified quantity of a {@link Product}. If the
	 * product already exists in the cart, the given quantity will be added to
	 * what was previously added.
	 * 
	 * @param product
	 *            the {@link Product} to be added to the cart
	 * @param quantity
	 *            the quantity to be added
	 */
	public void add(final Product product, final int quantity) {
		Integer itemQuantity = itemMap.get(product);
		if (itemQuantity == null) {
			itemQuantity = 0;
		}
		itemQuantity += quantity;
		itemMap.put(product, itemQuantity);
		final ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, itemQuantity);
		items.remove(shoppingCartItem);
		items.add(shoppingCartItem);
	}

	/**
	 * Remove from the cart the specified quantity of a {@link Product}. If the
	 * product is not present in the cart or if the quantity in the cart is
	 * lesser than the given in the parameter, a
	 * {@link IllegalArgumentException} is thrown.
	 * 
	 * @param product
	 *            the {@link Product} to be removed to the cart
	 * @param quantity
	 *            the quantity to be removed
	 * 
	 * @throws IllegalArgumentException
	 */
	public void remove(final Product product, final int quantity) {
		Integer itemQuantity = itemMap.get(product);
		if (itemQuantity == null) {
			itemQuantity = 0;
		}
		itemQuantity -= quantity;
		final ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, itemQuantity);
		items.remove(shoppingCartItem);
		
		if (itemQuantity > 0) {
			itemMap.put(product, itemQuantity);
			items.add(shoppingCartItem);	
		} else {
			itemMap.remove(product);
		}
	}

	/**
	 * Update the cart with the specified quantity of a {@link Product}. If this
	 * product was already present in the cart, the quantity will be replaced by
	 * the new one. If the product was not present in the cart, it will then be
	 * added in the given quantity.
	 * 
	 * @param product
	 *            the {@link Product} to be updated in the cart
	 * @param quantity
	 *            the desired quantity
	 */
	public void update(final Product product, final int quantity) {
		final ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);
		items.remove(shoppingCartItem);
		
		if (quantity > 0) {
			itemMap.put(product, quantity);
			items.add(shoppingCartItem);	
		} else {
			itemMap.remove(product);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(items);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ShoppingCart other = (ShoppingCart) obj;
		return Objects.equals(items, other.items);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("items", items).toString();
	}

}
