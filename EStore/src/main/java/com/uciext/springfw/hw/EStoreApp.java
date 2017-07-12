package com.uciext.springfw.hw;

import java.util.List;

import org.apache.log4j.Logger;

import com.uciext.springfw.hw.EStoreApp;
import com.uciext.springfw.hw.catalogs.model.Catalog;
import com.uciext.springfw.hw.catalogs.model.Product;
import com.uciext.springfw.hw.catalogs.service.CatalogService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EStoreApp {

    private static Logger logger = Logger.getLogger(EStoreApp.class.getName());

    public static void main(String[] args) throws Exception {
    	EStoreApp app = new EStoreApp();
        app.run();
    }
    
    public void run() {	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/EStoreConfig.xml");
	CatalogService catalogService = (CatalogService) context.getBean("catalogService");

	// Catalogs
	logger.info("\n==== Get Catalogs ...");
	List<Catalog> catalogs = catalogService.getCatalogs();
	logger.info("---- Total catalogs=" + catalogs.size());
	for (Catalog catalog : catalogs)	{
		logger.info("----	" + catalog);
	}
		
	// Insert catalogs
	if (catalogs.size() == 0)	{
		logger.info("\n=== Insert Catalogs ...");
		Catalog c1 = new Catalog();
		c1.setCatalogName("Catalog Android apps");
		catalogService.insertCatalog(c1);
		Catalog c2 = new Catalog();
		c2.setCatalogName("Catalog iOS apps");
		catalogService.insertCatalog(c2);

		Catalog c3 = new Catalog();
		c3.setCatalogName("Catalog HTML5 books");
		catalogService.insertCatalog(c3);
		Catalog c4 = new Catalog();
		c4.setCatalogName("Catalog .NET apps");
		catalogService.insertCatalog(c4);
		
		logger.info("\n==== Get Catalogs after the Insert ...");
        catalogs = catalogService.getCatalogs();
        logger.info("---- Total catalogs=" + catalogs.size());
        for (Catalog catalog : catalogs) {
        	logger.info("----   " + catalog);
        }
	}	
	
	//--------- Products
	
	// Products
	logger.info("\n==== Get Products ...");
	List<Product> products = catalogService.getProducts();
	logger.info("---- Total products=" + products.size());
	for (Product product : products)	{
		logger.info("----	" + product);
	}
	
	// Insert products
	if (products.size() == 0)	{
		logger.info("\n=== Insert Products ...");
		
		Product p1 = new Product();
		p1.setCatalogId(catalogs.get(0).getCatalogId());
		p1.setSku("ANDAPP10");
		p1.setName("Android app 1.0");
		p1.setAvailableQuantity(10);
		p1.setUnitOfMeasure("Google Play");
		catalogService.insertProduct(p1);
		
		Product p2 = new Product();
		p2.setCatalogId(catalogs.get(1).getCatalogId());
		p2.setSku("IOSAPP10");
		p2.setName("iPhone app 1.0");
		p2.setAvailableQuantity(20);
		p2.setUnitOfMeasure("AppStore");
		catalogService.insertProduct(p2);

		Product p3 = new Product();
		p3.setCatalogId(catalogs.get(1).getCatalogId());
		p3.setSku("IOSAPP20");
		p3.setName("iPad app 1.0");
		p3.setAvailableQuantity(20);
		p3.setUnitOfMeasure("AppStore");
		catalogService.insertProduct(p3);
		
		Product p4 = new Product();
		p4.setCatalogId(catalogs.get(2).getCatalogId());
		p4.setSku("HTML530");
		p4.setName("INTRO HTML5");
		p4.setAvailableQuantity(30);
		p4.setUnitOfMeasure("B&N");
		catalogService.insertProduct(p4);
		
		logger.info("\n==== Get Products after the Insert ...");
		products = catalogService.getProducts();
        logger.info("---- Total products=" + products.size());
        for (Product product : products) {
        	logger.info("----   " + product);
        }
	}	
	
	// Get a product
	logger.info("\n==== Get Product by Id ...");
	for (int i = 0; i < products.size(); i++){
		logger.info("----   [id=" + products.get(i).getProductId() + "] " 
	   			+ catalogService.getProduct(products.get(i).getProductId()));
	}	

	// Update, Delete
	if (products.size() > 0){
		int id = 0;
		logger.info("\n==== Update Product with Id="+id+" ...");
		Product p5 = new Product();
		p5.setProductId(products.get(id).getProductId());
		p5.setCatalogId(products.get(id).getCatalogId());
		p5.setSku("AFTVAPP11");
		p5.setName("Amazon Fire TV app 1.1");
		p5.setAvailableQuantity(15);
		p5.setUnitOfMeasure("Amazon Store");
		catalogService.updateProduct(p5);
		
		id = 2;
		logger.info("\n==== Delete Product with Id="+id+" ...");
		catalogService.removeProduct(products.get(id).getProductId());
	}

	products = catalogService.getProducts();
	logger.info("---- Total products=" + products.size());
	for (Product product : products)	{
		logger.info("----	" + product);
	}
	
	//----------

	// Get a catalog
	logger.info("\n==== Get Catalog by Id ...");
	for (int i = 0; i < catalogs.size(); i++){
		logger.info("----   [id=" + catalogs.get(i).getCatalogId() + "] " 
	   			+ catalogService.getCatalog(catalogs.get(i).getCatalogId()));
	}
	
	// Update, Delete
	if (catalogs.size() > 0){
		int id = 0;
		logger.info("\n==== Update Catalog with Id="+id+" ...");
		Catalog c5 = new Catalog();
		c5.setCatalogId(catalogs.get(id).getCatalogId());
		c5.setCatalogName("Catalog Amazon apps");
		catalogService.updateCatalog(c5);
		
		id = 2;
		logger.info("\n==== Delete Catalog with Id="+id+" ...");
		catalogService.removeCatalog(catalogs.get(id).getCatalogId());
	}

	// Get catalog list
	catalogs = catalogService.getCatalogs();
	logger.info("---- Total catalogs=" + catalogs.size());
	for (Catalog catalog : catalogs)	{
		logger.info("----	" + catalog);
	}
    
  }
}
