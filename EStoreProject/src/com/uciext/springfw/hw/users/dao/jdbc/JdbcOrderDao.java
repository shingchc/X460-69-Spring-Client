package com.uciext.springfw.hw.users.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.uciext.springfw.hw.catalogs.model.Catalog;
import com.uciext.springfw.hw.common.Util;
import com.uciext.springfw.hw.users.dao.OrderDao;
import com.uciext.springfw.hw.users.model.Order;
import com.uciext.springfw.hw.users.service.OrderService;

public class JdbcOrderDao implements OrderDao{

	protected static Logger logger = Logger.getLogger(JdbcOrderDao.class);

	// SQL statements
	private static final String SQL_INSERT_ORDER =
			"INSERT INTO orders (order_id, order_created, total_amount, confirm_number, user)"
			+ " VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_FIND_ORDERS = 
			"SELECT * FROM orders";
	private static final String SQL_FIND_ORDER_BY_ID = 
			"SELECT * FROM orders WHERE order_id = ?";
	private static final String SQL_UPDATE_ORDER_BY_ID =
			"UPDATE orders SET order_created = ?, total_amount = ?, confirm_number = ?, user = ? WHERE order_id = ?";
	private static final String SQL_DELETE_ORDER_BY_ID =
			"DELETE from orders where order_id = ?";

	

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
	public void insertOrder(Order order) {
		logger.info("In insertOrder order=" + order);
		System.out.println("In insertOrder order=" + order);
		Connection conn = null;
 
		try {
			order.setOrderId(Util.getRandomInt());
			order.setOrderCreated(Util.getTimestamp());
			order.setConfirmNumber(Util.getRandomInt());
			
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT_ORDER);
			ps.setInt(1, order.getOrderId());					
			ps.setTimestamp(2, order.getOrderCreated());
			ps.setInt(3, order.getTotalAmount());
			ps.setInt(4, order.getConfirmNumber());
			ps.setString(5, order.getUser());
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
	public Order findOrderById(int orderId) {
		logger.info("In findOrderById orderId=" + orderId);
		Connection conn = null;
		Order order = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_ORDER_BY_ID);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setOrderCreated(rs.getTimestamp("order_created"));
				order.setTotalAmount(rs.getInt("total_amount"));
				order.setConfirmNumber(rs.getInt("confirm_number"));
				order.setUser(rs.getString("user"));
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
		logger.info("exit findOrderById order=" + order);
		return order;
	}

	@Override
	public List<Order> findOrders() {
		logger.info("In findOrders");
		Connection conn = null;
		List<Order> orders = new ArrayList<Order>();
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_ORDERS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setOrderCreated(rs.getTimestamp("order_created"));
				order.setTotalAmount(rs.getInt("total_amount"));
				order.setConfirmNumber(rs.getInt("confirm_number"));
				order.setUser(rs.getString("user"));
				orders.add(order);
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
		logger.info("exit findOrders");
		return orders;
	}

	@Override
	public void updateOrder(Order order) {
		logger.info("In updateOrder");
		System.out.println("In updateOrder");
		Connection conn = null;	

		try {
			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_ORDER_BY_ID);
			ps.setTimestamp(1, order.getOrderCreated());
			ps.setInt(2, order.getTotalAmount());
			ps.setInt(3, order.getConfirmNumber());
			ps.setString(4, order.getUser());
			ps.setInt(5, order.getOrderId());					
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
	public void removeOrder(int orderId) {
		logger.info("In removeOrder");
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_DELETE_ORDER_BY_ID);
			ps.setInt(1, orderId);
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
