package nara.share.domain.granule;

import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

import java.util.Locale;

public class LocaledAddress implements ValueObject {
    //
    private String tag;             // home, office, etc
    private String langCode;        // ko, en
    private String displayName;     // Steve Jobs
    private String country;         // Korea (rep. of)
    private String state;           // 경상북도, 서울시 province, region
    private String city;            // 봉화군, 서울시
    private String street;          // 금천구 가산디지털 1로, 186, 제이플라츠
    private String zipCode;         // 123902
    private String phoneNumber;     // +82 2-0292-0908

    public LocaledAddress(Locale locale, String country, String state, String city, String street, String zipCode, String phoneNumber) {
        //
        this.langCode = locale.getLanguage();
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocaledAddress{");
        sb.append("tag='").append(tag).append('\'');
        sb.append(", langCode='").append(langCode).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static LocaledAddress getUSSample() {
        //
        Locale locale = Locale.US;
        String street = "12 Jhones st.";
        String city = "fairfield";
        String state = "CT";
        String zipCode = "06889";
        String country = "U.S.A";
        String phoneNumber = "202-0909-2939";

        LocaledAddress sample = new LocaledAddress(locale, country, state, city, street, zipCode, phoneNumber);
        sample.setTag("home");

        return sample;
    }

    public static LocaledAddress getKoreanSample() {
        //
        Locale locale = Locale.KOREAN;
        String street = "금천구 가산디지털 1로 186, 제이플라츠 702";
        String city = "서울시";
        String state = "";
        String zipCode = "12345";
        String country = "대한민국";
        String phoneNumber = "+82-10-0909-9898";

        LocaledAddress sample = new LocaledAddress(locale, country, state, city, street, zipCode, phoneNumber);
        sample.setTag("office");

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static LocaledAddress fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LocaledAddress.class);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static void main(String[] args) {
        //
        System.out.println(getKoreanSample());
        System.out.println(getUSSample().toJson());
        System.out.println(fromJson(getUSSample().toJson()));
    }
}