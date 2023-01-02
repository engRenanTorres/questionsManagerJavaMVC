package br.com.engrenantorres.questionmanager.config;

import br.com.engrenantorres.questionmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class DevWebSecurityConfig {

  @Autowired
  private UserService userService;

  @Autowired
  private DataSource dataSource;


  //config dynamic requests
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests((requests) -> requests
        .antMatchers("/",
            "/home","/signup","/js/**","/static/**",
            "/css/**", "/images/**","/vendor/**",
            "/fonts/**")
          .permitAll()
        .antMatchers("/question-form/**").hasRole("ADM")
        .anyRequest().authenticated()
      )//.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
      .and()
      .formLogin(form -> form
        .loginPage("/login")
        .defaultSuccessUrl("/",true)
        .permitAll()
      )
      .logout(logout ->
        logout.logoutUrl("/logout")
          .logoutSuccessUrl("/")
      );

    return http.build();
  }

  //config the static requests like css, js and more.
/*  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
  }*/


  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public static PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

/*  @Bean
  public JdbcUserDetailsManager users(PasswordEncoder encoder){
   UserDetails user =
      User.builder()
        .username("admin")
        .password(encoder.encode("123456"))
        .roles("ADM")
        .build();
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
    //jdbcUserDetailsManager.createUser(user);
    return jdbcUserDetailsManager;
  }*/


}
