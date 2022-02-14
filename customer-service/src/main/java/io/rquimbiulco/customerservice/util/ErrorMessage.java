/**
 * 
 */
package io.rquimbiulco.customerservice.util;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Richard
 *
 */
@Getter
@Setter
@Builder
public class ErrorMessage {

	private String code;
	private List<Map<String, String>> messages;

}
