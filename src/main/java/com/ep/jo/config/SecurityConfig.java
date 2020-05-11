package com.ep.jo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ep.jo.service.AdminService;

import lombok.RequiredArgsConstructor;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final AdminService adminService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override // ignore check swagger resource
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
				.csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
				.authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
					.antMatchers("/user/**").hasRole("ADMIN")
					.antMatchers("/**").permitAll()
				.and()
					.formLogin()
					.usernameParameter("email")
					.loginPage("/admin/login")
					.defaultSuccessUrl("/admin/login/success")
					.failureUrl("/admin/login/fail")
					.permitAll()
				.and()
                	.logout()
                	.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                	.invalidateHttpSession(true);
	}

	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService).passwordEncoder(passwordEncoder());
    }
}
