/**
 * 
 */
package io.rquimbiulco.shoppingservice.service;

import java.util.List;

import io.rquimbiulco.shoppingservice.entity.Invoice;

/**
 * @author Richard
 *
 */
public interface InvoiceService {

	public List<Invoice> findInvoiceAll();

	public Invoice createInvoice(Invoice invoice);

	public Invoice updateInvoice(Invoice invoice);

	public Invoice deleteInvoice(Invoice invoice);

	public Invoice getInvoice(Long id);

}
