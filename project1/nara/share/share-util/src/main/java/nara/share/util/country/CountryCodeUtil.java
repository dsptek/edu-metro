package nara.share.util.country;

import nara.share.exception.NaraException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CountryCodeUtil {
    //
    private static List<String> isoCountries = Arrays.asList(Locale.getISOCountries());

    /**
     * 
     * @param countryCode ISO 3166 alpha-2 code.
     * @return
     */
    public static boolean isValidCountryCode(String countryCode) {
        //
        if (countryCode == null) throw new NaraException("Country code is null.");
        if (countryCode.length() != 2) throw new NaraException("Country code must be ISO 3166 alpha-2 code.");
        return isoCountries.contains(countryCode);
    }

}
