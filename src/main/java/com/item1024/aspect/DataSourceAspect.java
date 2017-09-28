package com.item1024.aspect;

import com.item1024.Configs.HandleDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.item1024.annotation.DataSource;

import java.lang.reflect.Method;

/**
 * @author 阳十三
 * @email wdful165177@gmail.com
 * @blog http://www.item1024.com
 * @date 2017/9/4
 */

public class DataSourceAspect {
        /**
         * 在dao层方法之前获取datasource对象之前在切面中指定当前线程数据源路由的key
         */
        public void before(JoinPoint point)
        {


            Object target = point.getTarget();
//            System.out.println(target.toString());
            String method = point.getSignature().getName();
//            System.out.println(method);
            Class<?>[] classz = target.getClass().getInterfaces();
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                    .getMethod().getParameterTypes();
            try {
                Method m = classz[0].getMethod(method, parameterTypes);
                System.out.println("操作方法:"+m.getName());
                if (m != null && m.isAnnotationPresent(DataSource.class)) {
                    DataSource data = m.getAnnotation(DataSource.class);
                    System.out.println("用户选择数据库库类型："+data.value());
                    HandleDataSource.putDataSource(data.value());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
