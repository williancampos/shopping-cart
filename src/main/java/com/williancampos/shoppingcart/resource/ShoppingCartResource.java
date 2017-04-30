package com.williancampos.shoppingcart.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.williancampos.shoppingcart.service.ShoppingCartService;
import com.williancampos.shoppingcart.service.ShoppingCartService.ShoppingCartOperationSet;

@RestController
@Path("/cart")
public class ShoppingCartResource {
	
	@Autowired
	private ShoppingCartService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGet() {
		return Response.ok(service.retrieveCart()).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doPut(final ShoppingCartOperationSet operationSet) {
		service.perform(operationSet);
		return Response.ok().build();
	}
	
	
}
