/**
 * 
 */
package io.rquimbiulco.productservice.repository;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import io.rquimbiulco.productservice.entity.Category;
import io.rquimbiulco.productservice.entity.Product;

/**
 * @author Richard
 *
 */
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void whenFindByCategory() {
		Product product01 = Product.builder().name("smartphone").category(Category.builder().id(1L).build())
				.description("").stock(Double.parseDouble("100")).price(Double.parseDouble("250.50")).status("created")
				.createAt(new Date()).build();
		productRepository.save(product01);

		List<Product> foundProducts = productRepository.findByCategory(product01.getCategory());

		Assertions.assertThat(foundProducts.size()).isEqualTo(3);

	}

}
