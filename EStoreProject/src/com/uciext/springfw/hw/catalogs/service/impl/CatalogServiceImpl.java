package com.uciext.springfw.hw.catalogs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uciext.springfw.hw.catalogs.dao.CatalogDao;
import com.uciext.springfw.hw.catalogs.dao.ProductDao;
import com.uciext.springfw.hw.catalogs.model.Catalog;
import com.uciext.springfw.hw.catalogs.model.Product;
import com.uciext.springfw.hw.catalogs.service.CatalogService;
import com.uciext.springfw.hw.exception.InvalidProductException;

public class CatalogServiceImpl implements CatalogService {

    private static Logger logger = Logger.getLogger(CatalogServiceImpl.class.getName());

    // DAO classes
    private CatalogDao catalogDao;
    public void setCatalogDao(CatalogDao catalogDao)	{
    	this.catalogDao = catalogDao;
    }
    
    private ProductDao productDao;
    public void setProductDao(ProductDao productDao)	{
    	this.productDao = productDao;
    }
    
    private Catalog catalog;
    
	public CatalogServiceImpl() {
	    logger.info("In CatalogServiceImpl()");
	}
	
	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Product getProduct(String sku) {
		return catalog.getProduct(sku);
	}

	public void addProduct(Product product) throws InvalidProductException {
		catalog.addProduct(product);
	}

	@Override
	public Product getProduct(int productId) {
		logger.info("--- getProduct()");
		return productDao.findProductById(productId);
	}

	@Override
	public void insertProduct(Product product) {
		logger.info("--- insertProduct()");
		productDao.insertProduct(product);	
	}

	@Override
	public void updateProduct(Product product) {
		logger.info("--- updateProduct()");
		productDao.updateProduct(product);	
	}

	@Override
	public void removeProduct(int productId) {
		logger.info("--- removeProduct()");
		productDao.removeProduct(productId);		
	}

	@Override
	public List<Product> getProducts() {
		logger.info("--- getProducts()");
		return productDao.findProducts();
	}

	@Override
	public Catalog getCatalog(int catalogId) {
		logger.info("--- getCatalog()");
		return catalogDao.findCatalogById(catalogId);
	}

	@Override
	public void insertCatalog(Catalog catalog) {
		logger.info("--- insertCatalog()");
		catalogDao.insertCatalog(catalog);
	}

	@Override
	public void updateCatalog(Catalog catalog) {
		logger.info("--- updateCatalog()");
		catalogDao.updateCatalog(catalog);
	}

	@Override
	public void removeCatalog(int catalogId) {
		logger.info("--- removeCatalog()");
		catalogDao.removeCatalog(catalogId);
	}

	@Override
	public List<Catalog> getCatalogs() {
		logger.info("--- getCatalogs()");
		return catalogDao.findCatalogs();
	}
}
