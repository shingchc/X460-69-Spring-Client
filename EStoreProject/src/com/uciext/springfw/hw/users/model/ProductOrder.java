package com.uciext.springfw.hw.users.model;

public class ProductOrder {

	private int productOrderId;
	private int productId;
	private int orderId;
	private int orderAmount;
	
	public int getProductOrderId()	{
		return this.productOrderId;
	}
	
	public void setProductOrderId(int value)	{
		this.productOrderId = value;
	}
	
	public int getProductId()	{
		return this.productId;
	}
	
	public void setProductId(int value)	{
		this.productId = value;
	}
	
	public int getOrderId()	{
		return this.orderId;
	}
	
	public void setOrderId(int value)	{
		this.orderId = value;
	}
	
	public int getOrderAmount()	{
		return this.orderAmount;
	}
	
	public void setOrderAmount(int value)	{
		this.orderAmount = value;
	}
	
    public String toString() {
        StringBuilder buff = new StringBuilder("\n[ProductOrder: ")
        .append("orderAmount=").append(this.orderAmount)
        .append("]")
        ;

        return buff.toString();
     }
}
