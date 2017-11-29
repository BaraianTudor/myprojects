package ro.fortech.demoapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import ro.fortech.demoapp.service.SiteUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	SiteUserService siteUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/","/about","/register","/loginerror","/h2-console/*")
				.permitAll()
			.antMatchers("/js/*","/css/*","/img/*","/fonts/*")
				.permitAll()
			.antMatchers("/admin")
				.hasAnyAuthority("ROLE_ADMIN")
			.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.failureUrl("/loginerror")
				.permitAll();
	http.csrf().disable();
	http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(siteUserService).passwordEncoder(passwordEncoder);
	}

}
