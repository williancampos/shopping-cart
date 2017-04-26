package com.williancampos.shoppingcart.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

import com.google.common.base.MoreObjects;

/**
 * Representation of a product. It contains product characteristics as name,
 * description, etc.
 * 
 * @author Willian Campos
 *
 */
public final class Product implements Serializable {

	/**
	 * Builder for {@link Product}s.
	 *
	 */
	public static class Builder {
		private String identifier;
		private String name;
		private String description;
		private byte[] imageBytes;
		private Double price;

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
		 *             if the <code>identifier</code> does not contain text based on
		 *             {@link StringUtils#hasText(String)}.
		 * @see StringUtils#hasText(String)
		 */
		public Builder setIdentifier(final String identifier) {
			checkArgument(hasText(identifier), "The identifier must to contain text.");
			this.identifier = identifier;
			return this;
		}

		/**
		 * Sets the name.
		 * 
		 * @param name
		 *            the name
		 * @return this {@link Builder} instance
		 * @throws IllegalArgumentException
		 *             if the <code>name</code> does not contain text based on
		 *             {@link StringUtils#hasText(String)}.
		 * @see StringUtils#hasText(String)
		 */
		public Builder setName(final String name) {
			checkArgument(hasText(name), "The name must to contain text.");
			this.name = name;
			return this;
		}

		/**
		 * Sets the description.
		 * 
		 * @param description
		 *            the description
		 * @return this {@link Builder} instance
		 * @throws IllegalArgumentException
		 *             if the <code>description</code> does not contain text based on
		 *             {@link StringUtils#hasText(String)}.
		 * @see StringUtils#hasText(String)
		 */
		public Builder setDescription(final String description) {
			checkArgument(hasText(description), "The description must to contain text.");
			this.description = description;
			return this;
		}

		/**
		 * Sets the byte array representing the image.
		 * 
		 * @param imageBytes
		 *            the byte array representing the image
		 * @return this {@link Builder} instance
		 * @throws IllegalArgumentException
		 *             if the <code>imageBytes</code> is <code>null</code> or empty
		 */
		public Builder setImageBytes(final byte[] imageBytes) {
			checkArgument(imageBytes != null, "The image bytes must not be null.");
			checkArgument(imageBytes.length > 0, "The image bytes must not be empty.");
			this.imageBytes = imageBytes;
			return this;
		}

		/**
		 * Sets the price.
		 * 
		 * @param price
		 *            the price
		 * @return this {@link Builder} instance
		 * @throws IllegalArgumentException
		 *             if the <code>price</code> is not positive
		 */
		public Builder setPrice(final double price) {
			checkArgument(price > 0, "The price must to be positive.");
			this.price = price;
			return this;
		}

		/**
		 * Builds an instance of {@link Product} using the data previously defined
		 * on this builder.
		 * 
		 * @return a new instance of {@link Product}
		 * @throws IllegalStateException
		 *             if either <code>identifier</code>, <code>name</code>, <code>description</code> or <code>price</code> are not provided
		 */
		public Product build() {
			checkState(identifier != null, "The identifier must to be provided.");
			checkState(name != null, "The name must to be provided.");
			checkState(description != null, "The description must to be provided.");
			checkState(price != null, "The price must to be provided.");
			return new Product(identifier, name, description, imageBytes, price);
		}
	}

	private static final long serialVersionUID = -2269723765817337585L;

	@Id
	private String id;
	private String identifier;
	private String name;
	private String description;
	private byte[] imageBytes;
	private double price;

	/**
	 * Required for persistence framework.
	 */
	private Product() {
	}

	private Product(final String identifier, final String name, final String description, final byte[] imageBytes, final double price) {
		this.identifier = identifier;
		this.name = name;
		this.description = description;
		this.imageBytes = imageBytes;
		this.price = price;
	}
	
	/**
	 * Returns the product identifier.
	 * 
	 * @return the product identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Returns the product name.
	 * 
	 * @return the product name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the product description.
	 * 
	 * @return the product description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns an byte array representing the product image.
	 * 
	 * @return an byte array representing the product image. May be
	 *         <code>null</null>
	 */
	public byte[] getImageBytes() {
		return imageBytes;
	}

	/**
	 * Returns the product price.
	 * 
	 * @return the product price
	 */
	public double getPrice() {
		return price;
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
		final Product other = (Product) obj;
		return Objects.equals(identifier, other.identifier);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("identifier", identifier).add("name", name)
				.add("description", description).add("price", price)
				.toString();
	}
}
