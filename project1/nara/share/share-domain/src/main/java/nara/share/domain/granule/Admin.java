package nara.share.domain.granule;


import nara.share.domain.IdName;
import nara.share.domain.Tier;
import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

public class Admin implements ValueObject {
    //
    private Tier tier;
    private String id;
    private String name;
    private long time;

    public Admin() {
        //
    }

    public Admin(IdName idName) {
        //
        this(Tier.Primary, idName.getId(), idName.getName());
    }

    public Admin(String id, String name) {
        //
        this(Tier.Primary, id, name);
    }

    public Admin(Tier tier, String id, String name) {
        //
        this.tier = tier;
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Admin{");
        sb.append("tier=").append(tier);
        sb.append(", id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }

    public static Admin getSample() {
        //
        IdName idName = IdName.getSample();
        Admin sample = new Admin(idName.getId(), idName.getName());

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static Admin fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Admin.class);
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
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
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
