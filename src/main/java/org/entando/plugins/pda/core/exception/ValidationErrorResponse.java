package org.entando.plugins.pda.core.exception;

import static java.util.Collections.singletonList;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("PMD")
@SuppressFBWarnings
public class ValidationErrorResponse extends ErrorResponse {

    private ErrorData data;

    public ValidationErrorResponse(final String message) {
        super(message);
        data = new ErrorData();
    }

    public ValidationErrorResponse(final String message, final List<FieldError> errors) {
        this(message);
        errors.forEach(this::addError);
    }

    private void addError(final FieldError fieldError) {
        final String message = fieldError.getDefaultMessage();
        this.data.getErrors().put(fieldError.getField(), singletonList(message));
    }

    public void addError(final MessageSource messageSource, final Locale locale, final FieldError fieldError) {
        final String message = fieldError.getDefaultMessage();
        if (fieldError.getArguments() != null) {
            this.data.getErrors().put(fieldError.getField(), singletonList(message));
        }
    }

    @Data
    private class ErrorData {

        private Map<String, List<String>> errors = new HashMap<>();
    }

    public void addError(final MessageSource messageSource, final Locale locale,
            final String property, final String error, final Object[] arguments) {
        final String message = messageSource.getMessage(error, arguments, locale);
        this.data.getErrors().put(property, singletonList(message));
    }
}
