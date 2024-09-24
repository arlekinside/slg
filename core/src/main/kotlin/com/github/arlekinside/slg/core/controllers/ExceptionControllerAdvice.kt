package com.github.arlekinside.slg.core.controllers

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(Throwable::class)
    fun handleException(e: Throwable): ResponseEntity<String> {
        //Just encapsulate the exception for now
        return ResponseEntity.status(500)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"message\": \"Unexpected error\"}")
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleException(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity.status(400)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"message\": \"Message not readable\"}")
    }

}