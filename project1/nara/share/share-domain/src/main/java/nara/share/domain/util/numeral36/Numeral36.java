/*******************************************************************************
 * Copyright(c) 2015-2020 Incheon International Airport Corporation.
 * All rights reserved. This software is the proprietary information of
 * Incheon International Airport Corporation.
 *******************************************************************************/
package nara.share.domain.util.numeral36;

/**
 * 36진수 유틸
 *
 * @author <a href="mailto:hyunohkim@nextree.co.kr">Kim, Hyunoh</a>
 * @since 2015. 7. 29.
 */
public class Numeral36 {
    //
    private static final int Decimal36 = 36;

    private Numeral36() {
        //
    }

    public static Numeral36 getInstance() {
        //
        return new Numeral36();
    }

    public String getNextStr36(int int36) {
        //
        return getStr36(++int36);
    }

    public String getNextStr36(String str36) {
        //
        if (str36.length() == 1 && str36.equals("Z")) {
            throw new RuntimeException("Overflow, Z is the last char in one byte value -> " + str36);
        }

        long longValue = this.getLong36(str36);
        longValue++;
        return getStr36(longValue);
    }

    public long getLong36(String str36) {
        //
        if (str36 == null || str36.equals("")) {
            throw new RuntimeException("Minus digit is not valid -> " + str36);
        }

        long resultInt = 0;
        int currentIndex = 0;
        int remainCount = 0;
        char currentChar = '0';
        int intValue = 0;

        while (currentIndex < str36.length()) {
            //
            currentChar = str36.charAt(currentIndex);
            if (currentChar < 'A') {
                intValue = (currentChar - '0');
            } else {
                intValue = (currentChar + 10 - 'A');
            }

            currentIndex++;
            remainCount = str36.length() - currentIndex;

            if (remainCount == 0) {
                resultInt += intValue;
            } else {
                resultInt += (intValue * (Math.pow(36, remainCount)));
            }
        }

        return resultInt;
    }

    public String getStr36(long int36, int formatLength) {
        //
        return format(getStr36(int36), formatLength);
    }

    public String getStr36(long int36) {
        //
        String resultStr = "";

        if (int36 < 0) {
            throw new RuntimeException("Minus digit is not valid -> " + int36);
        }

        if (int36 < 10) {
            resultStr += (char) (int36 + '0');
        } else {

            long remain = 0;
            char charDigit = '0';

            while (int36 > 0) {
                remain = int36 % Decimal36;
                if (remain < 10) {
                    charDigit = (char) (remain + '0');
                } else {
                    charDigit = (char) (remain - 10 + 'A');
                }
                resultStr = charDigit + resultStr;
                int36 /= Decimal36;
            }
        }
        return resultStr;
    }

    private String format(String str36, int formatLength) {
        //
        int fillCount = formatLength - str36.length();
        if (fillCount < 0) {
            throw new RuntimeException("Formatted lenght is smaller than str36 length:" + str36);
        }

        if (fillCount == 0) {
            return str36;
        }

        StringBuilder strBuilder = new StringBuilder(formatLength);
        for (int i = 0; i < fillCount; i++) {
            strBuilder.append("0");
        }

        strBuilder.append(str36);

        return strBuilder.toString();
    }

}
