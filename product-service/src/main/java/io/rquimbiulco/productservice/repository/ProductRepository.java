/**
 * 
 */
package io.rquimbiulco.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.rquimbiulco.productservice.entity.Category;
import io.rquimbiulco.productservice.entity.Product;

/**
 * @author Richard
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByCategory(Category category);

}
