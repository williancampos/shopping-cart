package com.williancampos.shoppingcart.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;

import com.google.common.base.MoreObjects;

/**
 * Representation of an order item, meaning, a product of an {@link Order} and
 * its quantity.
 * 
 * @author Willian Campos
 *
 */
public final class OrderItem implements Serializable {

	/**
	 * Builder for {@link OrderItem}s.
	 *
	 */
	public static class Builder {
		private Product product;
		private Integer quantity;

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
		}

		/**
		 * Sets the product.
		 * 
		 * @param product
		 *            the product
		 * @return this {@link Builder} instance
		 * @throws IllegalArgumentException
		 *             if the <code>product</code> is null
		 */
		public Builder setProduct(final Product product) {
			checkArgument(product != null, "The product must not be null");
			this.product = product;
			return this;
		}

		/**
		 * Sets the product quatity.
		 * 
		 * @param quantity
		 *            the product quantity
		 * @return this {@link Builder} instance
		 * @throws IllegalArgumentException
		 *             if the <code>quantity</code> is not positive
		 */
		public Builder setQuantity(final int quantity) {
			checkArgument(quantity > 0, "The quantity must to be positive.");
			this.quantity = quantity;
			return this;
		}

		/**
		 * Builds an instance of {@link OrderItem} using the data previously
		 * defined on this builder.
		 * 
		 * @return a new instance of {@link OrderItem}
		 * @throws IllegalStateException
		 *             if either <code>product</code> or <code>quantity</code> are not provided
		 */
		public OrderItem build() {
			checkState(product != null, "The product must to be provided.");
			checkState(quantity != null, "The quantity must to be provided.");
			return new OrderItem(product, quantity);
		}
	}

	private static final long serialVersionUID = 561401351544065885L;

	@Id
	private String id;
	private Product product;
	private int quantity;

	/**
	 * Required for persistence framework.
	 */
	private OrderItem() {
	}

	private OrderItem(final Product product, final int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * Returns the product snapshot associated with this {@link OrderItem} when
	 * it was placed.
	 * 
	 * @return the product snapshot
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Returns the quantity of the {@link Product} associated with this
	 * {@link OrderItem} in an {@link Order}.
	 * 
	 * @return the quantity
	 * @see #getProduct()
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Returns the total cost of this {@link OrderItem}. In other words, the
	 * price of {@link Product} snapshot multiplied by the quantity in this
	 * {@link OrderItem}.
	 * 
	 * @return the total cost
	 */
	public double getTotalCost() {
		return product.getPrice() * quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, quantity);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final OrderItem other = (OrderItem) obj;
		return Objects.equals(product, other.product) && Objects.equals(quantity, other.quantity);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("product", product).add("quantity", quantity).toString();
	}
}
