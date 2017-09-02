package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocaledTexts implements ValueObject {
    //
    private Locale locale;
    private String text;

    private List<LocaledText> childTextList;

    public LocaledTexts() {}
    public LocaledTexts(Locale locale, String text) {
        //
        this.locale = locale;
        this.text = text;
        this.childTextList = new ArrayList<>();
    }

    public LocaledTexts(String text) {
        //
        this(getSystemLocale(), text);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocaledTexts{");
        sb.append("locale=").append(locale);
        sb.append(", text='").append(text).append('\'');
        sb.append(", childTextList=").append(childTextList);
        sb.append('}');
        return sb.toString();
    }

    public static LocaledTexts getSample() {
        //
        Locale baseLocale = Locale.ENGLISH;
        String text = "Sample localed text";

        LocaledTexts sample = new LocaledTexts(baseLocale, text);
        sample.getChildTextList().add(new LocaledText(Locale.KOREA, "예제 로케일 텍스트"));

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static LocaledTexts fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LocaledTexts.class);
    }

    public String getTextOf(Locale locale) {
        //
        String resultText = null;
        if (locale == null) return resultText;
        if (locale.equals(this.locale)) {
            resultText = text;
        } else {
            if(childTextList != null) {
                for (LocaledText childText : childTextList) {
                    if (childText.getLocale().equals(locale)) {
                        resultText = childText.getText();
                        break;
                    }
                }
            }
        }

        return resultText;
    }

    public boolean isLocaled() {
        //
        if (childTextList != null && childTextList.size() >= 1) {
            return true;
        }

        return false;
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

    public List<LocaledText> getChildTextList() {
        return childTextList;
    }

    public void setChildTextList(List<LocaledText> childTextList) {
        this.childTextList = childTextList;
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