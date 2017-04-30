package com.williancampos.shoppingcart.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.williancampos.shoppingcart.authentication", "com.williancampos.shoppingcart.resource", "com.williancampos.shoppingcart.service" })
@EnableMongoRepositories(basePackages = { "com.williancampos.shoppingcart.repository" })
public class WebServer {

	public static void main(final String[] args) {
		SpringApplication.run(WebServer.class, args);
	}
}
