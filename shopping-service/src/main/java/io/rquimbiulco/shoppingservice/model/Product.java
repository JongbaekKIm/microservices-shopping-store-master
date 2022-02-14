/**
 * 
 */
package io.rquimbiulco.shoppingservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard
 *
 */
@Data
@NoArgsConstructor
public class Product {

	private Long id;
	private String name;
	private String description;
	private Double stock;
	private Double price;
	private String status;
	private Category category;

}
