package nara.metro.da.mongo.document.metro;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import nara.metro.domain.entity.Metro;
import nara.share.domain.granule.AdminList;

@Document(collection = "MT_METRO")
public class MetroDoc {
    //
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String memo;
    private long pavilionSequence;
    private long citizenSequence;
    private AdminList admins;
    private long time;

    @Version
    private Long entityVersion;

    public MetroDoc() {
        //
    }

    public static MetroDoc toDocument(Metro metro) {
        //
        MetroDoc doc = new MetroDoc();
        doc.setId(metro.getId());
        doc.setName(metro.getName());
        doc.setEntityVersion(metro.getEntityVersion());
        BeanUtils.copyProperties(metro, doc);
        return doc;
    }

    public static List<Metro> toDomains(List<MetroDoc> metroDocuments) {
        //
        return metroDocuments.stream()
                .filter(Objects::nonNull)
                .map(MetroDoc::toDomain)
                .collect(Collectors.toList());
    }

    public Metro toDomain() {
        //
        Metro metro = new Metro(id);
        metro.setName(name);
        metro.setEntityVersion(entityVersion);
        BeanUtils.copyProperties(this, metro);
        return metro;
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

	public AdminList getAdmins() {
		return admins;
	}

	public void setAdmins(AdminList admins) {
		this.admins = admins;
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
