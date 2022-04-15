package ru.TagAll.tenderHackBack.swagger;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Конфигуратор для описания ошибки swagger ui.
 *
 * @author Iurii Babalin.
 */
@ApiResponses(value = {@ApiResponse(
        code = 400,
        message = "Bad Request",
        response = ApiResponse.class,
        examples = @Example(value = {
                @ExampleProperty(
                        mediaType = "application/json",
                        value = "{\n" +
                                "  \"type\": \"тип ошибки\",\n" +
                                "  \"code\": \"код ошибки\",\n" +
                                "  \"message\": \"текст ошибки\",\n" +
                                "  \"time\": \"время появления ошибки\"\n" +
                                "}"
                )
        }))
})
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BadRequestSystemError {
}
