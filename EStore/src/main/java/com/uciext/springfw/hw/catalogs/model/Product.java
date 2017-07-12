package com.uciext.springfw.hw.catalogs.model;

public class Product {

	private String sku;
	private String name;
	private String unitOfMeasure;
	private Float price;
	private Integer availableQuantity;
	private int productId;
	private int catalogId;

	public Product() {
	}

	public Product(String sku, String name, String unitOfMeasure, Float price,
		Integer availableQuantity) {
		
		this.sku = sku;
		this.name = name;
		this.unitOfMeasure = unitOfMeasure;
		this.price = price;
		this.availableQuantity = availableQuantity;
	}

	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

    public String toString() {
        StringBuilder buff = new StringBuilder("\n[Product: ")
        .append("sku=").append(sku)
        .append(", name=").append(name)
        .append(", unitOfMeasure=").append(unitOfMeasure)
        .append(", price=").append(price)
        .append(", availableQuantity=").append(availableQuantity)
        .append("]")
        ;

        return buff.toString();
     }

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setCatalogId(int catalogId)	{
		this.catalogId = catalogId;
	}
	
	public int getCatalogId()	{
		return this.catalogId;
	}
	
	public Object getCatalog() {
		// TODO Auto-generated method stub
		return null;
	}
}
