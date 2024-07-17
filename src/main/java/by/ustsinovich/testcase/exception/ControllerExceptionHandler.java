package by.ustsinovich.testcase.exception;

import by.ustsinovich.testcase.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponseDto resourceNotFoundExceptionHandler(ResourceNotFoundException exception, WebRequest request) {
        return new ErrorResponseDto(
                HttpStatus.NOT_FOUND,
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto globalExceptionHandler(Exception exception, WebRequest request) {
        return new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR,
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }

}
