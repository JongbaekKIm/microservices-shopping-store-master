/**
 * 
 */
package io.rquimbiulco.shoppingservice.client.fallbacks;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.rquimbiulco.shoppingservice.client.CustomerClient;
import io.rquimbiulco.shoppingservice.model.Customer;

/**
 * @author Richard
 *
 */
@Component
public class CustomerHystrixFallbackFactory implements CustomerClient {

	@Override
	public ResponseEntity<Customer> getCustomer(long id) {
		Customer customer = new Customer();
		customer.setFirstName("none");
		customer.setLastName("none");
		customer.setEmail("none");
		customer.setPhotoUrl("none");
		return ResponseEntity.ok(customer);
	}

}
