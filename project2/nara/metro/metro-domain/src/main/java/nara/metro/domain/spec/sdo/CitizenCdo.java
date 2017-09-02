package nara.metro.domain.spec.sdo;

import nara.metro.domain.entity.Metro;
import nara.share.domain.annote.Optional;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.util.json.JsonUtil;

import java.io.Serializable;

public class CitizenCdo implements Serializable {
    //
    private String metroId;
    private Name name;
    private String email;

    @Optional
    private String username;

    @Optional
    private String password;

    @Optional
    private String phone;

    public CitizenCdo() {
        //
    }

    public CitizenCdo(String metroId, Name name, String email) {
        //
        this.metroId = metroId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CitizenCdo{");
        sb.append("metroId='").append(metroId).append('\'');
        sb.append(", name=").append(name);
        sb.append(", email='").append(email).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static CitizenCdo getSample() {
        //
        String metroId = Metro.getSample().getId();
        Name name = Name.getSample();
        String email = Email.getSample().getEmail();

        CitizenCdo sample = new CitizenCdo(metroId, name, email);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static CitizenCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, CitizenCdo.class);
    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}