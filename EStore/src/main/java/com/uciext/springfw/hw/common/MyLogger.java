package com.uciext.springfw.hw.common;

import org.aspectj.lang.JoinPoint;

import com.uciext.springfw.hw.catalogs.model.Product;

public class MyLogger {

    public void logBeforeGetProduct(JoinPoint joinpoint, String sku) {
    	System.out.println("   MyLogger: Called get product with SKU: " + sku);
    }
/*
    public void logAfterGetProduct(JoinPoint joinpoint, Object object) {
    	System.out.println("   MyLogger: Log after getting product, returned: " + object);
    }
*/	
    public void logAfterGetProduct(JoinPoint joinpoint, Product product) {
    	System.out.println("   MyLogger: Log after getting product, returned: " + product.getName());
    }

    public void logErrorAddProduct(JoinPoint joinpoint, Exception ex) {
    	System.out.println("   MyLogger: Error occured while adding a product: " + ex.getMessage());
    }    
}
