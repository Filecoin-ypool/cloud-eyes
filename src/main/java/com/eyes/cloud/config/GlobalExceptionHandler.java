package com.eyes.cloud.config;

import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.exception.BusinessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

/**
 * 全局异常处理器
 *
 * @author fugq
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String PARAM_JSON_PARSE_ERROR = "JSON parse error";

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
        Result result = Result.build(-1, null);
        ex.printStackTrace();
        if (ex instanceof BindException) {
            BindingResult bindingResult = ((BindException) ex).getBindingResult();
            if (bindingResult.hasErrors()) {
                List<ObjectError> errorList = bindingResult.getAllErrors();
                ObjectError objectError = errorList.get(0);
                String message = objectError.getDefaultMessage();
                FieldError fieldError = (FieldError) objectError;
                String field = fieldError.getField();
                result.setMsg(field + ":" + message);
            }
        } else if (ex instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            if (bindingResult.hasErrors()) {
                List<ObjectError> errorList = bindingResult.getAllErrors();
                ObjectError objectError = errorList.get(0);
                String message = objectError.getDefaultMessage();
                FieldError fieldError = (FieldError) objectError;
                String field = fieldError.getField();
                result.setMsg(field + ":" + message);
            }
        } else if (ex instanceof BusinessException) {
            BusinessException businessException = ((BusinessException) ex);
            result.setCode(businessException.getErrCode());
            result.setMsg(businessException.getErrMsg());
        } else if (ex instanceof MaxUploadSizeExceededException) {
            result.setCode(-1);
            result.setMsg("上传文件大小超过限制");
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            result.setCode(-1);
            result.setMsg("请求方式错误");
        } else if (ex instanceof MultipartException) {
            result.setCode(-1);
            result.setMsg(ex.getMessage());
        } else if (ex instanceof MissingServletRequestParameterException || ex instanceof MissingServletRequestPartException) {
            result.setCode(-1);
            result.setMsg(ex.getMessage());
        } else if (ex instanceof HttpMessageNotReadableException) {
            String message = ex.getMessage();
            if (message.contains(PARAM_JSON_PARSE_ERROR)) {
                result.setCode(-1);
                result.setMsg("入参JSON格式错误");
            } else {
                result.setCode(-1);
                result.setMsg("入参格式异常");
            }
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            result.setCode(-1);
            result.setMsg("contentType错误");
        } else if (ex instanceof DuplicateKeyException) {
            result.setCode(-1);
            result.setMsg("已存在，请无重复添加");
        } else {
            result.setCode(-1);
            result.setMsg("未知异常");
        }
        return result;
    }
}