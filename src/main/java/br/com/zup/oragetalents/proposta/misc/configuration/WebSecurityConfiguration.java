package br.com.zup.oragetalents.proposta.misc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_propostas:read")
        .antMatchers(HttpMethod.POST, "/proposta/**").hasAuthority("SCOPE_propostas:write")
        .antMatchers(HttpMethod.GET, "/cartoes/**").hasAuthority("SCOPE_cartoes:read")
        .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_cartoes:write")
        .antMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_cartoes:write")
        .antMatchers("/actuator/**").permitAll()
        .anyRequest().authenticated().and()
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}
}
