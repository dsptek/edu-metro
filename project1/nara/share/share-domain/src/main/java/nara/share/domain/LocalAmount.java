package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.Currency;

public class LocalAmount {
    //
    private String currencyCode;        // ISO 4217
    private double amount;

    public LocalAmount(){
        //
    }

    public LocalAmount(Currency currency, double amount) {
        //
        this(currency.getCurrencyCode(), amount);
    }

    public LocalAmount(String currencyCode, double amount) {
        //
        this.currencyCode = currencyCode;
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocalAmount{");
        sb.append("currencyCode='").append(currencyCode).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    public static LocalAmount getSample() {
        //
        Currency currency = Currency.getInstance("KRW");
        double amount = 1270000.0;

        LocalAmount sample = new LocalAmount(currency, amount);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static LocalAmount fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LocalAmount.class);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}