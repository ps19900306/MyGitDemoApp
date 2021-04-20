package com.ningwenqiang.glory.mygitdemoapp.string;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：字符串验证工具，主要是对字符串是否满足哪一种规则
 * 注意\d是表示数字 ，但是注意的是\是需要转义的 \\
 * 作者：宁文强
 * 日期:2016-10-13 10:37
 * 修改日期
 * 日期:2018-07-11 10:17
 */
public class TextVerifyTools {


    // 验证字符串是否是手机号码
    public static boolean verifyPhone(String phonestr) {
        if (TextUtils.isEmpty(phonestr))
            return false;
        Pattern p = Pattern.compile("1[3-8]\\d{9}");
        Matcher m = p.matcher(phonestr);
        return m.matches();
    }

    // 验证字符串是否是邮箱
    public static boolean verifyEmail(String emailstr) {
        if (TextUtils.isEmpty(emailstr))
            return false;
        Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher m = p.matcher(emailstr);
        return m.matches();
    }

    // 验证是否是日期
    public static boolean verifyDate(String dateStr) {
        if (TextUtils.isEmpty(dateStr))
            return false;
        Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher m = p.matcher(dateStr);
        return m.matches();
    }

    // 验证是否是汉字
    public static boolean verifyChinese(String dateStr) {
        if (TextUtils.isEmpty(dateStr))
            return false;
        boolean flag = true;
        for (int i = 0; i < dateStr.length(); i++) {
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(dateStr.substring(i, i + 1));
            if (!m.matches())
                flag = false;
        }
        return flag;
    }

    // 验证字符串是否是身份证 这里智能验证格式，不能判断正确性
    public static boolean verifyIDCard(String dateStr) {
        if (TextUtils.isEmpty(dateStr))
            return false;
        Pattern p = Pattern.compile("[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)");
        Matcher m = p.matcher(dateStr);
        return m.matches();
    }

    // 验证
    public static boolean verifyRule(String infoStr, String ruleStr) {
        boolean flag = false;
        if (TextUtils.isEmpty(infoStr))
            return flag;
        Pattern p = Pattern.compile(ruleStr);
        Matcher m = p.matcher(infoStr);
        flag = m.matches();
        return flag;
    }


    /**
     * 这里会默认调取以存在的方法，当最后的都对不上的时候
     * 会调取正则表达式子
     * @param infoStr
     * @param ruleStr
     * @return
     */
    public static boolean verifyStr(String infoStr, String ruleStr) {
        boolean flag = true;
        if (!TextUtils.isEmpty(ruleStr)) {
            if (ruleStr.equals("chinese_characters")) {
                flag = verifyChinese(infoStr);
            } else if (ruleStr.equals("id_card")) {
                flag = verifyIDCard(infoStr);
            } else if (ruleStr.equals("phone_num")) {
                flag = verifyPhone(infoStr);
            } else if (ruleStr.equals("email")) {
                flag = verifyEmail(infoStr);
            } else {
                flag = verifyRule(infoStr, ruleStr);
            }
        }
        return flag;
    }
}

