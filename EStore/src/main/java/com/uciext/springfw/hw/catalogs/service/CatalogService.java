package com.uciext.springfw.hw.catalogs.service;

import java.util.List;

import com.uciext.springfw.hw.catalogs.model.Catalog;
import com.uciext.springfw.hw.catalogs.model.Product;
import com.uciext.springfw.hw.exception.InvalidProductException;

public interface CatalogService {
	//public Catalog getCatalog();
	//public Product getProduct(String sku);
	//public void addProduct(Product product) throws InvalidProductException;

	// Products
	public Product getProduct(int productId);
	public void insertProduct(Product product);
	public void updateProduct(Product product);
	public void removeProduct(int productId);
	public List<Product> getProducts();
	
	// Catalogs
	public Catalog getCatalog(int catalogId);
	public void insertCatalog(Catalog catalog);
	public void updateCatalog(Catalog catalog);
	public void removeCatalog(int catalogId);
	public List<Catalog> getCatalogs();
}
