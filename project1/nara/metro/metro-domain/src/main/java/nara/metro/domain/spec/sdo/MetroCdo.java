package nara.metro.domain.spec.sdo;

import nara.share.util.json.JsonUtil;

import java.io.Serializable;

public class MetroCdo implements Serializable {
    //
    private String name;
    private String memo;

    public MetroCdo() {
        //
    }

    public MetroCdo(String name) {
        //
        this.name = name;
    }

    public MetroCdo(String name, String memo) {
        //
        this.name = name;
        this.memo = memo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetroCdo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", memo='").append(memo).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static MetroCdo getSample() {
        //
        return new MetroCdo("Nextree", "Nextree Soft & Nextree Consulting");
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static MetroCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, MetroCdo.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}