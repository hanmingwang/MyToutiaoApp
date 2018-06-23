package com.hmw.mytoutiaoapp.util;

/**
 * Created by han on 2018/6/23.
 */

public class ChineseUtil {

    /**
     * Unicode编码转汉字
     */
    public static String UnicodeToChs(String s) {
        StringBuffer sb = new StringBuffer(s);

        int pos;
        while ((pos = sb.indexOf("\\u")) > -1) {
            String tmp = sb.substring(pos, pos + 6);
            sb.replace(pos, pos + 6, Character.toString((char) Integer.parseInt(tmp.substring(2), 16)));
        }
        s = sb.toString();
        return s;
    }
}
