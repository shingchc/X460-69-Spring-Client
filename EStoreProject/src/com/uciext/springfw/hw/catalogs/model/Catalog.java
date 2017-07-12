package com.uciext.springfw.hw.catalogs.model;

import java.lang.StringBuilder;
import java.util.Map;

import com.uciext.springfw.hw.exception.InvalidProductException;

public class Catalog {

	// Properties
    private String catalogName;
    private String lastUpdateDate;
    private Map<String, Product> products;
    private int catalogId;

    public String getCatalogName() {
        return catalogName;
    }
    public void setCatalogName(String value) {
        this.catalogName = value;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(String value) {
        this.lastUpdateDate = value;
    }

    public Map<String, Product> getProducts() {
        return products;
    }
    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public Product getProduct(String sku) {
        return products.get(sku);
    }

    public void addProduct(Product product) throws InvalidProductException {
    	if (product == null) {
    		throw new InvalidProductException("Product is not defined.");
    	}
    	if (product.getSku() == null || product.getSku().trim().length() == 0) {
    		throw new InvalidProductException("Product SKU is not defined.");
    	}
    	products.put(product.getSku(), product);
    }

    public String toString() {
       StringBuilder buff = new StringBuilder("[Catalog: ")
       .append("catalogName=").append(catalogName)
       .append(", lastUpdateDate=").append(lastUpdateDate)
       .append(", products=");
       if (products != null)	{
	       for (Product product : products.values()) {
	    	   buff.append(product).append(", ");
	       }
       }
       buff.append("]");

       return buff.toString();
    }
	public void setCatalogId(int catalogId) {
		// TODO Auto-generated method stub
		this.catalogId = catalogId;	
	}
	
	public int getCatalogId() {
		// TODO Auto-generated method stub
		return this.catalogId;
	}
}
