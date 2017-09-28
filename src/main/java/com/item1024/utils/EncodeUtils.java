package com.item1024.utils;




import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xiongyu
 * @version Create at ：2017/4/18 16:31
 */

public class EncodeUtils {

    private static EncodeUtils ourInstance = new EncodeUtils();

    public static EncodeUtils getInstance() {
        return ourInstance;
    }


    public EncodeUtils() {
    }


    public String Encode(String target) {


        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] digest = messageDigest.digest(target.getBytes());


            return new BigInteger(1, digest).toString(16);
//            System.out.println(Base64.getEncoder().encodeToString(digest));
            //base64将每三个字节转成4个字节，这样高位就使用00补齐，这样最大也就是63，最小为0。一共只有64种情况，就不会出现乱码了。

            /**
             * Java8之前的做法
             */
//            BASE64Encoder encoder = new BASE64Encoder();
//            return encoder.encode(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }

    }



    public boolean isTokenValid(HttpServletRequest request) {


        String cToken = request.getParameter("token");
        if (cToken == null)
            return false;

        String sToken = (String) request.getSession().getAttribute("token");
        if (sToken == null)
            return false;

        if (!cToken.equals(sToken))
            return false;

        return true;

    }
}
