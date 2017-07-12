package com.uciext.springfw.hw.catalogs.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.uciext.springfw.hw.catalogs.dao.ProductDao;
import com.uciext.springfw.hw.catalogs.model.Catalog;
import com.uciext.springfw.hw.catalogs.model.Product;
import com.uciext.springfw.hw.catalogs.service.CatalogService;
import com.uciext.springfw.hw.common.Util;

public class JdbcProductDao implements ProductDao {
	
    protected static Logger logger = Logger.getLogger(JdbcProductDao.class.getName());

	// SQL statements
	private static final String SQL_INSERT_PRODUCT = 
			"INSERT INTO products (product_id, catalog_id, sku, product_name, available_quantity, uom) "
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_FIND_PRODUCT_BY_ID = 
			"SELECT * FROM products WHERE product_id = ?";
	private static final String SQL_FIND_PRODUCTS_ADMIN =
			"SELECT * FROM products";
	private static final String SQL_FIND_PRODUCTS = 
			"SELECT * FROM products where available_quantity > 0";
	private static final String SQL_UPDATE_PRODUCT_BY_ID =
			"UPDATE products SET sku = ?, product_name = ?, available_quantity = ?, uom =? WHERE product_id = ?";
	private static final String SQL_DELETE_PRODUCT_BY_ID =
			"DELETE from products where product_id = ?";
	//	private static final String SQL_FIND_COURSES_BY_DEPARTMENT_ID = 
//			"SELECT * FROM courses WHERE department_id = ?";

 	// Optimization
	private static final String SQL_FIND_PRODUCTS_BY_CATALOG_ID = 
			"SELECT * FROM products WHERE catalog_id = ? AND availalbe_quantity > 0";

	// Datasource
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 
	// Course service
	private CatalogService catalogService;
	 
	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
 
	//=================================================
	// DB methods

	@Override
	public void insertProduct(Product product) {
		logger.info("In insertProduct product=" + product);
		Connection conn = null;
 
		try {
			product.setProductId(Util.getRandomInt());

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PRODUCT);
			ps.setInt(1, product.getProductId());
			ps.setInt(2, product.getCatalogId());
			ps.setString(3, product.getSku());
			ps.setString(4, product.getName());
			ps.setInt(5, product.getAvailableQuantity());
			ps.setString(6, product.getUnitOfMeasure());
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}	

	public List<Product> findProducts()	{
		logger.info("In findProducts");
		Connection conn = null;
		List<Product> products = new ArrayList<Product>();
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_PRODUCTS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setCatalogId(rs.getInt("catalog_id"));
				product.setSku(rs.getString("sku"));
				product.setName(rs.getString("product_name"));
				product.setAvailableQuantity(rs.getInt("available_quantity"));
				product.setUnitOfMeasure(rs.getString("uom"));
				products.add(product);
			}
			rs.close();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		logger.info("exit findProducts");
		return products;
	}
	
	@Override
	public Product findProductById(int productId) {
		logger.info("In findProductById productId=" + productId);
		Connection conn = null;
		Product product = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_PRODUCT_BY_ID);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setCatalogId(rs.getInt("catalog_id"));
				product.setSku(rs.getString("sku"));
				product.setName(rs.getString("product_name"));
				product.setAvailableQuantity(rs.getInt("available_quantity"));
				product.setUnitOfMeasure(rs.getString("uom"));
			}
			rs.close();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		logger.info("exit findProductById product=" + product);
		return product;
	}
	
	public void updateProduct(Product product)	{
		logger.info("In updateProduct");
		Connection conn = null;	

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_PRODUCT_BY_ID);
			ps.setString(1, product.getSku());
			ps.setString(2, product.getName());
			ps.setInt(3, product.getAvailableQuantity());
			ps.setString(4, product.getUnitOfMeasure());
			ps.setInt(5, product.getProductId());
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public void removeProduct(int productId)	{
		logger.info("In removeProduct");
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PRODUCT_BY_ID);
			ps.setInt(1, productId);
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}	
	}
}
