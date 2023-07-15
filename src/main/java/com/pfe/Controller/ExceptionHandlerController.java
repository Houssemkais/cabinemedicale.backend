package com.pfe.Controller;

import com.pfe.exception.DomainException;
import com.pfe.exception.InfrastructureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler(value
            = {DomainException.class})
    protected ResponseEntity<ExceptionBody> handleConflict(
            DomainException exception, WebRequest request) {
        log.error("", exception);
        return ResponseEntity.status(HttpStatus.valueOf(exception.getError().getCode())).body(new ExceptionBody(0, exception.getError().getError()));
    }

    @ExceptionHandler(value
            = {InfrastructureException.class})
    protected ResponseEntity<ExceptionBody> handleConflict(
            InfrastructureException exception, WebRequest request) {
        log.error("", exception);
        return ResponseEntity.status(HttpStatus.valueOf(exception.getError().getCode())).body(new ExceptionBody(0, exception.getError().getError()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionBody> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("", ex);
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> fieldError.getField()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.valueOf(400)).body(new ExceptionBody(0, errors));
    }

    @ExceptionHandler(value
            = {Throwable.class})
    protected ResponseEntity<ExceptionBody> handleConflict(
            Throwable exception, WebRequest request) {
        log.error("", exception);
        return ResponseEntity.status(HttpStatus.valueOf(500)).body(new ExceptionBody(0, "EXCEPTION NOT HANDLED"));
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


    class ExceptionBody {
        private Integer code;
        private Object text;

        public ExceptionBody(Integer code, Object text) {
            this.code = code;
            this.text = text;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Object getText() {
            return text;
        }

        public void setText(Object text) {
            this.text = text;
        }
    }
}
