package com.projectKotlinSpring.KotlinSpring.controller.errors

import com.fasterxml.jackson.core.JsonParseException
import com.projectKotlinSpring.KotlinSpring.service.exceptions.DatabaseException
import com.projectKotlinSpring.KotlinSpring.service.exceptions.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun ResourceNotFoundExceptionHandler(e: ResourceNotFoundException, request: HttpServletRequest): ResponseEntity<StandartError>{
        val error = "Resource Not Found!"
        val status: HttpStatus = HttpStatus.NOT_FOUND
        val standartError : StandartError = StandartError(Instant.now(),status.value(),error,e.message,request.requestURI)
        return ResponseEntity.status(status).body(standartError)
    }

    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(request: HttpServletRequest, e: JsonParseException): ResponseEntity<StandartError> {
        val error = "Json Error!"
        val status: HttpStatus = HttpStatus.BAD_REQUEST
        val jsonStandartError: StandartError = StandartError(Instant.now(),status.value(),error,e.message,request.requestURI)
        return  ResponseEntity.status(status).body(jsonStandartError)
    }

    @ExceptionHandler(DatabaseException::class)
    fun dataBaseExceptionHandler(request: HttpServletRequest, e: DatabaseException): ResponseEntity<StandartError>{
        val error = "Database Integrity error!"
        val status: HttpStatus = HttpStatus.BAD_REQUEST
        val dataStandartError: StandartError = StandartError(Instant.now(),status.value(),error,e.message,request.requestURI)
        return ResponseEntity.status(status).body(dataStandartError)
    }
}