package com.hmw.mytoutiaoapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by han on 2018/6/6.
 */

public class StringUtil {

    public static String getStringNum(String s) {
        String regex = "[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.replaceAll("").trim();
    }
}
