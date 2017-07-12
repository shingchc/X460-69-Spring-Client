package com.uciext.springfw.hw.catalogs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

	@XmlElement
	private String sku;
	@XmlElement
	private String name;
	@XmlElement
	private String unitOfMeasure;
	@XmlElement
	private Float price;
	@XmlElement
	private Integer availableQuantity;
	@XmlElement
	private int productId;
	@XmlElement
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
