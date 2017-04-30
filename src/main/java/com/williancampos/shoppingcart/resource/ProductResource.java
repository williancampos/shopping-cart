package com.williancampos.shoppingcart.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.williancampos.shoppingcart.service.ProductService;

@RestController
@Path("/product")
public class ProductResource {
	
	@Autowired
	private ProductService productService;

	@GET
	public Response doGet() {
		return Response.ok(productService.list()).build();
	}
}
