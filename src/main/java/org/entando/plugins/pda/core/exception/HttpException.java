package org.entando.plugins.pda.core.exception;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Arrays;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {

    private final HttpStatus status;

    @SuppressFBWarnings
    private final Object[] args;

    public HttpException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
        this.args = new Object[]{};
    }

    public HttpException(final HttpStatus status, final String message, final Object... args) {
        super(message);
        this.status = status;
        this.args = Arrays.copyOf(args, args.length);
    }

    public HttpException(final HttpStatus status, final String message, final Throwable throwable) {
        super(message, throwable);
        this.status = status;
        this.args = new Object[]{};
    }
}
