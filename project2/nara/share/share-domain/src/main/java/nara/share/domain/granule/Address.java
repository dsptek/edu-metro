package nara.share.domain.granule;

import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

import java.util.Locale;

public class Address implements ValueObject {
    //
    private Locale langLocale;

    private Style style;
    private String tag;               // example: Home 1, Home 2 <-- Category
    private Category category;

    private String zipCode;
    private String zipAddress;

    private String city;
    private String state;

    private String street;              // non-zip addresss, or street address
    private String country;

    private String phoneNumber;         // optional

    public Address() {
        //
    }

    public Address(Style style, Category category, String zipCode, String country) {
        //
        switch(style) {
            case Korean: langLocale = Locale.KOREA; break;
            case US: langLocale = Locale.US; break;
        }
        this.style = style;
        this.zipCode = zipCode;
        this.country = country;
        this.category = category;
        this.tag = category.name();
    }

    public static Address forKorean(Category category, String zipCode, String zipAddress, String street, String country) {
        //
        Address address = new Address(Style.Korean, category, zipCode, country);
        address.setZipAddress(zipAddress);
        address.setStreet(street);

        return address;
    }

    public static Address forUS(Category category, String zipCode, String street, String city, String state, String country) {
        //
        Address address = new Address(Style.US, category, zipCode, country);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);

        return address;
    }

    @Override
    public String toString() {
        //
        final StringBuilder builder = new StringBuilder("Address{");

        builder.append("langLocale=").append(langLocale.getDisplayLanguage());
        builder.append(", style=").append(style);
        builder.append(", tag='").append(tag);
        builder.append(", category=").append(category);
        builder.append(", zipCode='").append(zipCode);
        builder.append(", zipAddress='").append(zipAddress);
        builder.append(", city='").append(city);
        builder.append(", state='").append(state);
        builder.append(", street='").append(street);
        builder.append(", country='").append(country);
        builder.append(", phoneNumber='").append(phoneNumber);
        builder.append('}');

        return builder.toString();
    }

    public static Address getUSSample() {
        //
        Category category = Category.Office;
        String zipCode = "06889";
        String street = "12 Jhones st.";
        String city = "fairfield";
        String state = "CT";
        String country = "U.S.A";

        Address sample = Address.forUS(category, zipCode, street, city, state,  country);

        return sample;
    }

    public static Address getKoreanSample() {
        //
        Category category = Category.Office;
        String zipCode = "12345";
        String zipAddress = "서울시 금천구 디지털1로 155번지 잼잼빌딩";
        String street = "703호";
        String country = "대한민국";

        Address sample = Address.forKorean(category, zipCode, zipAddress, street, country);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static Address fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Address.class);
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Locale getLangLocale() {
        return langLocale;
    }

    public void setLangLocale(Locale langLocale) {
        this.langLocale = langLocale;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipAddress() {
        return zipAddress;
    }

    public void setZipAddress(String zipAddress) {
        this.zipAddress = zipAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public enum Style {
        US,
        Korean
    }

    public enum Category {
        Office,
        Home,
        Others
    }

    public static void main(String[] args) {
        //
        System.out.println(getKoreanSample());
        System.out.println(getUSSample().toJson());
        System.out.println(fromJson(getUSSample().toJson()));
    }
}