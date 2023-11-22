package com.example.demo;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig{
	AuthenticationManager auth;
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration conf) throws Exception{
		auth=conf.getAuthenticationManager();
		return auth;
	}
	
	@Bean
	public InMemoryUserDetailsManager userdetails()
	{
		System.out.println(" InMemoryUserDetailsManager 1 ----> ");
		List<UserDetails> users=List.of(
				User.withUsername("user1").
				password("{noop}user1").
				roles("USERS").
				build(),
				User.withUsername("user2").
				password("{noop}user2").
				roles("SELLER").
				build(),
				User.withUsername("admin").
				password("{noop}admin").
				roles("ADMIN").
				build()
		);
		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		System.out.println(" filterChain 1 ----> "+http);
		http.csrf(cus->cus.disable())
		.authorizeHttpRequests(aut->
			aut.requestMatchers(HttpMethod.GET,"/api/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.POST,"/api/**").hasRole("SELLER")
			.requestMatchers(HttpMethod.DELETE,"/api/**").hasAnyRole("ADMIN","SELLER")
			.requestMatchers("/api").authenticated()
			.anyRequest().permitAll()
			)
		.addFilter(new JWTAuthorizationFilter(auth));
		return http.build();		
	}
}







