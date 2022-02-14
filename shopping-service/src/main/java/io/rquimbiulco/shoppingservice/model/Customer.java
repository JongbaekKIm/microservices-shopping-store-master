/**
 * 
 */
package io.rquimbiulco.shoppingservice.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard
 *
 */
@Data
@NoArgsConstructor
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String numberID;

	private String firstName;

	private String lastName;

	private String email;

	private String photoUrl;

	private Region region;

	private String state;

}
