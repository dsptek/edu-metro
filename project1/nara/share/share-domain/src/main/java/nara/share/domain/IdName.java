package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.Objects;
import java.util.StringTokenizer;

public class IdName implements ValueObject {
    //
    private String id;
    private String name;

    public IdName() {
        //
    }

    public IdName(String id, String name) {
        //
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        //
        return id + ":" + name;
    }

    public static IdName fromString(String idNameStr) {
        //
        StringTokenizer tokenizer = new StringTokenizer(idNameStr, ":");
        String id = tokenizer.nextToken();
        String name = tokenizer.nextToken();

        return new IdName(id, name);
    }

    public static IdName getSample() {
        //
        String id = "1234";
        String name = "Hansoo Lee";

        return new IdName(id, name);
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static IdName fromJson(String json) {
        //
        return JsonUtil.fromJson(json, IdName.class);
    }

    @Override
    public boolean equals(Object target) {
        //
        if (this == target) {
            return true;
        }

        if (target == null || getClass() != target.getClass()) {
            return false;
        }

        IdName idName = (IdName) target;

        return Objects.equals(id, idName.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //
        this.name = name;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(IdName.fromString(getSample().toString()));
    }
}