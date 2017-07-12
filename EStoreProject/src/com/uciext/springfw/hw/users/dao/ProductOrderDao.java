package com.uciext.springfw.hw.users.dao;

import java.util.List;

import com.uciext.springfw.hw.users.model.ProductOrder;

public interface ProductOrderDao {

	public void insertProductOrder(ProductOrder productOrder);
	public ProductOrder findProductOrderById(int productOrderId);
	public List<ProductOrder> findProductOrders();
	public void updateProductOrder(ProductOrder productOrder);
	public void removeProductOrder(int productOrderId);
}
