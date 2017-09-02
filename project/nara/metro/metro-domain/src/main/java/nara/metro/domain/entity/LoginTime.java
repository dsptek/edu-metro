package nara.metro.domain.entity;


import nara.share.domain.Entity;
import nara.share.util.json.JsonUtil;

import java.time.ZonedDateTime;

public class LoginTime extends Entity {
    // immutable object
    //
    private String citizenId;
    private ZonedDateTime time;

    public LoginTime() {
        //
    }

    public LoginTime(String citizenId, ZonedDateTime time) {
        //
        super();
        this.citizenId = citizenId;
        this.time = time;
    }

    public LoginTime(String id) {
        //
        super(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginTime{");
        sb.append("citizenId='").append(citizenId).append('\'');
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }

    public static LoginTime getSample() {
        //
        String citizenId = Citizen.getSample().getId();
        ZonedDateTime time = ZonedDateTime.now();

        LoginTime sample = new LoginTime(citizenId, time);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static LoginTime fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LoginTime.class);
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
