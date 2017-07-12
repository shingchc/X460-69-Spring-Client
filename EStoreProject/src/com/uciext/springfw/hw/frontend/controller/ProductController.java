package com.uciext.springfw.hw.frontend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uciext.springfw.hw.catalogs.model.Items;
import com.uciext.springfw.hw.catalogs.model.Product;
import com.uciext.springfw.hw.catalogs.service.CatalogService;

@Controller
@RequestMapping("/product")
public class ProductController {
		
	private CatalogService catalogService;
	
	@Inject
	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	// VIEW LIST OF PRODUCTS

	@RequestMapping("/list")
	public ModelAndView productList(Model model) {
		System.out.println("======= in productList");
		List<Product> productList = catalogService.getProducts();
		model.addAttribute("productList", productList);
		model.addAttribute("selectedProducts", new Items());
		return new ModelAndView("product/productList3");
	}

	// VIEW PRODUCTS DETAILS

	@RequestMapping("/view")
	public ModelAndView productView(@RequestParam("productId") int productId, Model model) {
		System.out.println("======= in productView");
		Product product = catalogService.getProduct(productId);
		model.addAttribute("product", product);
		return new ModelAndView("product/viewProduct");
	}

	// UPDATE PRODUCT

	@RequestMapping(value="/edit/{productId}", method=RequestMethod.GET)
	public String editProduct(@PathVariable int productId, Model model) {
		System.out.println("======= in editProduct");
		Product product = catalogService.getProduct(productId);
		model.addAttribute("product", product);
		return "product/editProduct";
	}
	
	@RequestMapping(value="/edit/{productId}", method=RequestMethod.POST)
	public String editProductSave(@PathVariable int productId, 
			@Valid Product product, BindingResult bindingResult) {
		System.out.println("======= in editProductSave");
		if(bindingResult.hasErrors()) {
			return "product/editProduct";
		}
		catalogService.updateProduct(product);
//		catalogService.updateProduct(productId, product);
		return "redirect:/product/list.html";
	}

	// ADD PRODUCT
	
	// Before showing Add Product Form
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addProduct(Model model) {
		System.out.println("======= in addProduct");
		model.addAttribute(new Product());
		return "product/addProduct";
	}

	// After submitting Add Product Form
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addProductSave(@Valid Product product, BindingResult bindingResult) {
		System.out.println("======= in addProductSave");
		if(bindingResult.hasErrors()){
			return "product/addProduct";
		}
		catalogService.insertProduct(product);
		return "redirect:/product/list.html";
	}

	// DELETE PRODUCT

	// After submitting Delete from Product List Form
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String deleteProducts(@ModelAttribute Items selectedProducts) {
		System.out.println("======= in deleteProducts");
		for (String productIdStr : selectedProducts.getItemList()) {
			System.out.println("delete product id=" + productIdStr);
			try { 
				int productId = Integer.parseInt(productIdStr);
				catalogService.removeProduct(productId);
			}
			catch (Exception e) {}
		}
		return "redirect:/product/list.html";
	}

}
