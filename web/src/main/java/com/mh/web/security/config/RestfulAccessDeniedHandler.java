package com.mh.web.security.config;

import com.alibaba.fastjson.JSONObject;
import com.mh.web.security.utils.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 没有权限访问时返回的结果
 * @author 刘昌兴
 *
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String,String> map = new HashMap<>();

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JSONObject.toJSONString(new ResponseResult(20001,"权限不足，请联系管理员！",map,false)));
        out.flush();
        out.close();

    }

}


