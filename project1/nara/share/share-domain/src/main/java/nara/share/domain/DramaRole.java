package nara.share.domain;

public class DramaRole {
    //
    private String name;
    private RoleLevel level;

    public DramaRole() {

    }

    public DramaRole(String name) {
        //
        this.name = name;
        this.level = RoleLevel.Normal;
    }

    public DramaRole(String name, RoleLevel level) {
        //
        this.name = name;
        this.level = level;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DramaRole{");
        sb.append("name='").append(name).append('\'');
        sb.append(", level=").append(level);
        sb.append('}');
        return sb.toString();
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
}
