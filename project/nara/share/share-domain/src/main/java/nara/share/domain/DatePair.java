package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DatePair {
    //
    private String zoneId;
    private String startDate;
    private String endDate;

    public DatePair(){
        //
    }

    public DatePair(ZoneId zoneId, LocalDate startDate) {
        //
        this(zoneId.getId(), startDate, null);
    }

    public DatePair(ZoneId zoneId, LocalDate startDate, LocalDate endDate) {
        //
        this(zoneId.getId(), startDate, endDate);
    }

    public DatePair(String zoneId, LocalDate startDate, LocalDate endDate) {
        //
        this.zoneId = zoneId;
        this.startDate = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        if (endDate == null) {
            this.endDate = null;
        } else {
            this.endDate = endDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DatePair{");
        sb.append("zoneId=").append(zoneId);
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static DatePair getSample() {
        //
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(10L);

        DatePair sample = new DatePair(zoneId, startDate, endDate);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public int getPeriodMonth() {
        //
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        Period period = Period.between(start, end);
        return period.getMonths();
    }

    public static DatePair fromJson(String json) {
        //
        return JsonUtil.fromJson(json, DatePair.class);
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().getPeriodMonth());
    }
}