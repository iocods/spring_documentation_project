package iocode.web.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DocGlobalExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<String> handleDocumentNotFoundException(DocumentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body("Document not found: " + e.getMessage());
    }

    @ExceptionHandler(DocumentListFullException.class)
    public ResponseEntity<String> handleDocumentListFullException(DocumentListFullException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body("Document list is full: " + e.getMessage());
    }

    @ExceptionHandler(DocumentFormatNotAcceptedException.class)
    public ResponseEntity<String> handleDocumentFormatNotAcceptedException(DocumentFormatNotAcceptedException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body("Document format not accepted: " + e.getMessage());
    }
}
