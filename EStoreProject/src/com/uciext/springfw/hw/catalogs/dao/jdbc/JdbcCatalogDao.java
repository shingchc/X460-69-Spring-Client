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

import com.uciext.springfw.hw.catalogs.dao.CatalogDao;
import com.uciext.springfw.hw.catalogs.model.Catalog;
import com.uciext.springfw.hw.catalogs.service.CatalogService;
import com.uciext.springfw.hw.common.Util;

public class JdbcCatalogDao implements CatalogDao {

	protected static Logger logger = Logger.getLogger(JdbcCatalogDao.class.getName());

	// SQL statements
	private static final String SQL_INSERT_CATALOG =
			"INSERT INTO catalogs (catalog_id, catalog_name)"
			+ " VALUES (?, ?)";
	private static final String SQL_FIND_CATALOGS = 
			"SELECT * FROM catalogs";
	private static final String SQL_FIND_CATALOG_BY_ID = 
			"SELECT * FROM catalogs WHERE catalog_id = ?";
	private static final String SQL_UPDATE_CATALOG_BY_ID =
			"UPDATE catalogs SET catalog_name = ? WHERE catalog_id = ?";
	private static final String SQL_DELETE_CATALOG_BY_ID =
			"DELETE from catalogs where catalog_id = ?";
	/*
 	// Optimization
	private static final String SQL_FIND_SCHEDULES_BY_COURSE_2 = 
			"SELECT * FROM schedules s, courses c, departments d "
			+ " WHERE s.course_id = ?"
			+ " AND s.course_id = c.course_id"
			+ " AND c.department_id = d.department_id"
			;
*/	
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
	public void insertCatalog(Catalog catalog) {
		logger.info("In insertCatalog catalog=" + catalog);
		Connection conn = null;
 
		try {
			catalog.setCatalogId(Util.getRandomInt());

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT_CATALOG);
			ps.setInt(1, catalog.getCatalogId());
			ps.setString(2, catalog.getCatalogName());
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
	public List<Catalog> findCatalogs() {
		logger.info("In findCatalogs");
		Connection conn = null;
		List<Catalog> catlogs = new ArrayList<Catalog>();
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_CATALOGS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Catalog catalog = new Catalog();
				catalog.setCatalogId(rs.getInt("catalog_id"));
				catalog.setCatalogName(rs.getString("catalog_name"));
				catlogs.add(catalog);
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
		logger.info("exit findCatalogs");
		return catlogs;
	}

	@Override
	public Catalog findCatalogById(int catalogId) {
		logger.info("In findCatalogById catalogId=" + catalogId);
		Connection conn = null;
		Catalog catalog = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_CATALOG_BY_ID);
			ps.setInt(1, catalogId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				catalog = new Catalog();
				catalog.setCatalogId(rs.getInt("catalog_id"));
				catalog.setCatalogName(rs.getString("catalog_name"));
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
		logger.info("exit findCatalogById catalog=" + catalog);
		return catalog;
	}

	public void updateCatalog(Catalog catalog)	{
		logger.info("In updateCatalog");
		Connection conn = null;	

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_CATALOG_BY_ID);
			ps.setString(1, catalog.getCatalogName());
			ps.setInt(2, catalog.getCatalogId());
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
	
	public void removeCatalog(int catalogId)	{
		logger.info("In removeCatalog");
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_DELETE_CATALOG_BY_ID);
			ps.setInt(1, catalogId);
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
