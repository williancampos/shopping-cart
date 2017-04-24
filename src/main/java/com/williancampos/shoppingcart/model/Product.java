package com.williancampos.shoppingcart.model;

/**
 * Representation of a product. It contains product characteristics as name,
 * description, etc.
 * 
 * @author Willian Campos
 *
 */
public interface Product {

	/**
	 * Returns the product name.
	 * 
	 * @return the product name
	 */
	String getName();

	/**
	 * Returns the product description.
	 * 
	 * @return the product description
	 */
	String getDescription();

	/**
	 * Returns an byte array representing the product image.
	 * 
	 * @return an byte array representing the product image. May be
	 *         <code>null</null>
	 */
	byte[] getImageBytes();

	/**
	 * Returns the product price.
	 * 
	 * @return the product price
	 */
	double getPrice();
}
