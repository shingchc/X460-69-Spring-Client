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
import com.uciext.springfw.hw.users.model.Order;
import com.uciext.springfw.hw.users.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	private OrderService orderService;
	
	@Inject
	public void setOrderService(OrderService orderService)	{
		this.orderService = orderService;
	}
	
	// VIEW LIST OF ORDER

	@RequestMapping("/list")
	public ModelAndView orderList(Model model) {
		System.out.println("======= in orderList");
		List<Order> orderList = orderService.getOrders();
		model.addAttribute("orderList", orderList);
		model.addAttribute("selectedOrders", new Items());
		return new ModelAndView("order/orderList3");
	}
	
	// VIEW ORDER DETAILS

	@RequestMapping("/view")
	public ModelAndView orderView(@RequestParam("orderId") int orderId, Model model) {
		System.out.println("======= in orderView");
		Order order = orderService.getOrder(orderId);
		model.addAttribute("order", order);
		return new ModelAndView("order/viewOrder");
	}

	// UPDATE ORDER

	@RequestMapping(value="/edit/{orderId}", method=RequestMethod.GET)
	public String editOrder(@PathVariable int orderId, Model model) {
		System.out.println("======= in editOrder");
		Order order = orderService.getOrder(orderId);
		model.addAttribute("order", order);
		return "order/editOrder";
	}
	
	@RequestMapping(value="/edit/{orderId}", method=RequestMethod.POST)
	public String editOrderSave(@PathVariable int orderId,
			@Valid Order order, BindingResult bindingResult) {
		System.out.println("======= in editOrderSave");
		if(bindingResult.hasErrors()) {
			return "order/editOrder";
		}
		orderService.updateOrder(order);
		//catalogService.updateCatalog(catalogId, catalog);
		return "redirect:/order/list.html";
	}
	
	// ADD ORDER
	
	// Before showing Add Order Form
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addOrder(Model model) {
		System.out.println("======= in addOrder");
		model.addAttribute(new Order());
		return "order/addOrder";
	}

	// After submitting Add Order Form
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addOrderSave(@Valid Order order, BindingResult bindingResult) {
		System.out.println("======= in addOrderSave");
		if(bindingResult.hasErrors()){
			return "order/addOrder";
		}
		orderService.insertOrder(order);
		return "redirect:/order/list.html";
	}

	// DELETE ORDER

	// After submitting Delete from Order List Form
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String deleteOrders(@ModelAttribute Items selectedOrders) {
		System.out.println("======= in deleteOrders");
		for (String orderIdStr : selectedOrders.getItemList()) {
			System.out.println("delete order id=" + orderIdStr);
			try { 
				int orderId = Integer.parseInt(orderIdStr);
				orderService.removeOrder(orderId);
			}
			catch (Exception e) {}
		}
		return "redirect:/order/list.html";
	}
}
