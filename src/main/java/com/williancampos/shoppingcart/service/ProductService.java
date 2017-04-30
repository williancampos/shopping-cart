package com.williancampos.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williancampos.shoppingcart.model.Product;
import com.williancampos.shoppingcart.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> list() {
		return productRepository.findAll();
	}
	
}
