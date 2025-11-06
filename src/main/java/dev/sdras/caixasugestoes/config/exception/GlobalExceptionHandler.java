package dev.sdras.caixasugestoes.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleRecursoNaoLocalizadoException(RecursoNaoLocalizadoException e,
            HttpServletRequest request) {
        log.error("Recurso nao localizado", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ApiError(request,
                        e.getMessage(),
                        HttpStatus.NOT_FOUND.value()));
    }
}
