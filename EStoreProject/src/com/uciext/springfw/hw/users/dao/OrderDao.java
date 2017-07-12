package com.uciext.springfw.hw.users.dao;

import java.util.List;

import com.uciext.springfw.hw.users.model.Order;

public interface OrderDao {

	public void insertOrder(Order order);
	public Order findOrderById(int orderId);
	public List<Order> findOrders();
	public void updateOrder(Order order);
	public void removeOrder(int orderId);
}
