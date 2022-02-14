/**
 * 
 */
package io.rquimbiulco.shoppingservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.rquimbiulco.shoppingservice.entity.Invoice;
import io.rquimbiulco.shoppingservice.service.InvoiceService;
import io.rquimbiulco.shoppingservice.util.ErrorMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Richard
 *
 */
@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	// -------------------Retrieve All
	// Invoices--------------------------------------------
	@GetMapping
	public ResponseEntity<List<Invoice>> listAllInvoices() {
		List<Invoice> invoices = invoiceService.findInvoiceAll();
		if (invoices.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(invoices);
	}

	// -------------------Retrieve Single
	// Invoice------------------------------------------
	@GetMapping(value = "/{id}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id, @RequestHeader Map<String, String> headers) {
		log.info("Fetching Invoice with id {}", id);
		Invoice invoice = invoiceService.getInvoice(id);
		if (null == invoice) {
			log.error("Invoice with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(invoice);
	}

	// -------------------Create a
	// Invoice-------------------------------------------
	@PostMapping
	public ResponseEntity<Object> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result) {
		log.info("Creating Invoice : {}", invoice);
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.formatMessage(result));
		}
		Invoice invoiceDB = invoiceService.createInvoice(invoice);

		return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDB);
	}

	// ------------------- Update a Invoice
	// ------------------------------------------------
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
		log.info("Updating Invoice with id {}", id);

		invoice.setId(id);
		Invoice currentInvoice = invoiceService.updateInvoice(invoice);

		if (currentInvoice == null) {
			log.error("Unable to update. Invoice with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(currentInvoice);
	}

	// ------------------- Delete a Invoice-----------------------------------------
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
		log.info("Fetching & Deleting Invoice with id {}", id);

		Invoice invoice = invoiceService.getInvoice(id);
		if (invoice == null) {
			log.error("Unable to delete. Invoice with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		invoice = invoiceService.deleteInvoice(invoice);
		return ResponseEntity.ok(invoice);
	}

	private String formatMessage(BindingResult result) {
		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;

		}).collect(Collectors.toList());
		ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			log.error("Error JSON parse {}", e.getMessage());
		}
		return jsonString;
	}
}
