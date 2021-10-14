package com.yourssu_incubating.demo.controller.exception.handler

import com.yourssu_incubating.demo.controller.exception.MemoNotExistException
import com.yourssu_incubating.demo.controller.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemoApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MemoNotExistException::class)
    fun handleMemoNotExistException(exception: MemoNotExistException): ErrorResponse {
        return ErrorResponse(HttpStatus.NOT_FOUND, exception.message!!)
    }
}