/**
 * 
 */
package io.rquimbiulco.productservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.rquimbiulco.productservice.entity.Category;
import io.rquimbiulco.productservice.entity.Product;
import io.rquimbiulco.productservice.service.ProductService;
import io.rquimbiulco.productservice.util.ErrorMessage;

/**
 * @author Richard
 *
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/listProduct")
	public ResponseEntity<List<Product>> listProduct(
			@RequestParam(name = "categoryId", required = false) Long categoryId) {
		List<Product> products = new ArrayList<>();
		if (categoryId == null) {
			products = productService.listAllProduct();
			if (products.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} else {
			products = productService.findByCategory(Category.builder().id(categoryId).build());
			if (products.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.ok(products);
	}

	@GetMapping(value = "/findProduct/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
		Product product = productService.getProduct(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}

	@PostMapping(value = "/createProduct")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.formatMessage(result));
		}
		Product createdProduct = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

	@PutMapping(value = "/updateProduct/{idProduct}")
	public ResponseEntity<Product> updateProduct(@PathVariable("idProduct") Long idProduct,
			@RequestBody Product product) {
		product.setId(idProduct);
		Product productDb = productService.updateProduct(product);
		if (productDb == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productDb);
	}

	@DeleteMapping(value = "/deleteProduct/{idProduct}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("idProduct") Long idProduct) {
		Product deletedProduct = productService.deleteProduct(idProduct);
		if (deletedProduct == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(deletedProduct);
	}

	@GetMapping(value = "/updateStockProduct/{idProduct}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable("idProduct") Long idProduct,
			@RequestParam(name = "quantity", required = true) Double quantity) {
		Product product = productService.updateStock(idProduct, quantity);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}

	private String formatMessage(BindingResult bindingResult) {
		List<Map<String, String>> errors = bindingResult.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;
		}).collect(Collectors.toList());
		ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
		}
		return jsonString;
	}
}
