package nara.metro.da.jpa.jpo;

import nara.metro.domain.entity.Citizen;
import nara.share.domain.granule.Name;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "MT_CITIZEN")
@Table(indexes = {
        @Index(name = "IU_CITIZEN_METROID_EMAIL", unique = true, columnList = "metroId,email")
})
public class CitizenJpo {
    //
    @Id
    private String id;

    private long sequence;
    private String nameJson;
    private String displayName;
    private String username;
    private String email;
    private String phone;
    private boolean guest;
    private long time;
    private boolean disqualified;
    private String metroId;

    private Long entityVersion;

    public CitizenJpo() {
        //
    }

    public static CitizenJpo toJpo(Citizen citizen) {
        //
        CitizenJpo citizenJpo = new CitizenJpo();
        citizenJpo.setId(citizen.getId());
        citizenJpo.setSequence(citizen.getSequence());
        citizenJpo.setNameJson(citizen.getName().toJson());
        citizenJpo.setDisplayName(citizen.getName().getDisplayName());
        citizenJpo.setUsername(citizen.getUsername());
        citizenJpo.setEmail(citizen.getEmail());
        citizenJpo.setPhone(citizen.getPhone());
        citizenJpo.setGuest(citizen.isGuest());
        citizenJpo.setTime(citizen.getTime());
        citizenJpo.setDisqualified(citizen.isDisqualified());
        citizenJpo.setMetroId(citizen.getMetroId());
        citizenJpo.setEntityVersion(citizen.getEntityVersion());
        return citizenJpo;
    }

    public Citizen toDomain() {
        //
        Citizen citizen = new Citizen(id);
        citizen.setSequence(sequence);
        citizen.setName(Name.fromJson(nameJson));
        citizen.setUsername(username);
        citizen.setEmail(email);
        citizen.setPhone(phone);
        citizen.setGuest(guest);
        citizen.setTime(time);
        citizen.setDisqualified(disqualified);
        citizen.setMetroId(metroId);
        citizen.setEntityVersion(entityVersion);
        return citizen;
    }

    public static List<Citizen> toDomains(List<CitizenJpo> citizenJpos) {
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

    public Long getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Long entityVersion) {
        this.entityVersion = entityVersion;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
