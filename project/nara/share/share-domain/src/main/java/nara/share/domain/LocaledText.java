package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.Locale;

public class LocaledText implements ValueObject {
    //
    private Locale locale;
    private String text;

    public LocaledText(){}

    public LocaledText(Locale locale, String text) {
        //
        this.locale = locale;
        this.text = text;
    }

    public LocaledText(String text) {
        //
        this(getSystemLocale(), text);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocaledTexts{");
        sb.append("locale=").append(locale);
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static LocaledText getSample() {
        //
        Locale baseLocale = Locale.ENGLISH;
        String text = "Sample localed text";

        LocaledText sample = new LocaledText(baseLocale, text);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static LocaledText fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LocaledText.class);
    }

    public String getTextOf(Locale locale) {
        //
        String resultText = null;

        if (this.locale.equals(locale)) {
            resultText = text;
        }

        return resultText;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private static Locale getSystemLocale() {
        //
        return new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().getTextOf(getSystemLocale()));
        System.out.println(getSystemLocale());
    }
}