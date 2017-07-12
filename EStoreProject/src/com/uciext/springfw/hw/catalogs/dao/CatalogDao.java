package com.uciext.springfw.hw.catalogs.dao;

import java.util.List;

import com.uciext.springfw.hw.catalogs.model.Catalog;

public interface CatalogDao {

	public void insertCatalog(Catalog catalog);
	public Catalog findCatalogById(int catalogId);
	public List<Catalog> findCatalogs();
	public void updateCatalog(Catalog catalog);
	public void removeCatalog(int catalogId);
//	public List<Catalog> findSchedulesByCourse(Course course);

}
