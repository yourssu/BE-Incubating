package com.example.demo.commons.errors

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val message: String
){
    constructor(httpStatus: HttpStatus, error: String, message: String) : this(
        timestamp = LocalDateTime.now(),
        status = httpStatus.value(),
        error = error,
        message = message
    )
}

