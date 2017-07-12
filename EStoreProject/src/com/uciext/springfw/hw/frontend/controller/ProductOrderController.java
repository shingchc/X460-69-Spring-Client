package com.uciext.springfw.hw.frontend.controller;

import java.sql.Timestamp;
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

import com.uciext.springfw.hw.catalogs.model.Catalog;
import com.uciext.springfw.hw.catalogs.model.Items;
import com.uciext.springfw.hw.catalogs.model.Product;
import com.uciext.springfw.hw.catalogs.service.CatalogService;
import com.uciext.springfw.hw.users.model.Order;
import com.uciext.springfw.hw.users.model.ProductOrder;
import com.uciext.springfw.hw.users.service.OrderService;

@Controller
@RequestMapping("/productOrder")
public class ProductOrderController {

	private OrderService orderService;
	
	@Inject
	public void setOrderService(OrderService orderService)	{
		this.orderService = orderService;
	}
	
	private CatalogService catalogService;
	
	@Inject
	public void setCatalogService(CatalogService catalogService)	{
		this.catalogService = catalogService;
	}
	
	// VIEW LIST OF PRODUCT ORDER

	@RequestMapping("/list")
	public ModelAndView productOrderList(Model model) {
		System.out.println("======= in productOrderList");
		List<ProductOrder> productOrderList = orderService.getProductOrders();
		model.addAttribute("productOrderList", productOrderList);
		model.addAttribute("selectedProductOrders", new Items());
		return new ModelAndView("productOrder/productOrderList3");
	}
	
	// VIEW PRODUCT ORDER DETAILS

	@RequestMapping("/view")
	public ModelAndView productOrderView(@RequestParam("productOrderId") int productOrderId, Model model) {
		System.out.println("======= in productOrderView");
		ProductOrder productOrder = orderService.getProductOrder(productOrderId);
		model.addAttribute("productOrder", productOrder);
		return new ModelAndView("productOrder/viewProductOrder");
	}

	// UPDATE PRODUCT ORDER

	@RequestMapping(value="/edit/{productOrderId}", method=RequestMethod.GET)
	public String editProductOrder(@PathVariable int productOrderId, Model model) {
		System.out.println("======= in editProductOrder");
		ProductOrder productOrder = orderService.getProductOrder(productOrderId);
		model.addAttribute("productOrder", productOrder);
		return "productOrder/editProductOrder";
	}
	
	@RequestMapping(value="/edit/{productOrderId}", method=RequestMethod.POST)
	public String editOrderSave(@PathVariable int productOrderId,
			@Valid ProductOrder productOrder, BindingResult bindingResult) {
		// compare productOrder orderAmount with product availableQuantity
		int newProductId = productOrder.getProductId();
		Product refProduct = catalogService.getProduct(newProductId);
		int refProductAvailableQuantity = refProduct.getAvailableQuantity();
		
		System.out.println("======= in editProductOrderSave: newProductOrderId=" + newProductId
				+ " productOrderAmount=" + productOrder.getOrderAmount()
				+ " productAvailableQuantity=" + refProductAvailableQuantity);
		
		// check if productOrder orderAmount is greater than product availableQuantity
		if(bindingResult.hasErrors() || productOrder.getOrderAmount() > refProductAvailableQuantity) {
			return "productOrder/editProductOrder";
		}
		
		// update product availableQuantity
		int newProductAvailableQuantity = refProductAvailableQuantity - productOrder.getOrderAmount();
		refProduct.setAvailableQuantity(newProductAvailableQuantity);
		catalogService.updateProduct(refProduct);

		// update Order totalAmount
		int newOrderId = productOrder.getOrderId();
		Order refOrder = orderService.getOrder(newOrderId);
		int refOrderTotalAmount = refOrder.getTotalAmount();
		int newOrderTotalAmount = refOrderTotalAmount + productOrder.getOrderAmount();
		refOrder.setTotalAmount(newOrderTotalAmount);
		orderService.updateOrder(refOrder);
		
		orderService.updateProductOrder(productOrder);
		
		//catalogService.updateCatalog(catalogId, catalog);
		return "redirect:/productOrder/list.html";
	}
	
	// ADD PRODUCT ORDER
	
	// Before showing Add Product Order Form
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addProductOrder(Model model) {
		System.out.println("======= in addProductOrder");
		model.addAttribute(new ProductOrder());
		return "productOrder/addProductOrder";
	}

	// After submitting Add Product Order Form
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addProductOrderSave(@Valid ProductOrder productOrder, BindingResult bindingResult) {

		// compare productOrder orderAmount with product availableQuantity
		int newProductId = productOrder.getProductId();
		Product refProduct = catalogService.getProduct(newProductId);
		int refProductAvailableQuantity = refProduct.getAvailableQuantity();

		System.out.println("======= in addProductOrderSave newProductOrderId=" + newProductId
				+ " productOrderAmount=" + productOrder.getOrderAmount()
				+ " productAvailableQuantity=" + refProductAvailableQuantity);

		// check if productOrder orderAmount is greater than product availableQuantity
		if(bindingResult.hasErrors() || productOrder.getOrderAmount() > refProductAvailableQuantity) {
			return "productOrder/addProductOrder";
		}
		
		// update product availableQuantity
		int newProductAvailableQuantity = refProductAvailableQuantity - productOrder.getOrderAmount();
		refProduct.setAvailableQuantity(newProductAvailableQuantity);
		catalogService.updateProduct(refProduct);

		// update Order totalAmount
		int newOrderId = productOrder.getOrderId();
		Order refOrder = orderService.getOrder(newOrderId);
		int refOrderTotalAmount = refOrder.getTotalAmount();
		int newOrderTotalAmount = refOrderTotalAmount + productOrder.getOrderAmount();
		refOrder.setTotalAmount(newOrderTotalAmount);
		orderService.updateOrder(refOrder);
		
		orderService.insertProductOrder(productOrder);
		return "redirect:/productOrder/list.html";
	}

	// DELETE PRODUCT ORDER

	// After submitting Delete from Product Order List Form
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String deleteProductOrders(@ModelAttribute Items selectedProductOrders) {
		System.out.println("======= in deleteProductOrders");
		for (String productOrderIdStr : selectedProductOrders.getItemList()) {
			System.out.println("delete product order id=" + productOrderIdStr);
			try { 
				int productOrderId = Integer.parseInt(productOrderIdStr);
				
				ProductOrder productOrder = orderService.getProductOrder(productOrderId);
				
				// Update Product availableQuantity
				int productId = productOrder.getProductId();
				Product product = catalogService.getProduct(productId);
				int newProductAvailableQuantity = product.getAvailableQuantity() + productOrder.getOrderAmount();
				product.setAvailableQuantity(newProductAvailableQuantity);
				catalogService.updateProduct(product);
				
				// Update Order totalAmount
				int orderId = productOrder.getOrderId();
				Order order = orderService.getOrder(orderId);
				int newOrderTotalAmount = order.getTotalAmount() - productOrder.getOrderAmount();
				order.setTotalAmount(newOrderTotalAmount);
				orderService.updateOrder(order);
				
				orderService.removeProductOrder(productOrderId);
			}
			catch (Exception e) {}
		}
		return "redirect:/productOrder/list.html";
	}
}
