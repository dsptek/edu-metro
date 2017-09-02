package nara.metro.domain.entity;


import nara.share.domain.*;
import nara.share.domain.granule.Admin;
import nara.share.domain.granule.AdminList;
import nara.share.util.json.JsonUtil;

public class Metro extends Entity implements Aggregate {
    //
    private String name;
    private String memo;
    private long pavilionSequence;
    private long citizenSequence;
    private AdminList admins;
    private long time;

    public Metro() {
        //
    }

    public Metro(String id) {
        //
        super(id);
    }

    public Metro(long sequence, String name, String memo) {
        //
        super(String.format("M%d", sequence));
        this.name = name;
        this.memo = memo;
        this.pavilionSequence = 0;
        this.citizenSequence = 0;
        this.admins = new AdminList();
        this.time = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Metro{");
        sb.append("name='").append(name).append('\'');
        sb.append(", memo='").append(memo).append('\'');
        sb.append(", pavilionSequence=").append(pavilionSequence);
        sb.append(", citizenSequence=").append(citizenSequence);
        sb.append(", admins=").append(admins);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }

    public static Metro getSample() {
        //
        MetroBook book = MetroBook.getSample();
        String name = "해외사업처";
        String memo = "해외사업처 영업지원시스템";

        Metro sample = new Metro(book.nextSequence(), name, memo);
        sample.getAdmins().add(Admin.getSample());

        return sample;
    }

    public void setValues(NameValueList nameValues) {
        //
        for(NameValue nameValue : nameValues.getList()) {
            String value = nameValue.getValue();
            switch(nameValue.getName()) {
                case "name":        this.name = value; break;
                case "memo":        this.memo = value; break;
                case "admins":      this.admins = AdminList.fromJson(value); break;
            }
        }
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static Metro fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Metro.class);
    }

    public long nextPavilionSequence() {
        //
        return ++pavilionSequence;
    }

    public LongPair nextPavilionVolumeSequence(int volume) {
        //
        long start = ++pavilionSequence;
        long end = pavilionSequence += volume;

        return new LongPair(start, end);
    }

    public long nextCitizenSequence() {
        //
        return ++citizenSequence;
    }

    public LongPair nextCitizenVolumeSequence(int volume) {
        //
        long start = ++citizenSequence;
        long end = citizenSequence += volume;

        return new LongPair(start, end);
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

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
