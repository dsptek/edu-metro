package nara.metro.domain.entity;


import nara.share.domain.Entity;
import nara.share.domain.granule.Name;
import nara.share.util.json.JsonUtil;

public class DisqualifiedCitizen extends Entity {
    // immutable object
    //
    private long sequence;
    private Name name;
    private String email;
    private String phone;           // optional
    private boolean guest;
    private long time;
    private long disqualifiedTime;
    private String metroId;

    transient private Citizen citizen;

    public DisqualifiedCitizen() {
        // 
    }

    public DisqualifiedCitizen(String id) {
        //
        super(id);
    }

    public DisqualifiedCitizen(Citizen citizen) {
        //
        super(citizen.getId());
        this.sequence = citizen.getSequence();
        this.name = citizen.getName();
        this.email = citizen.getEmail();
        this.phone = citizen.getPhone();
        this.guest = citizen.isGuest();
        this.time = citizen.getTime();
        this.disqualifiedTime = System.currentTimeMillis();
        this.metroId = citizen.getMetroId();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DisqualifiedCitizen{");
        sb.append("sequence=").append(sequence);
        sb.append(", name=").append(name);
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", guest=").append(guest);
        sb.append(", time=").append(time);
        sb.append(", disqualifiedTime=").append(disqualifiedTime);
        sb.append(", metroId='").append(metroId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static DisqualifiedCitizen getSample() {
        //
        Citizen citizen = Citizen.getSample();

        DisqualifiedCitizen sample = new DisqualifiedCitizen(citizen);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static DisqualifiedCitizen fromJson(String json) {
        //
        return JsonUtil.fromJson(json, DisqualifiedCitizen.class);
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGuest() {
        return guest;
    }

    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getDisqualifiedTime() {
        return disqualifiedTime;
    }

    public void setDisqualifiedTime(long disqualifiedTime) {
        this.disqualifiedTime = disqualifiedTime;
    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
