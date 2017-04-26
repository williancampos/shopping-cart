package com.williancampos.shoppingcart.model;

import java.util.Set;

/**
 * Representation of the shopping cart. A customer can add and remove products
 * (in the desired quantity) of its shopping cart.
 * 
 * @author Willian Campos
 *
 */
public interface ShoppingCart {

	/**
	 * Returns the {@link ShoppingCartItem}s currently in the shopping cart.
	 * 
	 * @return the {@link ShoppingCartItem}s
	 * @see ShoppingCartItem
	 */
	Set<ShoppingCartItem> getItems();

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
	void add(Product product, int quantity);

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
	void remove(Product product, int quantity);

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
	void update(Product product, int quantity);

}
