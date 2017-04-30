package com.williancampos.shoppingcart.resource;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/rest")
public class RestResourceConfig extends ResourceConfig {

	public RestResourceConfig() {
		register(OrderResource.class);
		register(ProductResource.class);
		register(ShoppingCartResource.class);
	}
}
