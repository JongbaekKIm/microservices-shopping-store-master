/**
 * 
 */
package io.rquimbiulco.shoppingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.rquimbiulco.shoppingservice.entity.InvoiceItem;

/**
 * @author Richard
 *
 */
public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {

}
