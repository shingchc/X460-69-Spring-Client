package com.xyz.rest.client;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.xyz.rest.model.Product;
import com.xyz.rest.model.ProductList;

@Controller
public class ProductClient {
	private RestTemplate rest;
	
	@Inject
	public void setRest(RestTemplate rest) {
		this.rest = rest;
	}
	
	// Get all products
	public ArrayList<Product> getProducts() {
		System.out.println("******** CLIENT: getProducts");
		
		ProductList productList = rest.getForObject( 
		    "http://localhost:8080/EStoreProject/rest/products", ProductList.class);
		return productList.getProducts();
	}	
	
	// Get a product by id
	public Product getProduct(int productId) {
		System.out.println("******** CLIENT: getProduct");
		
		Product product = rest.getForObject( 
		    "http://localhost:8080/EStoreProject/rest/products/{productId} ", 
		    Product.class, productId);
		return product;
	}	
	
	// Create a new product
	public void createProduct(Product product) {
		System.out.println("******** CLIENT: createProduct");
		
		ResponseEntity response = rest.postForEntity(
		    "http://localhost:8080/EStoreProject/rest/products", 
		    product, null);
		if (response.getStatusCode() != HttpStatus.CREATED) {
			throw new RuntimeException("Error creating a product)");
		}
		
	}	
	
	// Update a new product
	public void updateProduct(Product product) {
		System.out.println("******** CLIENT: updateProduct");
		
		rest.put("http://localhost:8080/EStoreProject/rest/products/{productId}", 
				product, product.getProductId());
	}	
	
	// Delete a product
	public void deleteProduct(Product product) {
		System.out.println("******** CLIENT: deleteProduct");
		
		rest.delete(
		    "http://localhost:8080/EStoreProject/rest/products/{productId}", 
		    product.getProductId());
	}	

}
