package pl.infirsoft.trayme.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Configuration
    class SecurityConfig {

        @Bean
        fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
            return http
                .authorizeHttpRequests { registry ->
                    registry.requestMatchers("/", "/contact", "/modules").permitAll()
                    registry.requestMatchers("/notes/**").permitAll()
                    registry.anyRequest().authenticated()
                }
                .oauth2ResourceServer { oauth2 ->
                    oauth2.jwt { jwt ->
                        jwt.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                    }
                }
                .build()
        }
    }

}