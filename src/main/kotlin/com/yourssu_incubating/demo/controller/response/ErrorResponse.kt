package com.yourssu_incubating.demo.controller.response

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse (
    val timestamp: LocalDateTime,
    val status: Int,
    val message: String
) {
    constructor(httpStatus: HttpStatus, message: String): this(
        timestamp = LocalDateTime.now(),
        status = httpStatus.value(),
        message = message
    )
}