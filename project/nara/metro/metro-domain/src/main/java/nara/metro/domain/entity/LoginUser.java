package nara.metro.domain.entity;

import nara.share.domain.Entity;
import nara.share.domain.NameValue;
import nara.share.domain.NameValueList;
import nara.share.util.json.JsonUtil;

public class LoginUser extends Entity {
    //
    private String username;        // login user name
    private String email;
    private String password;
    private long passwordChangedTime;
    private String metroId;

    public LoginUser() {
        //
    }

    public LoginUser(Citizen citizen, String password) {
        //
        super(citizen.getId());
        this.username = citizen.getUsername();
        this.email = citizen.getEmail();
        this.password = password;
        this.passwordChangedTime = System.currentTimeMillis();
        this.metroId = citizen.getMetroId();
    }

    public LoginUser(String id) {
        //
        super(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginUser{");
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", passwordChangedTime=").append(passwordChangedTime);
        sb.append(", metroId='").append(metroId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static LoginUser getSample() {
        //
        Citizen citizen = Citizen.getSample();
        LoginUser sample = new LoginUser(citizen, "1234");

        return sample;
    }

    public void setValues(NameValueList nameValues) {
        //
        for(NameValue nameValue : nameValues.getList()) {
            String value = nameValue.getValue();
            switch(nameValue.getName()) {
                case "password":
                    this.password = value;
                    this.passwordChangedTime = System.currentTimeMillis();
                    break;
                case "email":
                    this.email = value;
                    break;
            }
        }
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static LoginUser fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LoginUser.class);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPasswordChangedTime() {
        return passwordChangedTime;
    }

    public void setPasswordChangedTime(long passwordChangedTime) {
        this.passwordChangedTime = passwordChangedTime;
    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
