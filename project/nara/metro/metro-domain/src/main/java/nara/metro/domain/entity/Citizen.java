package nara.metro.domain.entity;


import nara.share.domain.*;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.Phone;
import nara.share.util.json.JsonUtil;

public class Citizen extends Entity implements Aggregate {
    //
    private long sequence;
    private Name name;
    private String username;
    private String email;
    private String phone;           // optional
    private boolean guest;
    private long time;
    private boolean disqualified;   // 자격 상실

    private String metroId;

    public Citizen() {
        //
    }

    public Citizen(String id) {
        //
        super(id);
    }

    public Citizen(String metroId, long sequence, Name name, String email) {
        //
        super(String.format("%d@%s", sequence, metroId));
        this.sequence = sequence;
        this.name = name;
        this.username = "";
        this.email = email;
        this.phone = phone;
        this.disqualified = false;
        this.time = System.currentTimeMillis();
        this.metroId = metroId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Citizen{");
        sb.append("sequence=").append(sequence);
        sb.append(", name=").append(name);
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", guest=").append(guest);
        sb.append(", time=").append(time);
        sb.append(", disqualified=").append(disqualified);
        sb.append(", metroId='").append(metroId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Citizen getSample() {
        //
        Metro metro = Metro.getSample();
        long sequence = metro.nextCitizenSequence();
        Name name = Name.getSample();
        String email = Email.getSample().getEmail();

        Citizen sample = new Citizen(metro.getId(), sequence, name, email);
        sample.setUsername("doitfriend");
        sample.setPhone(Phone.getSample().getDisplayNumber());

        return sample;
    }

    public void setValues(NameValueList nameValues) {
        //
        for(NameValue nameValue : nameValues.getList()) {
            String value = nameValue.getValue();
            switch(nameValue.getName()) {
                case "name":        this.name = Name.fromJson(value); break;
                case "username":    this.username = value; break;
                case "email":       this.email = value; break;
                case "phone":       this.phone = value; break;
                case "guest":       this.guest = Boolean.valueOf(value); break;
                case "disqualified": this.disqualified = Boolean.valueOf(value); break;
            }
        }
    }

    public IdName getIdName() {
        //
        return new IdName(getId(), getName().getDisplayName());
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static Citizen fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Citizen.class);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public boolean isGuest() {
        return guest;
    }

    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    public boolean isDisqualified() {
        return disqualified;
    }

    public void setDisqualified(boolean disqualified) {
        this.disqualified = disqualified;
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
