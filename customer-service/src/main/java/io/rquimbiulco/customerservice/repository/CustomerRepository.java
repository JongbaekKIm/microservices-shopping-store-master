/**
 * 
 */
package io.rquimbiulco.customerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.rquimbiulco.customerservice.entity.Customer;
import io.rquimbiulco.customerservice.entity.Region;

/**
 * @author Richard
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByNumberID(String numberID);

	public List<Customer> findByLastName(String lastName);

	public List<Customer> findByRegion(Region region);

}
