package com.williancampos.shoppingcart.model;

import java.util.Set;

import org.joda.time.DateTime;

/**
 * Represents a customer order. It has an identifier, the order creation date
 * and the set of order items.
 * 
 * @author Willian Campos
 *
 *@see OrderItem
 */
public interface Order {

	/**
	 * Returns the unique identifier of this {@link Order}.
	 * The unique identifier allows an customer to easily identify an order. 
	 * @return the unique identifier
	 */
	String getIdentifier();

	/**
	 * Returns the creation date of this {@link Order}, meaning, the exact moment when it was created.
	 * @return the creation date
	 */
	DateTime getCreationDate();

	/**
	 * Returns the set of {@link OrderItem}s associated with this {@link Order}.
	 * @return the set of {@link OrderItem}s
	 * @see OrderItem
	 */
	Set<OrderItem> getItems();
}
