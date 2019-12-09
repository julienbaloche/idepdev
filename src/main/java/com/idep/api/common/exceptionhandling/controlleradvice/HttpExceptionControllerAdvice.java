package com.idep.api.common.exceptionhandling.controlleradvice;

import com.idep.api.common.exceptionhandling.ErrorResponseBody;
import com.idep.api.common.exceptionhandling.exception.BaseHttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpExceptionControllerAdvice {
    @ExceptionHandler({BaseHttpException.class})
    public ResponseEntity<ErrorResponseBody> handleBaseHttpException(BaseHttpException httpException) {
        return new ErrorResponseBody(httpException).toResponseEntity();
    }
}
