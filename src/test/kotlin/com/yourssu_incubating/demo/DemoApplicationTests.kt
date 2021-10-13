package com.yourssu_incubating.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootTest
class DemoApplicationTests {

	@Test
	fun contextLoads() {
	}

}
