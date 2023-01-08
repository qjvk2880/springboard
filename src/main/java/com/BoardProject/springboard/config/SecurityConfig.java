package com.BoardProject.springboard.config;

import com.BoardProject.springboard.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final MemberService memberService;
    AuthenticationManager authenticationManager;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(memberService);
        authenticationManager = authenticationManagerBuilder.build();

        http.csrf().disable();
        http.authorizeHttpRequests()
//                .antMatchers("/user/**").authenticated()
//                .antMatchers("/manager/**").hasAnyRole("ROLE_ADMIN", "ROLE_MANAGER")
//                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/member/myinfo").hasRole("USER")
                .antMatchers("/**").permitAll()
                .and()
                .authenticationManager(authenticationManager)
                .formLogin()
                .loginPage("/member/memberLoginForm")
//                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/member/memberLoginResult")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/logoutResult")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/member/denied");
        return http.build();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//    }
}
