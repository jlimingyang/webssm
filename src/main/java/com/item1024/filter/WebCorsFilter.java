package com.item1024.filter;

/**
 * @author 阳十三
 * @email wdful165177@gmail.com
 * @blog http://www.item1024.com
 * @date 2017/9/6
 */

import com.google.gson.Gson;
import com.thetransactioncompany.cors.CORSConfiguration;
import com.thetransactioncompany.cors.CORSFilter;
import com.item1024.utils.JwtUtils.JwtUtil;
import com.item1024.utils.JwtUtils.TokenState;
import com.item1024.utils.ResultWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 服务端跨域处理过滤器,该过滤器需要依赖cors-filter-2.2.1.jar和java-property-utils-1.9.1.jar
 * @author running@vip.163.com
 *
 */
@WebFilter(urlPatterns={"/Anh/*"},asyncSupported=true,
        initParams={
                @WebInitParam(name="cors.allowOrigin",value="*"),
                @WebInitParam(name="cors.supportedMethods",value="CONNECT, DELETE, GET, HEAD, OPTIONS, POST, PUT, TRACE"),
                @WebInitParam(name="cors.supportedHeaders",value="token,Accept, Origin, X-Requested-With, Content-Type, Last-Modified"),//注意，如果token字段放在请求头传到后端，这里需要配置
                @WebInitParam(name="cors.exposedHeaders",value="Set-Cookie"),
                @WebInitParam(name="cors.supportsCredentials",value="true")
        })
public class WebCorsFilter extends CORSFilter implements Filter {


    public void init(FilterConfig config) throws ServletException {
        System.out.println("跨域资源处理过滤器初始化了");
        super.init(config);
    }

    public void doFilter(ServletRequest arg1, ServletResponse arg2, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)arg1;
        HttpServletResponse response=(HttpServletResponse) arg2;
//        if(request.getRequestURI().endsWith("/servlet/login")){
//            //登陆接口不校验token，直接放行
//            chain.doFilter(request, response);
//            return;
//        }
        System.out.println("cors token 验证开始");
        String token = request.getHeader("token");
        System.out.println("token filter:"+token);
        //token不存在
        if (null != token && !token.equals(" ")) {
            Map<String, Object> resultMap = JwtUtil.validToken(token);
            System.out.println(resultMap.toString());
            TokenState state = TokenState.getTokenState((String) resultMap.get("state"));
            System.out.println(state);
            switch (state) {
                case VALID:
                    //取出payload中数据,放入到request作用域中
                    request.setAttribute("data", resultMap.get("data"));
                    //放行
                    super.doFilter(request, response, chain);
                    break;
                case EXPIRED:
                case INVALID:
                default:
                    System.out.println("无效token");
                    responseMessage(response, response.getWriter(), new ResultWrapper<>().ErrorWithData("证书过期"));
                    break;
            }
        } else {
            System.out.println("token = null");
            responseMessage(response, response.getWriter(), new ResultWrapper<>().ErrorWithData("证书无效ex0"));
//            response.sendRedirect("/login_register.py");
        }
    }


    public void setConfiguration(CORSConfiguration config) {
        super.setConfiguration(config);
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResultWrapper resultWrapper) {
        response.setContentType("application/json; charset=utf-8");
        String json = new Gson().toJson(response);
        out.print(json);
        out.flush();
        out.close();
    }
}
