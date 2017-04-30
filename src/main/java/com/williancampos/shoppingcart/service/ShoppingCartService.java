package com.williancampos.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williancampos.shoppingcart.model.Product;
import com.williancampos.shoppingcart.model.ShoppingCart;
import com.williancampos.shoppingcart.repository.ProductRepository;

@Service
public class ShoppingCartService {

	@XmlRootElement
	public static class ShoppingCartOperationSet {
		private List<ShoppingCartOperation> addOperations = new ArrayList<>();
		private List<ShoppingCartOperation> removeOperations = new ArrayList<>();

		public List<ShoppingCartOperation> getAddOperations() {
			return addOperations;
		}

		public void setAddOperations(List<ShoppingCartOperation> addOperations) {
			this.addOperations = addOperations;
		}

		public List<ShoppingCartOperation> getRemoveOperations() {
			return removeOperations;
		}

		public void setRemoveOperations(List<ShoppingCartOperation> removeOperations) {
			this.removeOperations = removeOperations;
		}
	}

	public static class ShoppingCartOperation {
		private String productIdentifier;
		private int quantity;

		public String getProductIdentifier() {
			return productIdentifier;
		}

		public void setProductIdentifier(String productIdentifier) {
			this.productIdentifier = productIdentifier;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	}
	
	@Autowired
	private ProductRepository productRepository;

	public ShoppingCart retrieveCart() {
		ShoppingCart cart = (ShoppingCart) SecurityUtils.getSubject().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
			SecurityUtils.getSubject().getSession().setAttribute("cart", cart);
		}
		return cart;
	}

	public void perform(final ShoppingCartOperationSet operationSet) {
//		TODO: validate arguments
		final ShoppingCart cart = retrieveCart();

		for (final ShoppingCartOperation operation : operationSet.addOperations) {
			//TODO: check not null (parameter and result)
			final Product product = productRepository.findByIdentifier(operation.productIdentifier);
			cart.add(product, operation.quantity);
		}

		for (final ShoppingCartOperation operation : operationSet.removeOperations) {
			final Product product = productRepository.findByIdentifier(operation.productIdentifier);
			cart.remove(product, operation.quantity);
		}
	}
}
