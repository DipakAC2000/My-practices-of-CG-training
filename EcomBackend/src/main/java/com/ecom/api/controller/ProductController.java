package com.ecom.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.api.dto.ProductDto;
import com.ecom.api.model.Category;
import com.ecom.api.model.Product;
import com.ecom.api.model.Review;
import com.ecom.api.repository.CategoryRepository;
import com.ecom.api.repository.ProductRepository;
import com.ecom.api.repository.ReviewRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ProductController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@PostMapping("/product/{cid}")
	public Product postProduct(@PathVariable("cid") long cid, @RequestBody Product product) {
		Category category = categoryRepository.getById(cid);
		product.setCategory(category);
		return productRepository.save(product);
	}
	
	@GetMapping("/product/{cid}")
	public List<ProductDto> getAllProduct(@PathVariable("cid") long cid) {
		List<ProductDto> dtoList = new ArrayList<>();
		
		List<Product> listProduct = productRepository.findByCategoryId(cid);
		
		for(Product p : listProduct) {
			ProductDto dto = new ProductDto();
			
			dto.setId(p.getId());
			dto.setTitle(p.getTitle());
			dto.setShortDescription(p.getShortDescription());
			dto.setPrice(p.getPrice());
			dto.setCategoryId(p.getCategory().getId());
			dto.setCategoryName(p.getCategory().getName());
			dto.setReviewCount(reviewRepository.getReviewCount(p.getId()));
			
			dtoList.add(dto);
		}
		
		return dtoList;
		
	}
	
	@GetMapping("/product/review/{pid}")
	public Long getReviewCount(@PathVariable("pid") long pid) {
		return reviewRepository.getReviewCount(pid);
	}
	
	@PostMapping("/review/{pid}")
	public Review postReview(@PathVariable("pid") long pid, @RequestBody Review review) {
		Product product = productRepository.getById(pid);
		review.setProduct(product);
		return reviewRepository.save(review);
	}
	
	@GetMapping("/review/{pid}")
	public List<Review> getAllReview(@PathVariable("pid") long pid) {
		return reviewRepository.findByProductId(pid);
	}
	
	@DeleteMapping("/review/{rid}")
	public void deleteReview(@PathVariable("rid") long rid) {
		reviewRepository.deleteById(rid);
	}
	
	
	
}
