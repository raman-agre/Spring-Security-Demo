package spring_security_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                                //Role based
                                .requestMatchers("/public").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/dev").hasRole("DEV")

                                //Permission based
                                .requestMatchers(HttpMethod.GET, "/profile/**").hasAuthority("READ_PROFILE")
                                .requestMatchers(HttpMethod.POST, "/profile/**").hasAuthority("WRITE_PROFILE")
                                .requestMatchers(HttpMethod.DELETE, "/profile/**").hasAuthority("DELETE_PROFILE")

                                .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("ram")
                .password("{noop}1234")
                .roles("USER", "DEV")
                .authorities("READ_PROFILE", "WRITE_PROFILE")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .authorities("READ_PROFILE", "WRITE_PROFILE", "DELETE_PROFILE")
                .build();

        UserDetails dev = User.withUsername("dev")
                .password("{noop}dev123")
                .roles("DEV")
                .authorities("READ_PROFILE")
                .build();

        return new InMemoryUserDetailsManager(user, admin, dev);
    }

    //to exclude any url from Spring Security. No security filters, no authentication/authorization, no security context
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/authentication");
    }
}
