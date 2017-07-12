package com.xyz;

import java.util.ArrayList;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xyz.rest.client.ProductClient;
import com.xyz.rest.model.Product;

public class EStoreClientApp {

    public static void main(String[] args) throws Exception {
    	EStoreClientApp app = new EStoreClientApp();
        app.run();
    }
    
    public void run()	{
    	ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("/META-INF/spring/EStoreClientContext.xml");
        ProductClient client = (ProductClient) context.getBean("productClient");

        // Get products
    	System.out.println("\n==== Get Products ...");
    	ArrayList<Product> products = client.getProducts();
    	System.out.println("---- Total products=" + products.size());
    	for (int i=0; i<products.size(); i++) {
    	    System.out.println("product[" + i + "]=" + products.get(i));	
    	}

    	// Get products by id
    	for (int i=0; i<products.size(); i++) {
	    	System.out.println("\n==== Get Products by Id ...");
	       	System.out.println("----   [id=" + products.get(i).getProductId() + "] " 
	       			+ client.getProduct(products.get(i).getProductId()));
//	       	System.out.println("----   [id=" + products.get(1).getProductId() + "] " 
//	       			+ client.getProduct(products.get(1).getProductId()));
    	}

       	// Add a new product
//    	System.out.println("\n==== Add new Product ...");
//		Product p1 = new Product();
//		p1.setCatalogId(9999);
//		p1.setSku("ANDAPP10");
//		p1.setName("Android app 1.0");
//		p1.setAvailableQuantity(10);
//		p1.setUnitOfMeasure("Google Play");
//		client.createProduct(p1);
//		
//		Product p2 = new Product();
//		p2.setCatalogId(9999);
//		p2.setSku("IOSAPP10");
//		p2.setName("iPhone app 1.0");
//		p2.setAvailableQuantity(20);
//		p2.setUnitOfMeasure("AppStore");
//		client.createProduct(p2);
//
//		Product p3 = new Product();
//		p3.setCatalogId(9999);
//		p3.setSku("IOSAPP20");
//		p3.setName("iPad app 1.0");
//		p3.setAvailableQuantity(20);
//		p3.setUnitOfMeasure("AppStore");
//		client.createProduct(p3);
//		
//		Product p4 = new Product();
//		p4.setCatalogId(9999);
//		p4.setSku("HTML530");
//		p4.setName("INTRO HTML5");
//		p4.setAvailableQuantity(30);
//		p4.setUnitOfMeasure("B&N");
//		client.createProduct(p4);

    	products = client.getProducts();
    	for (int i=0; i<products.size(); i++) {
    	    System.out.println("product[" + i + "]=" + products.get(i));	
    	}

    	// Update, Delete
    	if (products.size() > 0){
    		int id = 0;
    		System.out.println("\n==== Update Product with Id="+id+" ...");
    		Product p5 = new Product();
    		p5.setProductId(products.get(id).getProductId());
    		p5.setCatalogId(products.get(id).getCatalogId());
    		p5.setSku("AFTVAPP15");
    		p5.setName("Amazon Fire TV app 2.1");
    		p5.setAvailableQuantity(100);
    		p5.setUnitOfMeasure("Amazon Store");
    		client.updateProduct(p5);
    		
    		id = 2;
    		System.out.println("\n==== Delete Product with Id="+id+" ...");
    		client.deleteProduct(products.get(id));
    	}

    	products = client.getProducts();
    	System.out.println("---- Total products=" + products.size());
    	for (Product product : products)	{
    		System.out.println("----	" + product);
    	}

//       	// Update a product
//    	System.out.println("\n==== Update a Product ...");
//    	product = products.get(products.size()-1);
//    	product.setName(product.getName() + "-2");
//    	client.updateProduct(product);
//
//    	products = client.getProducts();
//    	for (int i=0; i<products.size(); i++) {
//    	    System.out.println("product[" + i + "]=" + products.get(i));	
//    	}
//
//       	// Delete a product
//    	System.out.println("\n==== Delete a Product ...");
//    	product = products.get(products.size()-1);
//    	client.deleteProduct(product);
//
//    	products = client.getProducts();
//    	for (int i=0; i<products.size(); i++) {
//    	    System.out.println("product[" + i + "]=" + products.get(i));	
//    	}
    }
}
