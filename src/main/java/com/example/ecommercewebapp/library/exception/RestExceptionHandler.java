package com.example.ecommercewebapp.library.exception;


import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.rest.MetaResponse;
import com.example.ecommercewebapp.library.rest.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends BaseExceptionHandler {

    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(CoreException.class)
    public Response<MetaResponse> handleCoreException(CoreException coreException, Locale locale) {
        MessageCodes messageCode = coreException.getCode();
        String message = messageSource.getMessage(messageCode.getMessage(), coreException.getArgs(), locale);
        StringBuilder sb = new StringBuilder("[CoreException] messageCode: ");
        sb.append(messageCode.getCode());
        sb.append(" , message: ");
        sb.append(message);
        log.error(sb.toString());
        return respond(MetaResponse.of(messageCode.getCode(),message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<MetaResponse> handleValidationErrors(MethodArgumentNotValidException ex, Locale locale) {
        String error = ex.getBindingResult().getFieldErrors()
                .stream().findFirst().map(FieldError::getDefaultMessage).orElse(MessageCodes.BAD_REQUEST.getMessage());
        String message = messageSource.getMessage(error, null, locale);
        StringBuilder sb = new StringBuilder("[MethodArgumentNotValidException] code: ");
        sb.append(MessageCodes.BAD_REQUEST);
        sb.append(" , message: ");
        sb.append(message);
        log.error(sb.toString());
        return respond(MetaResponse.of(MessageCodes.BAD_REQUEST.getCode(),message));
    }
}
