/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.
 */
package nara.share.domain.util.numeral36;


/**
 * USID 생성기
 *
 * @author <a href="mailto:hyunohkim@nextree.co.kr">Kim, Hyunoh</a>
 * @since 2015. 7. 29.
 */
public class UsidGen {
    //
    public static String getStr36(String prefix, String seperator, long number, int formatLength) {
        //
        number = (number < 0 ? -number : number);

        Numeral36 numeral36 = Numeral36.getInstance();

        return String.format("%s%s%s",
                prefix, seperator,
                numeral36.getStr36(number, formatLength));
    }

    public static String getStr36(String prefix, long number, int formatLength) {
        //
        number = (number < 0 ? -number : number);

        Numeral36 numeral36 = Numeral36.getInstance();
        return String.format("%s-%s", prefix, numeral36.getStr36(number, formatLength));
    }

    public static String getStr36(long number, int formatLength) {
        //
        number = (number < 0 ? -number : number);

        Numeral36 numeral36 = Numeral36.getInstance();
        return numeral36.getStr36(number, formatLength);
    }
}
