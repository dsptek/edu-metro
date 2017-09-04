package nara.metro.da.mongo.document.citizen;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.share.domain.granule.Name;

@Document(collection = "MT_DISQUALIFIED_CITIZEN")
@CompoundIndexes({
        @CompoundIndex(name = "idx_citizen_name",
                unique = true,
                def = "{'metroId' : 1, 'name' : 1}"),
        @CompoundIndex(name = "idx_citizen_email",
                unique = true,
                def = "{'metroId' : 1, 'email' : 1}")
})
public class DisqualifiedCitizenDoc {
    //
    @Id
    private String id;
    private long sequence;
    
    private Name name;
    private String email;
    private String phone;
    
    private boolean guest;
    private long time;
    private boolean disqualifiedTime;

    private String metroId;
    
    @Version
    private Long entityVersion;

    public DisqualifiedCitizenDoc() {

    }

    public static DisqualifiedCitizenDoc toDocument(DisqualifiedCitizen citizen) {
        //
        DisqualifiedCitizenDoc citizenDoc = new DisqualifiedCitizenDoc();
        citizenDoc.setId(citizen.getId());
        citizenDoc.setEntityVersion(citizen.getEntityVersion());
        BeanUtils.copyProperties(citizen, citizenDoc);
        return citizenDoc;
    }

    public static List<DisqualifiedCitizen> toDomains(List<DisqualifiedCitizenDoc> citizenDocs) {
        return citizenDocs.stream()
                .map( doc -> doc.toDomain() )
                .collect(Collectors.toList());
    }

    public DisqualifiedCitizen toDomain() {
        //
    	DisqualifiedCitizen citizen = new DisqualifiedCitizen(id);
        citizen.setEntityVersion(entityVersion);
        BeanUtils.copyProperties(this, citizen);
        return citizen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public void setEntityVersion(Long entityVersion) {
        this.entityVersion = entityVersion;
    }

	public boolean isDisqualifiedTime() {
		return disqualifiedTime;
	}

	public void setDisqualifiedTime(boolean disqualifiedTime) {
		this.disqualifiedTime = disqualifiedTime;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
