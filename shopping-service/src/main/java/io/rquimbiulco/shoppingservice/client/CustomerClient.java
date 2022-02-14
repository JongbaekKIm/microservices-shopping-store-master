/**
 * 
 */
package io.rquimbiulco.shoppingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.rquimbiulco.shoppingservice.client.fallbacks.CustomerHystrixFallbackFactory;
import io.rquimbiulco.shoppingservice.model.Customer;

/**
 * @author Richard
 *
 */
@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {

	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);

}
