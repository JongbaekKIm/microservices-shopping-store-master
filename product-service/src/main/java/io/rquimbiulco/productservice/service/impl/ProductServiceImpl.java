/**
 * 
 */
package io.rquimbiulco.productservice.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.rquimbiulco.productservice.entity.Category;
import io.rquimbiulco.productservice.entity.Product;
import io.rquimbiulco.productservice.repository.ProductRepository;
import io.rquimbiulco.productservice.service.ProductService;

/**
 * @author Richard
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> listAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product createProduct(Product product) {
		product.setStatus("CREATED");
		product.setCreateAt(new Date());
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		Product productDb = getProduct(product.getId());
		if (productDb == null) {
			return null;
		}
		productDb.setName(product.getName());
		productDb.setDescription(product.getDescription());
		productDb.setPrice(product.getPrice());
		productDb.setStock(product.getStock());
		productDb.setCategory(product.getCategory());
		return productRepository.save(productDb);
	}

	@Override
	public Product deleteProduct(Long id) {
		Product productDb = getProduct(id);
		if (productDb == null) {
			return null;
		}
		productDb.setStatus("DELETED");
		return productRepository.save(productDb);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Double quantity) {
		Product productDb = getProduct(id);
		if (productDb == null) {
			return null;
		}
		Double stock = productDb.getStock() + quantity;
		productDb.setStock(stock);
		return productRepository.save(productDb);
	}

}
