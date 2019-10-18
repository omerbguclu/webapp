package com.toplagel.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Product;
import com.toplagel.webapp.service.CompanyService;
import com.toplagel.webapp.service.ProductService;

@Controller
@RequestMapping("/company")
public class CompanyProductController extends ControllerCommon {

	@Autowired
	private ProductService productService;

	@Autowired
	private CompanyService companyService;

	@GetMapping("/add-product")
	public String addProductPage(Model model) {
		model.addAttribute("product", new Product());
		return "company/add-product";
	}

	@RequestMapping("/add-product/save")
	public String addProduct(Product product) {
		Company company = companyService.findByEmail(getActiveLoggedUserEmail());
		if (company.getProducts().contains(product)) {
			System.out.println("already added");
		} else {
			companyService.addProduct(company, product);
		}
		return "company/add-product";
	}

	@RequestMapping("/delete-product/{id}")
	public String deleteThisProduct(@PathVariable(name = "id") Long id) {
		Product product = productService.findById(id);
		Company company = companyService.findByEmail(getActiveLoggedUserEmail());
		if (company.getProducts().contains(product)) {
			companyService.deleteProduct(company, product);
		} else {
			System.out.println("There is no product with this name");
		}
		return "redirect:/company";
	}

	@RequestMapping("update-product/{id}")
	public String updateThisProduct(@PathVariable(name = "id") Long id, Product product) {
		product.setId(id);
		productService.save(product);
		return "redirect:/company";

	}

}
