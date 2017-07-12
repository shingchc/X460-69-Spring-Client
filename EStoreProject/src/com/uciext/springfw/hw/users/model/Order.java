package com.uciext.springfw.hw.users.model;

import java.sql.Timestamp;
import java.util.Map;

public class Order {

	private java.sql.Timestamp orderCreated;
	private int totalAmount;
	private int confirmNumber;
	private String user;
	private Map<String, ProductOrder> productOrders;
	private int orderId;
	
	public Timestamp getOrderCreated()	{
		return orderCreated;
	}

	public void setOrderCreated(Timestamp timestamp)	{
		this.orderCreated = timestamp;
	}
	
	public int getTotalAmount()	{
		return this.totalAmount;
	}
	
	public void setTotalAmount(int value)	{
		this.totalAmount= value;
	}
	
	public String getUser()	{
		return this.user;
	}
	
	public void setUser(String value)	{
		this.user = value;
	}
	
	public Map<String, ProductOrder> getProductOrders()	{
		return this.productOrders;
	}
	
	public void setProductOrders(Map<String, ProductOrder> value){
		this.productOrders = value;
	}
	
	public int getOrderId()	{
		return this.orderId;
	}
	
	public void setOrderId(int value)	{
		this.orderId = value;
	}
	
    public String toString() {
        StringBuilder buff = new StringBuilder("[Order: ")
        .append("orderIdd=").append(this.orderId)
        .append(", orderCreated=").append(this.orderCreated)
        .append(", totalAmount=").append(this.totalAmount)
        .append(", confirmNumber=").append(this.confirmNumber)
        .append(", user=").append(this.user);
        if (this.productOrders != null)	{
 	       for (ProductOrder productOrder : productOrders.values()) {
 	    	   buff.append(productOrder).append(", ");
 	       }
        }
        buff.append("]");

        return buff.toString();
     }

	public int getConfirmNumber() {
		return this.confirmNumber;
	}
	
	public void setConfirmNumber(int value)	{
		this.confirmNumber = value;
	}
}
