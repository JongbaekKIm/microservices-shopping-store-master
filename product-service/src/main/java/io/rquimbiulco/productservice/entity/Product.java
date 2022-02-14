/**
 * 
 */
package io.rquimbiulco.productservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El nombre del producto no debe ser vacio")
	private String name;
	private String description;

	@Positive(message = "El stock debe ser mayor que 0")
	private Double stock;
	private Double price;
	private String status;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@NotNull(message = "la categoria no puede ser null")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer" })
	private Category category;
}
