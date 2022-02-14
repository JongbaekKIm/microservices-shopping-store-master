/**
 * 
 */
package io.rquimbiulco.shoppingservice.client.interceptor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Richard
 *
 */
@Slf4j
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

	private static final String AUTHENTICATION_HEADER = "authorization";
	private static final String BEARER_TOKEN_TYPE = "Bearer";

	private void propagateAuthorizationHeader(RequestTemplate template) {
		try {
			if (template.headers().containsKey(AUTHENTICATION_HEADER)) {
				log.trace("the authorization {} token has been already set", AUTHENTICATION_HEADER);
			} else {
				log.trace("setting the authorization token {}", AUTHENTICATION_HEADER);
				JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext()
						.getAuthentication();
				template.header(AUTHENTICATION_HEADER,
						String.format("%s %s", BEARER_TOKEN_TYPE, authentication.getToken().getTokenValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void apply(RequestTemplate template) {
		propagateAuthorizationHeader(template);
	}

}
