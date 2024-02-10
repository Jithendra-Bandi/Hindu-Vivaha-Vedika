package com.example.demo.Secure;

// import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecureConfig {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
            .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/","/profiles","/register-profile","/registration","/css/**","/js/**","/images/**").permitAll()
            .anyRequest().authenticated()
            )
            .formLogin((form) -> form
            .loginPage("/login").loginProcessingUrl("/login")
            .defaultSuccessUrl("/profile-display").permitAll());
            // .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
            // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            // .logoutSuccessUrl("/registration").permitAll());
            // .formLogin(withDefaults());
            return http.build();
    }


    // @Bean
    // WebSecurityCustomizer webSecurityCustomizer() {
    //     return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    // }

    @Bean
    UserDetailsService myUserDetailsService() {
        return new MyUserDetailsService();
    }

    // @Bean
    // PasswordEncoder passwordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }

    @Bean
    BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
