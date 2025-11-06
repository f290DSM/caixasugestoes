package dev.sdras.caixasugestoes.config.exception;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class ApiError {
    private String message;
    private String path;
    private String method;
    private int status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    public ApiError(HttpServletRequest request, String message, int status) {
        this.message = message;
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status;
    }
}
