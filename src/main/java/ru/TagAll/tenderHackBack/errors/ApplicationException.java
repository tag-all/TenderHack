package ru.TagAll.tenderHackBack.errors;

import lombok.Getter;
import ru.TagAll.tenderHackBack.errors.model.ApplicationErrorDto;

/**
 * Ошибки приложения.
 *
 * @author Iurii Babalin.
 */
@Getter
public class ApplicationException extends RuntimeException {
    /**
     * {@link ApplicationErrorDto}.
     */
    private final ApplicationErrorDto error;

    public ApplicationException(ApplicationErrorDto error) {
        super(error.getMessage());
        this.error = error;
    }

    public ApplicationException(ApplicationErrorDto error, Throwable throwable) {
        super(error.getMessage(), throwable);
        this.error = error;
    }
}
