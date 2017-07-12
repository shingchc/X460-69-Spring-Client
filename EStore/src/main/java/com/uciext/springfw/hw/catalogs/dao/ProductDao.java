package com.uciext.springfw.hw.catalogs.dao;

import java.util.List;


import com.uciext.springfw.hw.catalogs.model.Catalog;
import com.uciext.springfw.hw.catalogs.model.Product;

public interface ProductDao {

	public void insertProduct(Product product);
	public Product findProductById(int productId);
	public List<Product> findProducts();
	public void updateProduct(Product product);
	public void removeProduct(int productId);
//	public List<Product> findProductsByCatalog(Catalog catalog);

}
