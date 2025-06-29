package shaimaa.clinic.shared.api.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
class GlobalControllerAdvice {
  private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerAdvice.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ErrorResponse handle400ValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.toMap(
                    FieldError::getField,
                    error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "invalid value"
            ));

    String errorMessage = (errors.size() == 1)
            ? String.format("%s %s",
            errors.entrySet().iterator().next().getKey(),
            errors.entrySet().iterator().next().getValue())
            : "Validation errors";

    return new ErrorResponse(errorMessage, errors);
  }



//  @ExceptionHandler(value = {BusinessRuleViolationException.class})
//  ResponseEntity<ErrorResponse> handle400ViolationException(BusinessRuleViolationException ex) {
//    LOG.error("Invariant", ex);
//
//    ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getErrors());
//    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//  }

  @ExceptionHandler(value = {Exception.class})
  ResponseEntity<ErrorResponse> handle500Exception(Exception ex) {
    LOG.error("Unexpected error", ex);

    ErrorResponse error = new ErrorResponse(ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
