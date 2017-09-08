package nara.metro.da.mongo.document.metro;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import nara.metro.domain.entity.MetroBook;

@Document(collection = "MT_METRO_BOOK")
public class MetroBookDoc {
    //
    @Id
    private String id;

    private long sequence;

    @Version
    private Long entityVersion;

    public MetroBookDoc() {
        //
    }
    
    public MetroBookDoc(String metroId) {
        //
    	super();
    	this.id = metroId;
    }

    public static MetroBookDoc toDocument(MetroBook metroBook) {
        //
        MetroBookDoc doc = new MetroBookDoc();
        doc.setId(metroBook.getId());
        doc.setSequence(metroBook.getSequence());
        doc.setEntityVersion(metroBook.getEntityVersion());
        return doc;
    }

    public static List<MetroBook> toDomains(List<MetroBookDoc> metroBookDocuments) {
        //
        return metroBookDocuments.stream()
                .filter(Objects::nonNull)
                .map(MetroBookDoc::toDomain)
                .collect(Collectors.toList());
    }

    public MetroBook toDomain() {
        //
    	MetroBook metro = new MetroBook(id);
        metro.setSequence(sequence);
        metro.setEntityVersion(entityVersion);
        return metro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public Long getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Long entityVersion) {
        this.entityVersion = entityVersion;
    }

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
}
