package nara.share.domain;

import java.util.Locale;

abstract public class LocaledValueObject implements ValueObject {
    //
    private Locale locale;

    protected LocaledValueObject(){
        //
    }

    protected LocaledValueObject(Locale locale) {
        //
        this.locale = locale;
    }

    @Override
    public String toString() {
        //
        return "locale:" + locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
