package com.williancampos.shoppingcart.model;

/**
 * Representation of an order item, meaning, a product of an
 * {@link Order} and its quantity.
 * 
 * @author Willian Campos
 *
 */
public interface OrderItem {
	
	/**
	 * Returns the product associated with this {@link OrderItem}.
	 * @return the product
	 */
	Product getProduct();

	/**
	 * Returns the quantity of the {@link Product} associated with this {@link OrderItem} in an {@link Order}.
	 * 
	 * @return the quantity
	 * @see #getProduct()
	 */
	int getQuantity();
}
