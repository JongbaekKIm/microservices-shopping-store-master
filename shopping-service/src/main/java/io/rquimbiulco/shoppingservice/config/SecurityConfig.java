/**
 * 
 */
package io.rquimbiulco.shoppingservice.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import feign.RequestInterceptor;
import io.rquimbiulco.shoppingservice.client.interceptor.OAuth2FeignRequestInterceptor;

/**
 * @author Richard
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Validate tokens through configured OpenID Provider
		http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
		// Require authentication for all requests
		http.authorizeRequests().anyRequest().authenticated();
		// Allow showing pages within a frame
		http.headers().frameOptions().sameOrigin();
	}

	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		// Convert realm_access.roles claims to granted authorities, for use in access
		// decisions
		converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
		return converter;
	}

	@Bean
	public JwtDecoder jwtDecoderByIssuerUri(OAuth2ResourceServerProperties properties) {
		String issuerUri = properties.getJwt().getIssuerUri();
		NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuerUri);
		// Use preferred_username from claims as authentication name, instead of UUID
		// subject
		jwtDecoder.setClaimSetConverter(new UsernameSubClaimAdapter());
		return jwtDecoder;
	}
	
	@Bean
    public RequestInterceptor securityFeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor();
    }

}
