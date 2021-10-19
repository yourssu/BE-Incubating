package jihwan1.jihwan1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class Jihwan1Application

fun main(args: Array<String>) {
	runApplication<Jihwan1Application>(*args)
}
