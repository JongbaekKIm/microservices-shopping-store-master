/**
 * 
 */
package io.rquimbiulco.shoppingservice.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Richard
 *
 */
@Data
public class Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;

}
