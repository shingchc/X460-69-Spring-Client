package com.uciext.springfw.hw.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uciext.springfw.hw.catalogs.model.Product;
import com.uciext.springfw.hw.catalogs.model.ProductList;
import com.uciext.springfw.hw.catalogs.service.CatalogService;

import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/")
public class CatalogsRestService {
		
	private CatalogService catalogService;
	
	@Inject
	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	// VIEW LIST OF PRODUCTS

	@RequestMapping(value="/products", method=RequestMethod.GET)
	public @ResponseBody ProductList productsList() {
		System.out.println("======= in productsList (REST)");
		List<Product> productList = catalogService.getProducts();
		
		// Convert
		ProductList pList = new ProductList();
		for (Product product : productList) {
			pList.getProducts().add(product);
		}
		
		return pList;
	}
	
	// VIEW PRODUCT DETAILS

	@RequestMapping(value="/products/{productId}", method=RequestMethod.GET)
	public @ResponseBody Product productView(@PathVariable("productId") int productId) {
		System.out.println("======= in productView (REST)");
		Product product = catalogService.getProduct(productId);
		return product;
	}

	// UPDATE PRODUCT

	@RequestMapping(value="/products/{productId}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void updateProduct(@PathVariable int productId, 
			@Valid @RequestBody Product product) {
		System.out.println("======= in updateProduct (REST)");
		catalogService.updateProduct(product);
	}

	// ADD PRODUCT
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED) 
	public void addDepartment(@Valid @RequestBody Product product) {
		System.out.println("======= in addProduct (REST)");
		catalogService.insertProduct(product);
	}

	// DELETE PRODUCT

	@RequestMapping(value="/products/{productId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void removeProduct(@PathVariable int productId) {
		System.out.println("======= in removeProduct  (REST)");
		catalogService.removeProduct(productId);
	}
}
