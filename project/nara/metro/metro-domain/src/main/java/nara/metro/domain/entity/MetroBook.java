package nara.metro.domain.entity;


import nara.share.domain.Entity;
import nara.share.domain.LongPair;
import nara.share.util.json.JsonUtil;

public class MetroBook extends Entity {
    //
    private long sequence;

    public MetroBook() {
        //
        super();
        this.sequence = 0;
    }

    public MetroBook(String id) {
        //
        super(id);
        this.sequence = 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetroBook{");
        sb.append("sequence=").append(sequence);
        sb.append('}');
        return sb.toString();
    }

    public static MetroBook getSample() {
        //
        return new MetroBook();
    }

    public long nextSequence() {
        //
        return ++sequence;
    }

    public LongPair nextVolumeSequence(int volume) {
        //
        long start = ++sequence;
        long end = sequence += volume;

        return new LongPair(start, end);
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static MetroBook fromJson(String json) {
        //
        return JsonUtil.fromJson(json, MetroBook.class);
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public void increaseSequence(int volume) {
        //
        this.sequence += (long)volume;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }

}