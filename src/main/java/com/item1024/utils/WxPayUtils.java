package com.item1024.utils;

import okhttp3.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 阳十三
 * @email wdful165177@gmail.com
 * @blog http://www.item1024.com
 * @date 2017/9/12
 */

public class WxPayUtils {
    /**
     * 创建证书
     * @param packageParams
     * @param API_KEY
     * @return
     */
    public static String createSign(SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = String.valueOf(entry.getKey());
            String v = String.valueOf(entry.getValue());
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + API_KEY);
        System.out.println("sign:"+sb);
        String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase();
        return sign;
    }

    /**
     *
     * @param parameters
     * 微信用  将map转为weixin用的xml
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    private final static String DEFAULT_ENCODING = "UTF-8";
    private final static int CONNECT_TIMEOUT = 5000;

    public static String postData(String url, String xmlData, String contentType) throws IOException {
        String result = null;
        MediaType MEDIA_TYPE = MediaType.parse("application/xml; charset="+contentType);
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, xmlData);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //发送请求获取响应
        Response response=okHttpClient.newCall(request).execute();
        //判断请求是否成功
        if(response.isSuccessful()){
            //打印服务端返回结果
            result = response.body().string();
        }
        return result;
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length
     *            int 设定所取出随机数的长度。length小于32
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        if (length>=32){
            System.out.println("随机数出错");
            return 0;
        }
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 获取本机的ip地址
     * @return String*/
    public static String getHostIP(){
        InetAddress addr;
        String ip = "";
        try {
            addr = InetAddress.getLocalHost();
            ip=addr.getHostAddress().toString(); //获取本机ip
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
