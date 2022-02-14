/**
 * 
 */
package io.rquimbiulco.productservice.service;

import java.util.List;

import io.rquimbiulco.productservice.entity.Category;
import io.rquimbiulco.productservice.entity.Product;

/**
 * @author Richard
 *
 */
public interface ProductService {

	public List<Product> listAllProduct();

	public Product getProduct(Long id);

	public Product createProduct(Product product);

	public Product updateProduct(Product product);

	public Product deleteProduct(Long id);

	public List<Product> findByCategory(Category category);

	public Product updateStock(Long id, Double quantity);

}
