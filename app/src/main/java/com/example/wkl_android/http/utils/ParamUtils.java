package com.example.wkl_android.http.utils;


import android.net.Uri;
import android.text.TextUtils;

import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.LogUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * APP调用接口,获取签名的工具
 *
 * @author li
 * @since 2018/12/25
 */
public class ParamUtils {

    private ParamUtils() {
    }

    public static String addKeySignWithMethodGet(String url, Map<String, ? super String> params) {
        // 获取旧参数和去掉参数的url
        url = Processor.analysisPreviousUrlParams(url, params);
        // 执行签名操作
       // Processor.addKeySign(params);
        // 返回拼接好新参数集的请求路径
        String result = Processor.concatUrl(url, params);
        LogUtils.d("sign", result);
        return result;
    }

    public static void addKeySignWithMethodPost(Map<String, ? super String> params) {
       // Processor.addKeySign(params);
    }

    /**
     * 处理类
     */
    private static class Processor {
        private static Format timestampFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());




        /**
         * 对Map进行按照KEY值的首字母进行排序
         *
         * @param param 入参:未排序的{@link Map}
         * @return 出参:按照KEY值的字母顺序升序排序的{@link TreeMap}
         */
        private static Map<String, String> getSortedMapFromMap(Map<String, ? super String> param) {
            // 参数按key的字母顺序排序
            Map<String, String> map = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String str1, String str2) {
                    // 升序排序
                    return str1.compareTo(str2);
                }
            });
            // 遍历添加到Map，map排序
            for (Map.Entry<String, ? super String> entry : param.entrySet()) {
                String key = entry.getKey();
                if (TextUtils.isEmpty(key)) continue;
                String val = String.valueOf(entry.getValue());
                if (TextUtils.isEmpty(val)) continue;
                map.put(key, val);
            }
            return map;
        }

        private static String genTimestamp() {
            return timestampFormat.format(new Date());
        }

        /**
         * 在原集合中添加签名
         * 1.先清除原有数据
         * 2.添加时间戳 {@link #genTimestamp()}
         * 3.生成并添加签名 {@link #genSign(Map)}
         * 4.添加APP_KEY {@link C#APP_KEY}
         *
         * @param params 原有集合
         */
//        private static void addKeySign(@NonNull Map<String, ? super String> params) {
//            // 添加签名
//            params.remove("timestamp");
//            params.remove("sign");
//            params.remove("appKey");
//            //1.添加时间戳
//            params.put("timestamp", genTimestamp());
//            //2.添加签名
//            params.put("sign", genSign(params));
//            //3.添加APP_KEY
//            params.put("appKey", C.APP_KEY);
//        }

        /**
         * 拆分原链接中的URL和参数
         *
         * @param url    原链接
         * @param params 接收新参数的集合 previous
         * @return 到XXX.html
         */
        private static String analysisPreviousUrlParams(String url, Map<String, ? super String> params) {
            if (!url.contains("?")) {
                return url;
            }
            Uri uri = Uri.parse(url);
            Set<String> keys = uri.getQueryParameterNames();
            for (String key : keys) {
                if (TextUtils.isEmpty(key)) continue;
                String val = uri.getQueryParameter(key);
                if (val == null) continue;
                params.put(key, val);
            }
            return url.substring(0, url.indexOf('?'));
        }

        /**
         * GET方法拼接请求参数
         *
         * @param url    原链接
         * @param params 参数集
         * @return GET请求的接口URL
         */
        private static String concatUrl(String url, Map<String, ? super String> params) {
            if (TextUtils.isEmpty(url)) {
                return "";
            }
            if (params == null || params.size() == 0) {
                return url;
            }
            StringBuilder sb = new StringBuilder(url);
            if (url.contains("?")) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            for (Map.Entry<String, ? super String> entry : params.entrySet()) {
                String key = entry.getKey();
                if (TextUtils.isEmpty(key)) continue;
                String val = String.valueOf(entry.getValue());
                if (TextUtils.isEmpty(val)) continue;

                sb.append(key).append("=").append(val).append("&");
            }
            return sb.substring(0, sb.length() - 1);
        }
    }
}
