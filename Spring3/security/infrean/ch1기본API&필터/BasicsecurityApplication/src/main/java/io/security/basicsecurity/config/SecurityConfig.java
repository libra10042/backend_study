package io.security.basicsecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService userDetailsService;
//    6. 세션 제어 필터 SessionManagementFilter, ConcurrentSessionFilter
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("{noop}1111").roles("USER");
//        auth.inMemoryAuthentication().withUser("sys").password("{noop}1111").roles("SYS", "USER");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}1111").roles("ADMIN", "SYS", "USER");
//
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .anyRequest().permitAll();
        http
                .formLogin();

        http
                .csrf().disable();





//        >7. 예외 처리 및 요청 캐시 필터  ExceptionTranslationFilter, RequestCacheAwareFilter
//        http
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/user").hasRole("USER")
//                .antMatchers("/admin/pay").hasRole("ADMIN")
//                .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
//                .anyRequest().authenticated();
//        http
//                .formLogin()
//                .successHandler(new AuthenticationSuccessHandler() {
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        RequestCache requestCache = new HttpSessionRequestCache();
//                        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
//                        String redirectUrl = savedRequest.getRedirectUrl();
//                        httpServletResponse.sendRedirect(redirectUrl);
//                    }
//                });
//
//
//        http
//                .exceptionHandling()
////                .authenticationEntryPoint(new AuthenticationEntryPoint() {
////                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
////                        httpServletResponse.sendRedirect("/login"); // 스프링 security가 만든 login 페이지가 아닌 우리가 만든 login page 로 이동한다.
////                    }
////                })
//                .accessDeniedHandler(new AccessDeniedHandler() {
//                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//                        httpServletResponse.sendRedirect("/denied");
//                    }
//                });

//      >6. 세션 제어 필터 SessionManagementFilter, ConcurrentSessionFilter
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//        http
//                .formLogin();
//
//        http
//                .sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true);

//      >5-2.  세션 고정 보호
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//        http
//                .formLogin();
//        http
//                .sessionManagement()
////                .sessionFixation().none(); // 공격자가 cookie를 탈취할 위험이 있다.
//                .sessionFixation().changeSessionId(); // 세션 고정 보호 기능인데, Spring security가 기본적으로 지원한다.

//      >5-1. 동시 세션 제어
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//        http
//                .formLogin();
//        http
//                .sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(false);



//      >4. Anonymous
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//        http
//                .formLogin();

//      >3. rememberme
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//        http
//                .formLogin();
//
//        http
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .addLogoutHandler(new LogoutHandler(){
//                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
//                        HttpSession session = request.getSession();
//                        session.invalidate();
//                    }
//                })
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletResponse.sendRedirect("/login");
//                    }
//                })
//            .and()
//                .rememberMe()
//                .rememberMeParameter("remember")
//                .tokenValiditySeconds(3600)
//                .userDetailsService(userDetailsService);

//        >2. logout
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//        http
//                .formLogin();
//
//        http
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .addLogoutHandler(new LogoutHandler(){
//                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
//                        HttpSession session = request.getSession();
//                        session.invalidate();
//                    }
//                })
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletResponse.sendRedirect("/login");
//                    }
//                })
//                .deleteCookies("remember.me");


//        >1. login
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//        http
//                .formLogin()
//                    .loginPage("/loginPage")
//                    .defaultSuccessUrl("/")
//                    .failureUrl("/login")
//                    .usernameParameter("userId")
//                    .passwordParameter("passwd")
//                    .loginProcessingUrl("/login_proc")
//                    .successHandler(new AuthenticationSuccessHandler() {
//                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                            System.out.println("authentication" + authentication.getName());
//                            response.sendRedirect("/");
//                        }
//                    })
//                    .failureHandler(new AuthenticationFailureHandler(){
//                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                            System.out.println("exception" + exception.getMessage());
//                            response.sendRedirect("/login");
//                        }
//                    })
//                    .permitAll();
    }

}
