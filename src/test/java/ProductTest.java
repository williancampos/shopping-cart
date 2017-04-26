import static org.junit.Assert.*;

import org.junit.Test;

import com.williancampos.shoppingcart.model.Product;

public class ProductTest {

	@Test(expected = IllegalArgumentException.class)
	public void testNullIdentifier() {
		new Product.Builder().setIdentifier(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyIdentifier() {
		new Product.Builder().setIdentifier("  ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullName() {
		new Product.Builder().setName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyName() {
		new Product.Builder().setName("  ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullDescription() {
		new Product.Builder().setDescription(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyDecription() {
		new Product.Builder().setDescription("  ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullImageBytes() {
		new Product.Builder().setImageBytes(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyImageBytes() {
		new Product.Builder().setImageBytes(new byte[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositivePrice() {
		new Product.Builder().setPrice(0);
	}

	@Test(expected = IllegalStateException.class)
	public void testBuildMissingIdentifier() {
		final Product.Builder builder = new Product.Builder();
		builder.setDescription("Product description").setName("Product name").setPrice(120.5).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testBuildMissingName() {
		final Product.Builder builder = new Product.Builder();
		builder.setIdentifier("PROD-TEST").setDescription("Product description").setPrice(120.5).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testBuildMissingDescription() {
		final Product.Builder builder = new Product.Builder();
		builder.setIdentifier("PROD-TEST").setPrice(120.5).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testBuildMissingPrice() {
		final Product.Builder builder = new Product.Builder();
		builder.setIdentifier("PROD-TEST").setDescription("Product description").build();
	}

	@Test
	public void testBuildWithSuccess() {
		final Product.Builder builder = new Product.Builder();
		final Product product = builder.setIdentifier("PROD-TEST").setDescription("Product description")
				.setName("Product name").setPrice(120.5).build();

		assertEquals("PROD-TEST", product.getIdentifier());
		assertEquals("Product name", product.getName());
		assertEquals("Product description", product.getDescription());
		assertEquals(null, product.getImageBytes());
		assertEquals(120.5, product.getPrice(), 0);
	}

	@Test
	public void testNotEquals() {
		final Product.Builder builder = new Product.Builder();
		final Product product1 = builder.setIdentifier("PROD-TEST").setDescription("Product description")
				.setName("Product name").setPrice(120.5).build();

		final Product product2 = builder.setIdentifier("PROD-TEST-DIFFERENT").setDescription("Product description")
				.setName("Product name").setPrice(120.5).build();

		assertFalse(product1.equals(product2));
	}
	
	@Test
	public void testEquals() {
		final Product.Builder builder = new Product.Builder();
		final Product product1 = builder.setIdentifier("PROD-TEST").setDescription("Product description")
				.setName("Product name").setPrice(120.5).build();

		final Product product2 = builder.setIdentifier("PROD-TEST").setDescription("Another product description")
				.setName("Another product name").setPrice(999).build();

		assertTrue(product1.equals(product2));
	}
}
