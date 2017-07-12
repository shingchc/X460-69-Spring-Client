package com.uciext.springfw.hw.users.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productOrderList")
public class ProductOrderList {
	
	ArrayList<ProductOrder> productOrders = new ArrayList<ProductOrder>(); 
	
	public void setProductOrderList(ArrayList<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}
	
	public ArrayList<ProductOrder> getProductOrderList() {
		return productOrders;
	}
}
