package com.example.springbootdemo.config;

import org.springframework.util.StringUtils;
import com.example.springbootdemo.dao.entity.system.SystemUser;
import com.example.springbootdemo.dao.mapper.system.SystemUserMapper;
import com.example.springbootdemo.system.SecurityUser;
import com.example.springbootdemo.utils.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index")
                .permitAll().successHandler(loginSuccessHandler()).failureHandler(loginFailureHandler())
                .and().logout().permitAll().invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler())
                .and().sessionManagement().maximumSessions(10).expiredUrl("/loginPage");
        http.csrf().disable();//关闭CSRF跨域
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/lib/**","iconfont/**","/images/**", "/receiveData/**", "/appFill/**", "/systemApp/**");
    }


    @Bean
    public AuthenticationFailureHandler loginFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler(){
            @Override
            public void onAuthenticationFailure (HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
                ResponseResult jsonData=null;
                if (exception.getMessage().equals("用户不存在")) {
                    jsonData = new ResponseResult("用户不存在", null, 402);
                }
                if (exception.getMessage().equals("Bad credentials")) {
                    jsonData = new ResponseResult("用户名或密码错误", null, 403);
                }
                if(!StringUtils.isEmpty(jsonData)){
                    String json = objectMapper.writeValueAsString(jsonData);//包装成Json 发送的前台
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(json);
                    out.flush();
                    out.close();
                }
            }
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                SecurityUser user=null;
                try{
                    user = (SecurityUser) authentication.getPrincipal();
                    log.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");

                }catch (Exception e){
                    log.info("LOGOUT EXCEPTION , e : " + e.getMessage());

                }
                httpServletResponse.sendRedirect("/loginPage");
            }
        };
    }

    //成功登录处理句柄
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler(){
        return new SavedRequestAwareAuthenticationSuccessHandler(){
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
                SecurityUser userDetails=(SecurityUser)authentication.getPrincipal();
                log.info("USER: "+userDetails.getUsername()+" LOGIN SUCCESS");
                ResponseResult jsonData = new ResponseResult("认证OK",null,0);
                String json=objectMapper.writeValueAsString(jsonData);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out=response.getWriter();
                out.write(json);
                out.flush();
                out.close();
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Autowired
            private SystemUserMapper systemUserMapper;
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               SystemUser systemUser=systemUserMapper.getSystemUserByLoginName(username);
               if(systemUser!=null){
                   return new SecurityUser(systemUser);
               }else
                   throw new UsernameNotFoundException("Username "+username +"not found");
            }
        };
    }


}
