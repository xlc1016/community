package com.xlc.community.community.advice;


import com.xlc.community.community.exception.CustomizeExecption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
// 全局异常处理类


@ControllerAdvice
@Slf4j
public class CustomizeExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(HttpServletRequest request,Model model,Throwable e) throws IOException {
        ModelAndView  m = new ModelAndView();
        HttpStatus status = getStatus(request);
        if (e instanceof CustomizeExecption){

            model.addAttribute("message", e.getMessage());

        }else {
            model.addAttribute("message", "error!!!!!");
        }

        m.setViewName("error");
        return  m;
    }

    private HttpStatus getStatus(HttpServletRequest request){

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;

        }

        return HttpStatus.valueOf(statusCode);


    }

}

