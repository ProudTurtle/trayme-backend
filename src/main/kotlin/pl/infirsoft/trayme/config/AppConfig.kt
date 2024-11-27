package pl.infirsoft.trayme.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebSecurity
class AppConfig {
}