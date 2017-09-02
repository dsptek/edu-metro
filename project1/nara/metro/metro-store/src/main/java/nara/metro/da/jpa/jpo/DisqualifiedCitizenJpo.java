package nara.metro.da.jpa.jpo;

import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.share.domain.granule.Name;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "MT_DISQUALIFIED_CITIZEN")
public class DisqualifiedCitizenJpo {
    //
    @Id
    private String id;

    private long sequence;
    private String nameJson;
    private String displayName;
    private String email;
    private String phone;
    private boolean guest;
    private long time;
    private long disqualifiedTime;
    private String metroId;

    private Long entityVersion;

    public DisqualifiedCitizenJpo() {
        //
    }

    public static DisqualifiedCitizenJpo toJpo(DisqualifiedCitizen citizen) {
        //
        DisqualifiedCitizenJpo jpo = new DisqualifiedCitizenJpo();
        jpo.setId(citizen.getId());
        jpo.setSequence(citizen.getSequence());
        jpo.setNameJson(citizen.getName().toJson());
        jpo.setDisplayName(citizen.getName().getDisplayName());
        jpo.setEmail(citizen.getEmail());
        jpo.setPhone(citizen.getPhone());
        jpo.setGuest(citizen.isGuest());
        jpo.setTime(citizen.getTime());
        jpo.setDisqualifiedTime(citizen.getDisqualifiedTime());
        jpo.setMetroId(citizen.getMetroId());
        jpo.setEntityVersion(citizen.getEntityVersion());
        return jpo;
    }

    public DisqualifiedCitizen toDomain() {
        //
        DisqualifiedCitizen citizen = new DisqualifiedCitizen(id);
        citizen.setSequence(sequence);
        citizen.setName(Name.fromJson(nameJson));
        citizen.setEmail(email);
        citizen.setPhone(phone);
        citizen.setGuest(guest);
        citizen.setTime(time);
        citizen.setDisqualifiedTime(disqualifiedTime);
        citizen.setMetroId(metroId);
        citizen.setEntityVersion(entityVersion);
        return citizen;
    }

    public static List<DisqualifiedCitizen> toDomains(List<DisqualifiedCitizenJpo> citizenJpos) {
        //
        return citizenJpos.stream()
                .map(jpo -> jpo.toDomain())
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public String getNameJson() {
        return nameJson;
    }

    public void setNameJson(String nameJson) {
        this.nameJson = nameJson;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public Long getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Long entityVersion) {
        this.entityVersion = entityVersion;
    }
}
