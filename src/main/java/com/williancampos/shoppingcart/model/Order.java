package com.williancampos.shoppingcart.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;

/**
 * Represents a customer order. It has an identifier, the order creation date
 * and the set of order items.
 * 
 * @author Willian Campos
 *
 * @see OrderItem
 */
public final class Order implements Serializable {

	/**
	 * Builder for {@link Order}s.
	 *
	 */
	public static class Builder {
		private String identifier;
		private DateTime creationDate;
		private Set<OrderItem> items = new HashSet<>();

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
		}

		/**
		 * Sets the identifier.
		 * 
		 * @param identifier
		 *            the identifier
		 * @return this {@link Builder} instance
		 * @throws IllegalArgumentException
		 *             if the <code>identifier</code> does not contain text
		 *             based on {@link StringUtils#hasText(String)}.
		 * @see StringUtils#hasText(String)
		 */
		public Builder setIdentifier(final String identifier) {
			checkArgument(hasText(identifier), "The identifier must to contain text.");
			this.identifier = identifier;
			return this;
		}

		/**
		 * Sets the creation date.
		 * 
		 * @param creationDate
		 *            the creation date
		 * @return this {@link Builder} instance
		 * @throws IllegalArgumentException
		 *             if the <code>creationDate</code> is <code>null</code>
		 */
		public Builder setCreationDate(final DateTime creationDate) {
			checkArgument(creationDate != null, "The creation date must not be null");
			this.creationDate = creationDate;
			return this;
		}

		/**
		 * Add an {@link OrderItem} to this {@link Builder}
		 * 
		 * @param item
		 *            the item to be added
		 * @return this {@link Builder} instance
		 * @throws IllegalArgumentException
		 *             if the <code>item</code> is null
		 */
		public Builder addItem(final OrderItem item) {
			checkArgument(item != null, "The item must not be null");
			this.items.add(item);
			return this;
		}

		/**
		 * Builds an instance of {@link Order} using the data previously defined
		 * on this builder.
		 * 
		 * @return a new instance of {@link Order}
		 * @throws IllegalStateException
		 *             if either <code>identifier</code> or
		 *             <code>creationDate</code> are not provided. It is also
		 *             thrown if no {@link OrderItem} is provided.
		 */
		public Order build() {
			checkState(identifier != null, "The identifier must to be provided.");
			checkState(creationDate != null, "The creation date must to be provided.");
			checkState(items.size() > 0, "The items must not be empty.");
			return new Order(identifier, creationDate, items);
		}

	}

	private static final long serialVersionUID = 4055146672140602621L;

	@Id
	private String id;
	private String identifier;
	private DateTime creationDate;
	private Set<OrderItem> items;

	/**
	 * Required for persistence framework.
	 */
	private Order() {
	}

	private Order(String identifier, DateTime creationDate, Set<OrderItem> items) {
		this.identifier = identifier;
		this.creationDate = creationDate;
		this.items = ImmutableSet.copyOf(items);
	}

	/**
	 * Returns the unique identifier of this {@link Order}. The unique
	 * identifier allows an customer to easily identify an order.
	 * 
	 * @return the unique identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Returns the creation date of this {@link Order}, meaning, the exact
	 * moment when it was created.
	 * 
	 * @return the creation date
	 */
	public DateTime getCreationDate() {
		return creationDate;
	}

	/**
	 * Returns the set of {@link OrderItem}s associated with this {@link Order}.
	 * 
	 * @return the set of {@link OrderItem}s
	 * @see OrderItem
	 */
	public Set<OrderItem> getItems() {
		return Collections.unmodifiableSet(items);
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifier);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Order other = (Order) obj;
		return Objects.equals(identifier, other.identifier);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("identifier", identifier).add("creationDate", creationDate)
				.add("items", items).toString();
	}
}
