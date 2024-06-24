package com.filemanagement.filemanagement.exception


import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GeneralExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(exception: UserNotFoundException): ResponseEntity<Map<String, String?>> {
        return ResponseEntity(mapOf("message" to exception.message), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<Map<String, String?>> {
        return ResponseEntity(mapOf("message" to exception.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(FileNotFoundException::class)
    fun handleFileNotFoundException(exception: FileNotFoundException): ResponseEntity<Map<String, String?>> {
        return ResponseEntity(mapOf("message" to exception.message), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(exception: UsernameNotFoundException): ResponseEntity<Map<String, String?>> {
        return ResponseEntity(mapOf("message" to exception.message), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(exception: BadCredentialsException): ResponseEntity<Map<String, String?>> {
        return ResponseEntity(mapOf("message" to exception.message), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwtException(exception: ExpiredJwtException): ResponseEntity<Map<String, String?>> {
        return ResponseEntity(mapOf("message" to "JWT expired"), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(SignatureException::class)
    fun handleSignatureException(exception: SignatureException): ResponseEntity<Map<String, String?>> {
        return ResponseEntity(mapOf("message" to "Invalid JWT signature"), HttpStatus.UNAUTHORIZED)
    }
}




