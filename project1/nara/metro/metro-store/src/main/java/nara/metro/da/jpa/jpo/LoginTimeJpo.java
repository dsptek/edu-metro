package nara.metro.da.jpa.jpo;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nara.metro.domain.entity.LoginTime;
import nara.share.util.json.JsonUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "MT_LOGIN_TIME")
public class LoginTimeJpo {
    //
    @Id
    private String id;

    private String citizenId;
    private String timeJson; // ZonedDateTime

    static final Gson gson = Converters.registerZonedDateTime(new GsonBuilder()).create();

    public LoginTimeJpo() {
        //
    }

    public static LoginTimeJpo toJpo(LoginTime loginTime) {
        //
        LoginTimeJpo loginTimeJpo = new LoginTimeJpo();
        loginTimeJpo.setId(loginTime.getId());
        loginTimeJpo.setCitizenId(loginTime.getCitizenId());
        // FIXME move converter to JsonUtil for converting ZonedDateTime.
        //loginTimeJpo.setTimeJson(JsonUtil.toJson(loginTime.getTime()));
        loginTimeJpo.setTimeJson(gson.toJson(loginTime.getTime()));
        return loginTimeJpo;
    }

    public LoginTime toDomain() {
        //
        LoginTime loginTime = new LoginTime(id);
        loginTime.setCitizenId(citizenId);
        //loginTime.setTime(JsonUtil.fromJson(timeJson, ZonedDateTime.class));
        loginTime.setTime(gson.fromJson(timeJson, ZonedDateTime.class));
        return loginTime;
    }

    public static List<LoginTime> toDomains(List<LoginTimeJpo> loginTimeJpos) {
        //
        return loginTimeJpos.stream()
                .map(jpo -> jpo.toDomain())
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getTimeJson() {
        return timeJson;
    }

    public void setTimeJson(String timeJson) {
        this.timeJson = timeJson;
    }
}
