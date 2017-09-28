package com.item1024.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author 阳十三
 * @email wdful165177@gmail.com
 * @blog http://www.item1024.com
 * @date 2017/8/4
 */

public class CSRFTokenUtil {
        public static String generate(HttpServletRequest request) {

            return UUID.randomUUID().toString();
        }
    }
