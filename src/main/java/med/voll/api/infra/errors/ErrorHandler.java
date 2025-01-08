package med.voll.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleError400(MethodArgumentNotValidException error){
        var errors = error.getFieldErrors().stream().map(ValidationError::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record ValidationError(String field, String error){
        public ValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
