/**
 * 
 */
package io.rquimbiulco.shoppingservice.client.fallbacks;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.rquimbiulco.shoppingservice.client.ProductClient;
import io.rquimbiulco.shoppingservice.model.Product;

/**
 * @author Richard
 *
 */
@Component
public class ProductHytrixFallbackFactory implements ProductClient {

	@Override
	public ResponseEntity<Product> getProduct(Long productId) {
		Product productoFallback = notFoundPropduct();
		return ResponseEntity.ok(productoFallback);
	}

	@Override
	public ResponseEntity<Product> updateStockProduct(Long idProduct, Double quantity) {
		Product productoFallback = notFoundPropduct();
		return ResponseEntity.ok(productoFallback);
	}

	private Product notFoundPropduct() {
		Product product = new Product();
		product.setName("not found");
		product.setDescription("not found");
		product.setStock(0.0);
		product.setPrice(0.0);
		return product;
	}

}
