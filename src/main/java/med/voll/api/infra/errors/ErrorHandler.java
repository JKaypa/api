package med.voll.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException error){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleError400(MethodArgumentNotValidException error){
        var errors = error.getFieldErrors().stream().map(ValidationError::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<String> businessExceptions(BusinessRuleException error){
        return ResponseEntity.badRequest().body(error.getMessage());
    }

    private record ValidationError(String field, String error){
        public ValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
