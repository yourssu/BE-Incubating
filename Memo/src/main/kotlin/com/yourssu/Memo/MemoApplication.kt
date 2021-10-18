package com.yourssu.Memo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class MemoApplication

fun main(args: Array<String>) {
	runApplication<MemoApplication>(*args)
}
