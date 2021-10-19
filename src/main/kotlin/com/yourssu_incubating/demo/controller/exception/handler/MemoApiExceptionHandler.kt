package com.yourssu_incubating.demo.controller.exception.handler

import com.yourssu_incubating.demo.controller.exception.MemoNotExistException
import com.yourssu_incubating.demo.controller.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class MemoApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MemoNotExistException::class)
    fun handleMemoNotExistException(exception: MemoNotExistException): ErrorResponse {
        return ErrorResponse(HttpStatus.NOT_FOUND, exception.message!!)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleDateFormatInvalidException(exception: MethodArgumentTypeMismatchException): ErrorResponse {
        return ErrorResponse(HttpStatus.BAD_REQUEST, "Check date format")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(exception: MethodArgumentNotValidException): ErrorResponse {
        return ErrorResponse(HttpStatus.BAD_REQUEST, exception.message)
    }
}