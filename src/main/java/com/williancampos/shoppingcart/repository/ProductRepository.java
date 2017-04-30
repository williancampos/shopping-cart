package com.williancampos.shoppingcart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.williancampos.shoppingcart.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
	
	Product findByIdentifier(final String identifier);

}
