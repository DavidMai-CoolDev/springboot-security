package selflearning.home.demo.springboot.security.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import selflearning.home.demo.springboot.security.security.customfilter.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin1").password("admin").roles("ADMIN");

    }



    //    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }
@Autowired
private MyFilter myFilter;
    @Autowired
    private MyFilterBeforeTheChain myFilterBeforeTheChain;
    @Autowired
    private MyFilterAfterTheChain myFilterAfterTheChain;

    public void FilterChainConfig(MyFilter myFilter){
        this.myFilter = myFilter;
    }

    public void FilterBeforeTheChainConfig(MyFilterBeforeTheChain myFilterBeforeTheChain){
        this.myFilterBeforeTheChain = myFilterBeforeTheChain;
    }
    public void FilterAfterTheChainConfig(MyFilterAfterTheChain myFilterAfterTheChain){
        this.myFilterAfterTheChain = myFilterAfterTheChain;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .addFilterAfter(new TestFilter2(), SwitchUserFilter.class)
                .csrf()
                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/welcome/**").hasAnyRole("USER","ADMIN");
//                .and().addFilterAfter(myFilter,SwitchUserFilter.class);
//                .anyRequest()
//                .authenticated()
//                .and()
//                .exceptionHandling().accessDeniedPage("/403");
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler(new MyAuthenticationSuccessHandler())
//                .failureUrl("/login?error=true");

//                .and()
//
//                .addFilterBefore(new CustomLoginFilter("/login-user", authenticationManager()), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterAfter(myFilter, FilterSecurityInterceptor.class);
        http.addFilterAfter(myFilterBeforeTheChain,BasicAuthenticationFilter.class);
        http.addFilterBefore(myFilter, SwitchUserFilter.class);
        http.addFilterAfter(myFilterAfterTheChain, SwitchUserFilter.class);
//        http.addFilterBefore(myFilter, ChannelProcessingFilter.class);
    }

}
