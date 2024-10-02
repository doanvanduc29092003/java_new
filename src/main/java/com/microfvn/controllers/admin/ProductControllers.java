package com.microfvn.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.microfvn.models.Category;
import com.microfvn.models.Product;
import com.microfvn.servicer.CategoryServicer;
import com.microfvn.servicer.ProductServicer;
import com.microfvn.servicer.StorageService;

@Controller
@RequestMapping("/admin")
public class ProductControllers {

	@Autowired
	
	private CategoryServicer categoryServicer;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private ProductServicer productServicer;
	
	@RequestMapping("/product")
	public String index(Model model ,@Param("keyword") String keyword,@RequestParam(name = "pageNo", defaultValue  = "1") Integer pageNo) {
		
		Page<Product> listCategory = this.productServicer.getAll(pageNo);
		
		if(keyword != null) {
			listCategory = this.productServicer.searchProduct(keyword,pageNo);
			model.addAttribute("keyword",keyword);
		}
		model.addAttribute("totalPage",listCategory.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("listCategory", listCategory);
        return "admin/product/index";
	}
	
	@RequestMapping("/product-add")
	public String add(Model model) {
		
		Product product = new Product();
		model.addAttribute("product",product);
		
		List<Category> lisCate = this.categoryServicer.getAll();
		model.addAttribute("listCate",lisCate);
		return "admin/product/add";
	}
	
	@PostMapping("/product-add")
	
	public String save(@ModelAttribute("product") Product product,@RequestParam("fileImage") MultipartFile file) {
		
		//upload file
		this.storageService.store(file);
		String fileName = file.getOriginalFilename();
		product.setImage(fileName);
		if(this.productServicer.create(product)) {
			return "redirect:/admin/product";
		}
		return "admin/product/add";
	}
	
	@GetMapping("/edit-product/{id}")
    public String edit(Model model,@PathVariable("id") Integer id) {
    	
    	Product product = this.productServicer.findById(id);
    	model.addAttribute("product",product);
    	List<Category> lisCate = this.categoryServicer.getAll();
		model.addAttribute("listCate",lisCate);
    	return "admin/product/edit";
    }
	
	@PostMapping("/product-edit")
	
	public String update(@ModelAttribute("product") Product product,@RequestParam("fileImage") MultipartFile file) {
		
		//upload file
		String fileName = file.getOriginalFilename();
		boolean isEmpty = file == null || fileName.trim().length() ==0;
		if(!isEmpty) {
			this.storageService.store(file);
			product.setImage(fileName);
		}
		if(this.productServicer.update(product)) {
			return "redirect:/admin/product";
		}
		return "admin/product/edit";
	}
	
	@GetMapping("/delete-product/{id}")
    public String delete(@PathVariable("id") Integer id) {
    	if (this.productServicer.delete(id)) {
			return "redirect:/admin/product";
		} else {
			return "redirect:/admin/product";
		}
    }
}
