package com.williancampos.shoppingcart.model;

/**
 * Represents the association between a {@link Product} and a {@link ShoppingCart}.
 * In other words, it is an abstraction of the quantity of products in a shopping cart.
 * 
 * @author Willian Campos
 * @see Product
 * @see ShoppingCart
 *
 */
public interface ShoppingCartProduct {
	
	/**
	 * Returns the associated {@link Product}.
	 * @return the associated {@link Product}
	 */
	Product getProduct();
	
	/**
	 * Returns the quantity of the {@link Product}.
	 * @return the quantity of the {@link Product}
	 */
	int getQuantity();
}
