package com.xlc.community.community.advice;


import com.alibaba.fastjson.JSON;
import com.xlc.community.community.dto.ResultDTO;
import com.xlc.community.community.exception.CustomizeErrorCode;
import com.xlc.community.community.exception.CustomizeExecption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
// 全局异常处理类


@ControllerAdvice
@Slf4j
public class CustomizeExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response,Model model, Throwable e) throws IOException {
        ModelAndView  m = new ModelAndView();
        HttpStatus status = getStatus(request);

        String contentType = request.getContentType();
        if ("application/json".equals(contentType)){
            //返回json
            ResultDTO resultDTO= null;

            if (e instanceof CustomizeExecption) {

                resultDTO = ResultDTO.errorOf((CustomizeExecption)e);

            } else {
                resultDTO =   ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            return  null;


        }else {
            // 错误页面跳转
            if (e instanceof CustomizeExecption) {

                model.addAttribute("message", e.getMessage());

            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }

            m.setViewName("error");
            return m;
        }
    }

    private HttpStatus getStatus(HttpServletRequest request){

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;

        }

        return HttpStatus.valueOf(statusCode);


    }

}

