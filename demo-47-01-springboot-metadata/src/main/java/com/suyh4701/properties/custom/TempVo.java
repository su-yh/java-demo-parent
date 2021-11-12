package com.suyh4701.properties.custom;


import java.util.regex.Pattern;

/**
 * TODO: 如何解析自定义类型
 */
public class TempVo {
    private static final Pattern PATTERN = Pattern.compile("xxx\\\\d+ooo");

    private Integer count;

    public static TempVo parse(CharSequence text) {
        if (text == null) {
            return null;
        }

        if (text.length() <3 ) {
            return null;
        }
        String strText = text.toString();
        strText = strText.replace("xxx", "").replace("ooo", "");
        TempVo vo = new TempVo();
        vo.count = new Integer(strText);
        return vo;
    }
}
