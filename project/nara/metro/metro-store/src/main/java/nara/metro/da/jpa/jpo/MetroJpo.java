package nara.metro.da.jpa.jpo;

import nara.metro.domain.entity.Metro;
import nara.share.domain.granule.AdminList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "MT_METRO")
public class MetroJpo {
    @Id
    private String id;

    @Column(unique = true)
    private String name;
    private String memo;
    private long pavilionSequence;
    private long citizenSequence;
    private String adminsJson; // AdminList
    private long time;

    private Long entityVersion;

    public MetroJpo() {
        //
    }

    public static MetroJpo toJpo(Metro metro) {
        //
        MetroJpo metroJpo = new MetroJpo();
        metroJpo.setId(metro.getId());
        metroJpo.setEntityVersion(metro.getEntityVersion());
        metroJpo.setName(metro.getName());
        metroJpo.setMemo(metro.getMemo());
        metroJpo.setPavilionSequence(metro.getPavilionSequence());
        metroJpo.setCitizenSequence(metro.getCitizenSequence());
        metroJpo.setAdminsJson(metro.getAdmins().toJson());
        metroJpo.setTime(metro.getTime());
        return metroJpo;
    }

    public Metro toDomain() {
        //
        Metro metro = new Metro(id);
        metro.setEntityVersion(entityVersion);
        metro.setName(name);
        metro.setMemo(memo);
        metro.setPavilionSequence(pavilionSequence);
        metro.setCitizenSequence(citizenSequence);
        metro.setAdmins(AdminList.fromJson(adminsJson));
        metro.setTime(time);
        return metro;
    }

    public static List<Metro> toDomains(List<MetroJpo> metroJpos) {
        //
        return metroJpos.stream()
                .map(jpo -> jpo.toDomain())
                .collect(Collectors.toList());
    }

    public void increasePavilionSequence(int volume) {
        //
        this.pavilionSequence += volume;
    }

    public void increaseCitizenSequence(int volume) {
        //
        this.citizenSequence += volume;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public long getPavilionSequence() {
        return pavilionSequence;
    }

    public void setPavilionSequence(long pavilionSequence) {
        this.pavilionSequence = pavilionSequence;
    }

    public long getCitizenSequence() {
        return citizenSequence;
    }

    public void setCitizenSequence(long citizenSequence) {
        this.citizenSequence = citizenSequence;
    }

    public String getAdminsJson() {
        return adminsJson;
    }

    public void setAdminsJson(String adminsJson) {
        this.adminsJson = adminsJson;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Long getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Long entityVersion) {
        this.entityVersion = entityVersion;
    }


}
