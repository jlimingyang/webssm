package com.item1024.utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiongyu
 * @version Create at ：2017/5/24 10:13
 */

public class MoneyUtils {
    private static final Map<String,ThreadLocal<DecimalFormat>> moneyMap = new HashMap<>();

    private static final double CENT_RATE = 100.0;

    /**
     * 获取当前线程的decimalFormat工具类,因为该类是非同步的
     * @param pattern 格式
     * @return 该实例
     */
    private static DecimalFormat getDecimal(final String pattern){
        ThreadLocal<DecimalFormat> instance = moneyMap.get(pattern);
        if (instance == null){
            synchronized (MoneyUtils.class){
                instance = moneyMap.get(pattern);
                if (instance == null){
                    instance = new ThreadLocal<DecimalFormat>(){
                        @Override
                        protected DecimalFormat initialValue() {
                            return new DecimalFormat(pattern);
                        }
                    };
                }
                moneyMap.put(pattern,instance);
            }
        }
        return instance.get();
    }

    /**
     * 分转元
     * @param cent 分
     * @return 元
     */
    public static String cent2yuan(Long cent){
        return getDecimal("0.00").format(cent/CENT_RATE);
    }

    /**
     * 元转分
     * @param yuan 元金额
     * @return 分
     */
    public static Long yuan2cent(double yuan) {
        return Math.round(yuan * CENT_RATE);
    }
}
