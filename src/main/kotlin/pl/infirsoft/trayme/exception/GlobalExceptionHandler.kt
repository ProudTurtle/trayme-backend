package pl.infirsoft.trayme.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val status = when (ex) {
            is NoteNotFoundException -> HttpStatus.NOT_FOUND
            is UserNotFoundException -> HttpStatus.UNAUTHORIZED
            is SpaceNotFoundException -> HttpStatus.NOT_FOUND
            is ModuleNotFoundException -> HttpStatus.NOT_FOUND
            is NoteServiceException -> HttpStatus.NOT_FOUND
            is SpaceAlreadyAssignedException -> HttpStatus.BAD_REQUEST
            is ShareKeyExpiredException -> HttpStatus.BAD_REQUEST
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }

        val message = ex.message ?: "An error occurred"
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)


        return createErrorResponse(status, message, timestamp)
    }

    private fun createErrorResponse(
        status: HttpStatus,
        message: String,
        timestamp: String
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = status.value(),
            message = message,
            timestamp = timestamp
        )
        return ResponseEntity(error, status)
    }

    data class ErrorResponse(
        val status: Int,
        val message: String,
        val timestamp: String
    )
}
