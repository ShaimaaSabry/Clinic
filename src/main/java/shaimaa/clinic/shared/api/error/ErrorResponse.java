package shaimaa.clinic.shared.api.error;

import java.util.Collections;
import java.util.Map;

record ErrorResponse(
        String errorMessage,
        Map<String, String> errors
) {
  ErrorResponse(String errorMessage) {
    this(errorMessage, Collections.emptyMap());
  }
}
