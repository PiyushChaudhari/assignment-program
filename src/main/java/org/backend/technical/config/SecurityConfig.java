package org.backend.technical.config;

import java.util.Arrays;
import java.util.List;

import org.backend.technical.handler.LoginFailHandler;
import org.backend.technical.handler.LoginSuccessHandler;
import org.backend.technical.voter.MinuteBasedVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	@Qualifier("secureUserDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private LoginFailHandler loginFailHandler;
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays.asList(new WebExpressionVoter(),new RoleVoter(), new AuthenticatedVoter(), new MinuteBasedVoter());
		return new UnanimousBased(decisionVoters);
	}
	
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/auth/login", "/auth/signIn")
				.permitAll()
				.anyRequest()
				.authenticated()
				.accessDecisionManager(accessDecisionManager())
				.and()
					.formLogin()
						.loginPage("/auth/login")
						.loginProcessingUrl("/auth/signIn")
						.failureUrl("/auth/login")
						.failureHandler(loginFailHandler)
						.successHandler(loginSuccessHandler)
						.usernameParameter("email").passwordParameter("password").and()
						.exceptionHandling().accessDeniedPage("/auth/unauthorized")
				.and()
					.logout()
						.logoutSuccessUrl("/")
						.deleteCookies("JSESSIONID", "SESSION")
						.invalidateHttpSession(true)
				.and()
			    	.csrf().disable()
						;

	}
}
