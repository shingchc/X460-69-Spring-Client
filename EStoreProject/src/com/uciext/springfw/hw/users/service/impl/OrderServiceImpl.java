package com.uciext.springfw.hw.users.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.uciext.springfw.hw.users.dao.OrderDao;
import com.uciext.springfw.hw.users.dao.ProductOrderDao;
import com.uciext.springfw.hw.users.model.Order;
import com.uciext.springfw.hw.users.model.ProductOrder;
import com.uciext.springfw.hw.users.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());
	
	// DAO classes
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao)	{
		this.orderDao = orderDao;
	}
	
	private ProductOrderDao productOrderDao;
	public void setProductOrderDao(ProductOrderDao productOrderDao)	{
		this.productOrderDao = productOrderDao;
	}

	private Order order;
	
	public OrderServiceImpl() {
	    logger.info("In OrderServiceImpl()");
	}
	
	public Order getOrder(int orderId) {
		logger.info("In getOrder");
		return this.orderDao.findOrderById(orderId);
	}

	public void insertOrder(Order order) {
	    logger.info("In insertOrder()");
	    this.orderDao.insertOrder(order);
	}

	public void updateOrder(Order order) {
	    logger.info("In updateOrder()");
		this.orderDao.updateOrder(order);
	}

	public void removeOrder(int orderId) {
	    logger.info("In removeOrder()");
		this.orderDao.removeOrder(orderId);
	}

	public List<Order> getOrders() {
		logger.info("In getOrders");
		return this.orderDao.findOrders();
	}

	@Override
	public ProductOrder getProductOrder(int productOrderId) {
		logger.info("In getProductOrder");
		return this.productOrderDao.findProductOrderById(productOrderId);
	}

	@Override
	public void insertProductOrder(ProductOrder productOrder) {
		logger.info("In insertProductOrder");
		this.productOrderDao.insertProductOrder(productOrder);
	}

	@Override
	public void updateProductOrder(ProductOrder productOrder) {
		logger.info("In updateProductOrder");
		this.productOrderDao.updateProductOrder(productOrder);
	}

	@Override
	public void removeProductOrder(int productOrderId) {
		logger.info("In removeProductOrder");
		this.productOrderDao.removeProductOrder(productOrderId);
	}

	@Override
	public List<ProductOrder> getProductOrders() {
		logger.info("In getProductOrders");
		return this.productOrderDao.findProductOrders();
	}
}
