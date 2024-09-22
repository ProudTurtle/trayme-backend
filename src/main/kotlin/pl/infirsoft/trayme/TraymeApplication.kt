package pl.infirsoft.trayme

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TraymeApplication

fun main(args: Array<String>) {
	runApplication<TraymeApplication>(*args)
}
