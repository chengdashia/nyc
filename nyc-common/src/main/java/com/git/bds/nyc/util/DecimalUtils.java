package com.git.bds.nyc.util;

import java.math.BigDecimal;

/**
 * @author 成大事
 * @since 2022/8/12 10:52
 * BigDecimal运算工具类
 */
public class DecimalUtils {
    /**
     * 加法计算（result = x + y）
     *
     * @param x 被加数（可为null）
     * @param y 加数 （可为null）
     * @return 和 （可为null）
     * @author dengcs
     */
    public static BigDecimal add(BigDecimal x, BigDecimal y) {
        if (x == null) {
            return y;
        }
        if (y == null) {
            return x;
        }
        return x.add(y);
    }

    /**
     * 加法计算（result = a + b + c + d）
     *
     * @param a 被加数（可为null）
     * @param b 加数（可为null）
     * @param c 加数（可为null）
     * @param d 加数（可为null）
     * @return BigDecimal （可为null）
     * @author dengcs
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d) {
        BigDecimal ab = add(a, b);
        BigDecimal cd = add(c, d);
        return add(ab, cd);
    }

    /**
     * 累加计算(result=x + result)
     *
     * @param x      被加数（可为null）
     * @param result 和 （可为null,若被加数不为为null，result默认值为0）
     * @return result 和 （可为null）
     * @author dengcs
     */
    public static BigDecimal accumulate(BigDecimal x, BigDecimal result) {
        if (x == null) {
            return result;
        }
        if (result == null) {
            result = new BigDecimal("0");
        }
        return result.add(x);
    }

    /**
     * 减法计算(result = x - y)
     *
     * @param x 被减数（可为null）
     * @param y 减数（可为null）
     * @return BigDecimal 差 （可为null）
     * @author dengcs
     */
    public static BigDecimal subtract(BigDecimal x, BigDecimal y) {
        if (x == null || y == null) {
            return null;
        }
        return x.subtract(y);
    }

    /**
     * 乘法计算(result = x × y)
     *
     * @param x 乘数(可为null)
     * @param y 乘数(可为null)
     * @return BigDecimal 积
     * @author dengcs
     */
    public static BigDecimal multiply(BigDecimal x, BigDecimal y) {
        if (x == null || y == null) {
            return null;
        }
        return x.multiply(y);
    }

    /**
     * 除法计算(result = x ÷ y)
     *
     * @param x 被除数（可为null）
     * @param y 除数（可为null）
     * @return 商 （可为null,四舍五入，默认保留20位小数）
     * @author dengcs
     */
    public static BigDecimal divide(BigDecimal x, BigDecimal y) {
        if (x == null || y == null || y.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        // 结果为0.000..时，不用科学计数法展示
        return stripTrailingZeros(x.divide(y, 20, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 转为字符串(防止返回可续计数法表达式)
     *
     * @param x 要转字符串的小数
     * @return String
     * @author dengcs
     */
    public static String toPlainString(BigDecimal x) {
        if (x == null) {
            return null;
        }
        return x.toPlainString();
    }

    /**
     * 保留小数位数
     *
     * @param x     目标小数
     * @param scale 要保留小数位数
     * @return BigDecimal 结果四舍五入
     * @author dengcs
     */
    public static BigDecimal scale(BigDecimal x, int scale) {
        if (x == null) {
            return null;
        }
        return x.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 整型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author dengcs
     */
    public static BigDecimal toBigDecimal(Integer x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x.toString());
    }

    /**
     * 长整型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author dengcs
     */
    public static BigDecimal toBigDecimal(Long x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x.toString());
    }

    /**
     * 双精度型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author dengcs
     */
    public static BigDecimal toBigDecimal(Double x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x.toString());
    }

    /**
     * 单精度型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author dengcs
     */
    public static BigDecimal toBigDecimal(Float x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x.toString());
    }

    /**
     * 字符串型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author dengcs
     */
    public static BigDecimal toBigDecimal(String x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x);
    }

    /**
     * 对象类型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author dengcs
     */
    public static BigDecimal toBigDecimal(Object x) {
        if (x == null) {
            return null;
        }
        BigDecimal result = null;
        try {
            result = new BigDecimal(x.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 倍数计算，用于单位换算
     *
     * @param x        目标数(可为null)
     * @param multiple 倍数 (可为null)
     * @return BigDecimal (可为null)
     * @author dengcs
     */
    public static BigDecimal multiple(BigDecimal x, Integer multiple) {
        if (x == null || multiple == null) {
            return null;
        }
        return DecimalUtils.multiply(x, toBigDecimal(multiple));
    }

    /**
     * 去除小数点后的0（如: 输入1.000返回1）
     *
     * @param x 目标数(可为null)
     * @return BigDecimal
     */
    public static BigDecimal stripTrailingZeros(BigDecimal x) {
        if (x == null) {
            return null;
        }
        return x.stripTrailingZeros();
    }

    /**
     * 将BigDecimal 转成人民币 并格式化
     * @param bigDecimal  金额
     * @return  人民币表述
     */
    public static String bigDecimalToLocalStr(BigDecimal bigDecimal) {
        String[] chinese = new String[]{"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万"};
        String[] numChinese = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] afterChinese = new String[]{"角", "分"};
        String str = String.valueOf(bigDecimal);
        String[] arr = str.split("\\.");
        char[] chars = arr[0].toCharArray();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < chars.length; ++i) {
            sb.append(numChinese[Integer.parseInt(String.valueOf(chars[i]))]).append(chinese[chars.length - i - 1]);
        }

        if (arr.length == 1) {
            return sb.toString() + "元整";
        } else if (arr[1].length() > 2) {
            throw new IllegalArgumentException("人民币大写转换BigDecimal只能保留2位小数");
        } else {
            sb.append("元");
            char[] chars1 = arr[1].toCharArray();

            for(int i = 0; i < chars1.length; ++i) {
                sb.append(numChinese[Integer.parseInt(String.valueOf(chars1[i]))]).append(afterChinese[i]);
            }

            return sb.toString();
        }
    }

}
