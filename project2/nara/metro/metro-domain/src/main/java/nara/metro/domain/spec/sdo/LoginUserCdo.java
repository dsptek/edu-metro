package nara.metro.domain.spec.sdo;

import nara.metro.domain.entity.Citizen;
import nara.share.util.json.JsonUtil;

import java.io.Serializable;

public class LoginUserCdo  implements Serializable {
    //
    private String username;
    private String password;

    public LoginUserCdo() {
        //
    }

    public LoginUserCdo(String username, String password) {
        //
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginUserCdo{");
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static LoginUserCdo getSample() {
        //
        Citizen citizen = Citizen.getSample();
        String username = "doitfriend";
        String password = "1234";

        LoginUserCdo sample = new LoginUserCdo(username, password);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static LoginUserCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LoginUserCdo.class);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}