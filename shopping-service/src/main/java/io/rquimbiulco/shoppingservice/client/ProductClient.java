/**
 * 
 */
package io.rquimbiulco.shoppingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.rquimbiulco.shoppingservice.client.fallbacks.ProductHytrixFallbackFactory;
import io.rquimbiulco.shoppingservice.model.Product;

/**
 * @author Richard
 *
 */
@FeignClient(name = "product-service", fallback = ProductHytrixFallbackFactory.class)
public interface ProductClient {

	@GetMapping(value = "/product/findProduct/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId);

	@GetMapping(value = "/product/updateStockProduct/{idProduct}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable("idProduct") Long idProduct,
			@RequestParam(name = "quantity", required = true) Double quantity);

}
