package com.uciext.springfw.hw.frontend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uciext.springfw.hw.catalogs.model.Catalog;
import com.uciext.springfw.hw.catalogs.model.Items;
import com.uciext.springfw.hw.catalogs.service.CatalogService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
		
	private CatalogService catalogService;
	
	@Inject
	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	// VIEW LIST OF CATALOG

	@RequestMapping("/list")
	public ModelAndView catalogList(Model model) {
		System.out.println("======= in catalogList");
		List<Catalog> catalogList = catalogService.getCatalogs();
		model.addAttribute("catalogList", catalogList);
		model.addAttribute("selectedCatalogs", new Items());
		return new ModelAndView("catalog/catalogList3");
	}

	// VIEW CATALOG DETAILS

	@RequestMapping("/view")
	public ModelAndView catalogView(@RequestParam("catalogId") int catalogId, Model model) {
		System.out.println("======= in catalogView");
		Catalog catalog = catalogService.getCatalog(catalogId);
		model.addAttribute("catalog", catalog);
		return new ModelAndView("catalog/viewCatalog");
	}

	// UPDATE CATALOG

	@RequestMapping(value="/edit/{catalogId}", method=RequestMethod.GET)
	public String editCatalog(@PathVariable int catalogId, Model model) {
		System.out.println("======= in editCatalog");
		Catalog catalog = catalogService.getCatalog(catalogId);
		model.addAttribute("catalog", catalog);
		return "catalog/editCatalog";
	}
	
	@RequestMapping(value="/edit/{catalogId}", method=RequestMethod.POST)
	public String editCatalogSave(@PathVariable int catalogId, 
			@Valid Catalog catalog, BindingResult bindingResult) {
		System.out.println("======= in editCatalogSave");
		if(bindingResult.hasErrors()) {
			return "catalog/editCatalog";
		}
		catalogService.updateCatalog(catalog);
		//catalogService.updateCatalog(catalogId, catalog);
		return "redirect:/catalog/list.html";
	}

	// ADD CATALOG
	
	// Before showing Add Catalog Form
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addCatalog(Model model) {
		System.out.println("======= in addCatalog");
		model.addAttribute(new Catalog());
		return "catalog/addCatalog";
	}

	// After submitting Add Catalog Form
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addCatalogSave(@Valid Catalog catalog, BindingResult bindingResult) {
		System.out.println("======= in addCatalogSave");
		if(bindingResult.hasErrors()){
			return "catalog/addCatalog";
		}
		catalogService.insertCatalog(catalog);
		return "redirect:/catalog/list.html";
	}

	// DELETE CATALOG

	// After submitting Delete from Catalog List Form
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String deleteCatalogs(@ModelAttribute Items selectedCatalogs) {
		System.out.println("======= in deleteCatalogs");
		for (String catalogIdStr : selectedCatalogs.getItemList()) {
			System.out.println("delete catalog id=" + catalogIdStr);
			try { 
				int catalogId = Integer.parseInt(catalogIdStr);
				catalogService.removeCatalog(catalogId);
			}
			catch (Exception e) {}
		}
		return "redirect:/catalog/list.html";
	}

}
