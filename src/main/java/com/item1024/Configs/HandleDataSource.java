package com.item1024.Configs;

/**
 * @author 阳十三
 * @email wdful165177@gmail.com
 * @blog http://www.item1024.com
 * @date 2017/9/4
 */

public class HandleDataSource {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * 绑定当前线程数据源路由的key
     * @param datasource
     */
    public static void putDataSource(String datasource) {
        holder.set(datasource);
    }

    /**
     * 获取当前线程的数据源路由的key
     * @return
     */
    public static String getDataSource() {
        return holder.get();
    }
}
