package com.williancampos.shoppingcart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.williancampos.shoppingcart.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

}
