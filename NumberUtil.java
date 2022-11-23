package com.csg.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By Chryl on 2022-11-23.
 * * FileName: NumberUtil
 * * Date:     2022/6/15 14:55
 * * Description: 数字转换中文
 */
public class NumberUtil {

    private NumberUtil() {
    }

    /**
     * 中文数字
     */
    private static final String[] CN_NUM = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    /**
     * 中文数字单位
     */
    private static final String[] CN_UNIT = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};

    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";

    /**
     * 特殊字符：点
     */
    private static final String CN_POINT = "点";


    /**
     * int 转 中文数字
     * 支持到int最大值
     *
     * @param intNum 要转换的整型数
     * @return 中文数字
     */
    public static String int2chineseNum(int intNum) {
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int intNum2 = intNum;
        boolean isNegative = false;
        if (intNum < 0) {
            isNegative = true;
            intNum *= -1;
        }
        int count = 0;
        while (intNum > 0) {
            sb.insert(0, CN_NUM[intNum % 10] + CN_UNIT[count]);
            intNum = intNum / 10;
            count++;
        }

        if (isNegative)
            sb.insert(0, CN_NEGATIVE);
        // 10-19时,得到十~十九而不是一十~一十九
        sb = "一".equals(sb.substring(0, 1)) && intNum2 < 100 && intNum2 > 1 ? sb2.append(sb.substring(1, sb.length())) : sb;
        return sb.toString().replaceAll("零[千百十]", "零").replaceAll("零+万", "万")
                .replaceAll("零+亿", "亿").replaceAll("亿万", "亿零")
                .replaceAll("零+", "零").replaceAll("零$", "");
    }

    /**
     * bigDecimal 转 中文数字
     * 整数部分只支持到int的最大值
     *
     * @param bigDecimalNum 要转换的BigDecimal数
     * @return 中文数字
     */
    public static String bigDecimal2chineseNum(BigDecimal bigDecimalNum) {
        if (bigDecimalNum == null)
            return CN_NUM[0];

        StringBuffer sb = new StringBuffer();

        //将小数点后面的零给去除
        String numStr = bigDecimalNum.abs().stripTrailingZeros().toPlainString();

        String[] split = numStr.split("\\.");
        String integerStr = int2chineseNum(Integer.parseInt(split[0]));

        sb.append(integerStr);

        //如果传入的数有小数，则进行切割，将整数与小数部分分离
        if (split.length == 2) {
            //有小数部分
            sb.append(CN_POINT);
            String decimalStr = split[1];
            char[] chars = decimalStr.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = Integer.parseInt(String.valueOf(chars[i]));
                sb.append(CN_NUM[index]);
            }
        }

        //判断传入数字为正数还是负数
        int signum = bigDecimalNum.signum();
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }

        return sb.toString();
    }

    /**
     * 校验是不是汉字数字
     *
     * @param inNum
     * @return
     */
    public static boolean validChineseNum(String inNum) {

        String regex = "^[零一二三四五六七八九十]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(inNum);
        if (m.matches()) {
            System.out.println(inNum + "是汉字的数字");
            return true;
        } else {
            System.out.println(inNum + "不是汉字的数字");
            return false;
        }
    }

    /**
     * 处理阿拉伯数字为中文数字
     *
     * @param queryText
     * @return
     */
    public static String convertNum2ChineseNum(String queryText) {
        if (queryText.contains("0")) {
            queryText = queryText.replaceAll("0", "零");
        }
        if (queryText.contains("1")) {
            queryText = queryText.replaceAll("1", "一");
        }
        if (queryText.contains("2")) {
            queryText = queryText.replaceAll("2", "二");
        }
        if (queryText.contains("3")) {
            queryText = queryText.replaceAll("3", "三");
        }
        if (queryText.contains("4")) {
            queryText = queryText.replaceAll("4", "四");
        }
        if (queryText.contains("5")) {
            queryText = queryText.replaceAll("5", "五");
        }
        if (queryText.contains("6")) {
            queryText = queryText.replaceAll("6", "六");
        }
        if (queryText.contains("7")) {
            queryText = queryText.replaceAll("7", "七");
        }
        if (queryText.contains("8")) {
            queryText = queryText.replaceAll("8", "八");
        }
        if (queryText.contains("9")) {
            queryText = queryText.replaceAll("9", "九");
        }
        return queryText;
    }

    /**
     * 处理中文数字为阿拉伯数字
     *
     * @param queryText
     * @return
     */
    public static String convertChineseNum2Num(String queryText) {
        if (queryText.contains("零")) {
            queryText = queryText.replaceAll("零", "0");
        }
        if (queryText.contains("一")) {
            queryText = queryText.replaceAll("一", "1");
        }
        if (queryText.contains("二")) {
            queryText = queryText.replaceAll("二", "2");
        }
        if (queryText.contains("三")) {
            queryText = queryText.replaceAll("三", "3");
        }
        if (queryText.contains("四")) {
            queryText = queryText.replaceAll("四", "4");
        }
        if (queryText.contains("五")) {
            queryText = queryText.replaceAll("五", "5");
        }
        if (queryText.contains("六")) {
            queryText = queryText.replaceAll("六", "6");
        }
        if (queryText.contains("七")) {
            queryText = queryText.replaceAll("七", "7");
        }
        if (queryText.contains("八")) {
            queryText = queryText.replaceAll("八", "8");
        }
        if (queryText.contains("九")) {
            queryText = queryText.replaceAll("九", "9");
        }
        return queryText;
    }

    /**
     * 将用户说的户号后6位和数字区分
     *
     * @param str
     * @return
     */
    public static Map<String, String> isNumeric(String str) {
        HashMap<String, String> resMap = new HashMap<>();
//        String str = "love234csdn3423java";


        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            if (!Character.isDigit(c)) {//非数字

            } else {//数字
                String xm = str.substring(0, i);
                String hh = str.substring(i);

                resMap.put("xm", xm);
                resMap.put("hh", hh);
                break;
            }
        }
        return resMap;

    }

    /**
     * 提取数字
     *
     * @param str
     * @return 户号
     */
    public static String getSz(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String hh = m.replaceAll("").trim();
        return hh;
    }

    /**
     * 提取汉字
     *
     * @param str
     * @return
     */
    public static String getHz(String str) {
        String reg = "[^(\\u4e00-\\u9fa5)]";
        str = str.replaceAll(reg, "");
        return str;
    }


    public static void main(String[] args) {
        String s = "二八三三零";
        String s0 = "111025";
        final boolean b = NumberUtil.validChineseNum(s);
        System.out.println("validChineseNum:" + b);

        final String s1 = NumberUtil.convertNum2ChineseNum(s0);
        System.out.println("convertNum2ChineseNum:" + s1);

        final String s3 = NumberUtil.convertChineseNum2Num(s1);
        System.out.println("convertChineseNum2Num:" + s3);
        String s2 = NumberUtil.int2chineseNum(111025);
        System.out.println("int2chineseNum:" + s2);


        System.out.println("-----------------");
//        String testTxt = "张文涛卡夏552095523";
        String testTxt = "张夏";
//        final String[] split = testTxt.split(null);
        final char[] chars = testTxt.toCharArray();
        final String s00 = NumberUtil.convertNum2ChineseNum(testTxt);
        System.out.println(s00);
        final String s11 = NumberUtil.convertChineseNum2Num(testTxt);
        System.out.println(s11);

        System.out.println(NumberUtil.isNumeric(s11));


    }


}
