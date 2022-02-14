/**
 * 
 */
package io.rquimbiulco.shoppingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.rquimbiulco.shoppingservice.entity.Invoice;

/**
 * @author Richard
 *
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	public List<Invoice> findByCustomerId(Long customerId);

	public Invoice findByNumberInvoice(String numberInvoice);

}
