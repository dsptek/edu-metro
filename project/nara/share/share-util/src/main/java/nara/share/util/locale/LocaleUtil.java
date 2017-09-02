package nara.share.util.locale;

import java.util.Arrays;
import java.util.Locale;

public class LocaleUtil extends org.apache.commons.lang3.LocaleUtils {
    //
    public static void main(String[] args) {
        //
        Arrays.asList(Locale.getISOLanguages()).forEach(language -> {
            System.out.println(language);
        });

        LocaleUtil.availableLocaleList().forEach(locale -> {
            System.out.println(locale.getDisplayLanguage());
        });
    }
}
