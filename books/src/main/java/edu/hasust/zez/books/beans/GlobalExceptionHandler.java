package edu.hasust.zez.books.beans;

import edu.hasust.zez.books.ResultCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
        System.out.println("init Global Exception Handler");
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResultCode handleConstraintViolationException(ConstraintViolationException e) {
        int index = e.getMessage().trim().indexOf(' ');
        if(index > 0) {
            return ResultCode.validateFailed(e.getMessage().substring(index + 1));
        } else {
            return ResultCode.validateFailed(e.getMessage());
        }
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResultCode handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return ResultCode.validateFailed(message);
    }
}

