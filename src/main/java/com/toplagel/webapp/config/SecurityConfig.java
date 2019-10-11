package com.toplagel.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.toplagel.webapp.service.CompanyService;
import com.toplagel.webapp.service.CustomerService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
        
	@Autowired
	private CompanyService companyService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .antMatcher("/company/**")
                .authorizeRequests()
                    .antMatchers(
                    		"/index",
                            "/company/company-register*",
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/webjars/**").permitAll()
                    .anyRequest().authenticated()
                //.and().authorizeRequests().antMatchers("/index").permitAll().anyRequest().anonymous()
                .and()
                    .formLogin()
                        .loginPage("/company/company-login")
                        .loginProcessingUrl("/company/company-login")
                            .permitAll()
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/company/logout"))
                        .logoutSuccessUrl("/company/company-login?logout")
                .permitAll();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProviderForCompany(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(companyService);
        auth.setPasswordEncoder(bCryptPasswordEncoder());
        return auth;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProviderForCompany());
    }
    
    @EnableWebSecurity
    @Configuration
    @Order(2)
    public static class SecurityConfig2 extends WebSecurityConfigurerAdapter{

    	@Autowired
    	private CustomerService customerService;
    	
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            .antMatcher("/customer/**")
                    .authorizeRequests()
                        .antMatchers(
                        		"/index",
                                "/customer/customer-register*",
                                "/js/**",
                                "/css/**",
                                "/img/**",
                                "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                    //.and().authorizeRequests().antMatchers("/index").permitAll().anyRequest().anonymous()
                    .and()
                        .formLogin()
                            .loginPage("/customer/customer-login")
                            .loginProcessingUrl("/customer/customer-login")
                                .permitAll()
                    .and()
                        .logout()
                            .invalidateHttpSession(true)
                            .clearAuthentication(true)
                            .logoutRequestMatcher(new AntPathRequestMatcher("/customer/logout"))
                            .logoutSuccessUrl("/customer/customer-login?logout")
                    .permitAll();
        }
        
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoderForCustomer() {
            return new BCryptPasswordEncoder();
        }
        
        @Bean
        public DaoAuthenticationProvider authenticationProviderForCustomer(){
            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
            auth.setUserDetailsService(customerService);
            auth.setPasswordEncoder(bCryptPasswordEncoderForCustomer());
            return auth;
        }
        
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProviderForCustomer());
        }
    }
}
