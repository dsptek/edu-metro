package nara.metro.da.jpa.jpo;

import nara.metro.domain.entity.MetroBook;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "MT_METRO_BOOK")
public class MetroBookJpo {
    //
    @Id
    private String id;

    private long sequence;

    public MetroBookJpo() {
        //
    }

    public MetroBookJpo(String id) {
        //
        this.id = id;
    }

    public static MetroBookJpo toJpo(MetroBook metroBook) {
        //
        MetroBookJpo metroBookJpo = new MetroBookJpo();
        metroBookJpo.setId(metroBook.getId());
        metroBookJpo.setSequence(metroBook.getSequence());
        return metroBookJpo;
    }

    public MetroBook toDomain() {
        //
        MetroBook metroBook = new MetroBook(id);
        metroBook.setSequence(sequence);
        return metroBook;
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
}
