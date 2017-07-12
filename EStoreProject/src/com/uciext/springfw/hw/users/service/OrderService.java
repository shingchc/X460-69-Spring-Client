package com.uciext.springfw.hw.users.service;

import java.util.List;

import com.uciext.springfw.hw.users.model.Order;
import com.uciext.springfw.hw.users.model.ProductOrder;

public interface OrderService {
	// Orders
	public Order getOrder(int orderId);
	public void insertOrder(Order order);
	public void updateOrder(Order order);
	public void removeOrder(int orderId);
	public List<Order> getOrders();
	
	// ProductOrders
	public ProductOrder getProductOrder(int productOrderId);
	public void insertProductOrder(ProductOrder productOrder);
	public void updateProductOrder(ProductOrder productOrder);
	public void removeProductOrder(int productOrderId);
	public List<ProductOrder> getProductOrders();
}
