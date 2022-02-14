/**
 * 
 */
package io.rquimbiulco.productservice.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import io.rquimbiulco.productservice.entity.Category;
import io.rquimbiulco.productservice.entity.Product;
import io.rquimbiulco.productservice.repository.ProductRepository;
import io.rquimbiulco.productservice.service.impl.ProductServiceImpl;

/**
 * @author Richard
 *
 */
@SpringBootTest
public class ProductServiceTest {

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@TestConfiguration
	static class ProductServiceTestContextConfiguration {

		@Bean
		public ProductService employeeService() {
			return new ProductServiceImpl();
		}

	}

	@BeforeEach
	public void setup() {
		Product testProduct = Product.builder().id(1L).name("computer").category(Category.builder().id(1L).build())
				.price(Double.parseDouble("13.5")).stock(Double.parseDouble("5")).build();
		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
		Mockito.when(productRepository.save(testProduct)).thenReturn(testProduct);
	}

	@Test
	public void getProduct() {
		Product found = productService.getProduct(1L);
		Assertions.assertThat(found.getName()).isEqualTo("computer");
	}

	@Test
	public void testUpdateStock() {
		Product newStock = productService.updateStock(1L, Double.parseDouble("18"));
		Assertions.assertThat(newStock.getStock()).isEqualTo(Double.parseDouble("23"));
	}

}
