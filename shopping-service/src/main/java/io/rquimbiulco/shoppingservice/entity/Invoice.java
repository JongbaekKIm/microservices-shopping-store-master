/**
 * 
 */
package io.rquimbiulco.shoppingservice.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.rquimbiulco.shoppingservice.model.Customer;
import lombok.Data;

/**
 * @author Richard
 *
 */
@Data
@Entity
@Table(name = "tlb_invoices")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "number_invoice")
	private String numberInvoice;

	private String description;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@Valid
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id")
	private List<InvoiceItem> items;

	private String state;

	@Transient
	private Customer customer;

	public Invoice() {
		items = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
}
