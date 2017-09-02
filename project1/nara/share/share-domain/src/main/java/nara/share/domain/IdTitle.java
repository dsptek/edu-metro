package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.Objects;
import java.util.StringTokenizer;

public class IdTitle implements ValueObject {
    //
    private String id;
    private String title;

    public IdTitle() {
        //
    }

    public IdTitle(String id, String title) {
        //
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        //
        return id + ":" + title;
    }

    public static IdTitle fromString(String idTitleStr) {
        //
        StringTokenizer tokenizer = new StringTokenizer(idTitleStr, ":");
        String id = tokenizer.nextToken();
        String title = tokenizer.nextToken();

        return new IdTitle(id, title);
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static IdTitle fromJson(String json) {
        //
        return JsonUtil.fromJson(json, IdTitle.class);
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

        IdTitle idTitle = (IdTitle) target;

        return Objects.equals(id, idTitle.id);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        //
        this.title = title;
    }
}