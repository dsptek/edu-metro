package nara.share.domain.granule;

import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

import java.util.Locale;

public class LocaledName implements ValueObject {
    //
    private String langCode;        // iso 639, ko, en, etc
    private String familyName;
    private String middleName;
    private String givenName;

    public LocaledName(String familyName, String givenName) {
        //
        this(Locale.getDefault(), familyName, givenName);
    }

    public LocaledName(Locale langLocale, String familyName, String givenName) {
        //
        this.langCode = langLocale.getLanguage();
        this.familyName = familyName;
        this.givenName = givenName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocaledName{");
        sb.append("langCode='").append(langCode).append('\'');
        sb.append(", familyName='").append(familyName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", givenName='").append(givenName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static LocaledName getSample() {
        //
        Locale langLocale = Locale.getDefault();
        String familyName = "김";
        String givenName = "철수";

        LocaledName sample = new LocaledName(langLocale, familyName, givenName);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static LocaledName fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LocaledName.class);
    }

    public String getDisplayName() {
        //
        if(langCode.equals("ko")) {
            return String.format("%s%s", familyName, givenName);
        } else {
            return String.format("%s %s", givenName, familyName);
        }
    }

    public String getFullName() {
        //
        if (this.middleName == null || middleName.equals("")) {
            return getDisplayName();
        }

        return String.format("%s %s %s", givenName, middleName, familyName);
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}