/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bookstorebackend.Config;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import bookstorebackend.Service.UserService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


/**
 * @author Joe Grandja
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

    @Bean
    UserDetailsService realUserDetailsService(){ //注册UserDetailsService 的bean
        return new RealUserDetailsService();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(realUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder()); //user Details Service verification
    }

    // @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
                    .antMatchers("/api/books/delete,/api/books/save").hasRole("Administrator")
					.antMatchers("/api/books,/api/cart/**,/api/orders/**").hasRole("User")
				.and()
				.formLogin().loginPage("/Login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

                        HttpSession session=httpServletRequest.getSession(true);//这就是session的创建
                        session.setAttribute("userid",JSONObject.fromObject(userService.findByUsername(authentication.getName())).get("userid"));//给session添加属性属性name： username,属性 value：TOM

                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        String username = authentication.getName();

                        List<String> authorities = new ArrayList<>();
                        for (GrantedAuthority authority :authentication.getAuthorities()) {
                            authorities.add(authority.getAuthority());
                        };
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"status\":\"ok\",\"msg\":\"login success\",\"user\":"+ JSONObject.fromObject(userService.findByUsername(username)).toString()+
                                ",\"role\":"+ JSONArray.fromObject(authorities).toString()+"}");
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        System.out.println("username:"+httpServletRequest.getParameter("username"));
                        System.out.println("password:"+httpServletRequest.getParameter("password"));
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"status\":\"error\",\"msg\":\"login fail\"}");
                        out.flush();
                        out.close();
                    }
                })
                .loginProcessingUrl("/api/login")
                .usernameParameter("username").passwordParameter("password").permitAll()
                .and()
                .logout().logoutUrl("/api/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        String username = authentication.getName();
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"status\":\"ok\",\"username\":\"" + username + "\",\"msg\":\"logout success\"}");
                        out.flush();
                        out.close();
                    }
                })
                .deleteCookies("JSESSIONID")
                .deleteCookies("login")
                .deleteCookies("role")
                .deleteCookies("username")
                .permitAll()

                .and().csrf().disable().cors()
                .and()
                .httpBasic();
	}
	// @formatter:on
	// @formatter:off
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("Administrator"));
	}
	// @formatter:on
}