package com.uciext.springfw.hw.users.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.uciext.springfw.hw.catalogs.model.Product;
import com.uciext.springfw.hw.common.Util;
import com.uciext.springfw.hw.users.dao.ProductOrderDao;
import com.uciext.springfw.hw.users.model.ProductOrder;
import com.uciext.springfw.hw.users.service.OrderService;

public class JdbcProductOrderDao implements ProductOrderDao {

	protected static Logger logger = Logger.getLogger(JdbcProductOrderDao.class);
	
	// SQL statements
	private static final String SQL_INSERT_PRODUCT_ORDER =
			"INSERT INTO productOrders (product_order_id, order_id, product_id, order_amount)"
			+ " VALUES (?, ?, ?, ?)";
	
	private static final String SQL_FIND_PRODUCT_ORDER_BY_ID =
			"SELECT * FROM productOrders WHERE product_order_id = ?";
	
	private static final String SQL_FIND_PRODUCT_ORDERS =
			"SELECT * FROM productOrders";
	
	private static final String SQL_UPDATE_PRODUCT_ORDER_BY_ID =
			"UPDATE productOrders SET order_amount = ? WHERE product_order_id = ?";
	
	private static final String SQL_DELETE_PRODUCT_ORDER_BY_ID =
			"DELETE from productOrders where product_order_id = ?";
	
	// Datasource
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// Order service
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService)	{
		this.orderService = orderService;
	}
	
	//=================================================
	// DB methods

	@Override
	public void insertProductOrder(ProductOrder productOrder) {
		logger.info("In insertProductOrder productOrder=" + productOrder);
		Connection conn = null;
 
		try {
			productOrder.setProductOrderId(Util.getRandomInt());

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PRODUCT_ORDER);
			ps.setInt(1, productOrder.getProductOrderId());
			ps.setInt(2, productOrder.getOrderId());
			ps.setInt(3, productOrder.getProductId());
			ps.setInt(4, productOrder.getOrderAmount());
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

	@Override
	public ProductOrder findProductOrderById(int productOrderId) {
		logger.info("In findProductOrderById productOrderId=" + productOrderId);
		Connection conn = null;
		ProductOrder productOrder = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_PRODUCT_ORDER_BY_ID);
			ps.setInt(1, productOrderId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				productOrder = new ProductOrder();
				productOrder.setProductOrderId(rs.getInt("product_order_id"));
				productOrder.setOrderId(rs.getInt("order_id"));
				productOrder.setProductId(rs.getInt("product_id"));
				productOrder.setOrderAmount(rs.getInt("order_amount"));
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
		logger.info("exit findProductOrderById productOrder=" + productOrder);
		return productOrder;
	}

	@Override
	public List<ProductOrder> findProductOrders() {
		logger.info("In findProductOrders");
		Connection conn = null;
		List<ProductOrder> productOrders = new ArrayList<ProductOrder>();
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_PRODUCT_ORDERS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductOrder productOrder = new ProductOrder();
				productOrder.setProductOrderId(rs.getInt("product_order_id"));
				productOrder.setOrderId(rs.getInt("order_id"));
				productOrder.setProductId(rs.getInt("product_id"));
				productOrder.setOrderAmount(rs.getInt("order_amount"));
				productOrders.add(productOrder);
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
		return productOrders;
	}

	@Override
	public void updateProductOrder(ProductOrder productOrder) {
		logger.info("In updateProductOrder productOrder=" + productOrder);
		Connection conn = null;	

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_PRODUCT_ORDER_BY_ID);
			ps.setInt(1, productOrder.getOrderAmount());
			ps.setInt(2, productOrder.getProductOrderId());
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

	@Override
	public void removeProductOrder(int productOrderId) {
		logger.info("In removeProductOrder productOrderId=" + productOrderId);
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PRODUCT_ORDER_BY_ID);
			ps.setInt(1, productOrderId);
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
