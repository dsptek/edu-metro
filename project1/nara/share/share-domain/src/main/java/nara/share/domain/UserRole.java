package nara.share.domain;

import nara.share.util.json.JsonUtil;

public class UserRole {
    //
    private String name;
    private RoleLevel level;

    public UserRole() {
        //
    }

    public UserRole(String name) {
        //
        this(name, RoleLevel.Normal);
    }

    public UserRole(String name, RoleLevel level) {
        //
        this.name = name;
        this.level = level;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRole{");
        sb.append("name='").append(name).append('\'');
        sb.append(", level=").append(level);
        sb.append('}');
        return sb.toString();
    }

    public UserRole clone() {
        //
        return new UserRole(name, level);
    }

    public static UserRole getSample() {
        //
        return new UserRole("Instructor", RoleLevel.Admin);
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static UserRole fromJson(String json) {
        //
        return JsonUtil.fromJson(json, UserRole.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleLevel getLevel() {
        return level;
    }

    public void setLevel(RoleLevel level) {
        this.level = level;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}