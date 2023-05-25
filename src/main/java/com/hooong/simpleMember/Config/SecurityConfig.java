package com.hooong.simpleMember.Config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.hooong.simpleMember.Service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 인증을 무시하기 위한 설정
        web.ignoring().antMatchers("/css/**","/js/**","/img/**","/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable(); //이거 disalbe 하면 일반페이지 403에러 막아줌 but 안하면 로그인페이지 에러남
        http.csrf().ignoringAntMatchers("/api/memos/**","/notice/**","/content/**"); //이걸로 원하는 페이지부분만 disable해줌
        http.authorizeRequests()
                .antMatchers("/**").permitAll()

                .and()

            .formLogin()     // 로그인 설정
                .loginPage("/member/login")      // 커스텀 login 페이지를 사용
                .defaultSuccessUrl("/main")      // 로그인 성공 시 이동할 페이지
                .failureUrl("/") //로그인 실패시 이동할 페이지
                .permitAll()
                .and()

            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)    // 세션 초기화
                .and()
            .exceptionHandling();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 로그인 처리를 하기 위한 AuthenticationManagerBuilder를 설정
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}


