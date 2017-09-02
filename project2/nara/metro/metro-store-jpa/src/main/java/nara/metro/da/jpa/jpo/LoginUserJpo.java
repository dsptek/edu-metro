package nara.metro.da.jpa.jpo;

import nara.metro.domain.entity.LoginUser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "MT_LOGIN_USER")
@Table(indexes = {
        @Index(name = "IU_LOGINUSER_METROID_EMAIL", unique = true, columnList = "metroId,email")
})
public class LoginUserJpo {
    //
    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private long passwordChangedTime;
    private String metroId;

    private Long entityVersion;

    public LoginUserJpo() {
        //
    }

    public static LoginUserJpo toJpo(LoginUser loginUser) {
        //
        LoginUserJpo loginUserJpo = new LoginUserJpo();
        loginUserJpo.setId(loginUser.getId());
        loginUserJpo.setUsername(loginUser.getUsername());
        loginUserJpo.setEmail(loginUser.getEmail());
        loginUserJpo.setPassword(loginUser.getPassword());
        loginUserJpo.setPasswordChangedTime(loginUser.getPasswordChangedTime());
        loginUserJpo.setMetroId(loginUser.getMetroId());
        loginUserJpo.setEntityVersion(loginUser.getEntityVersion());
        return loginUserJpo;
    }

    public LoginUser toDomain() {
        //
        LoginUser loginUser = new LoginUser(id);
        loginUser.setUsername(username);
        loginUser.setEmail(email);
        loginUser.setPassword(password);
        loginUser.setPasswordChangedTime(passwordChangedTime);
        loginUser.setMetroId(metroId);
        loginUser.setEntityVersion(entityVersion);
        return loginUser;
    }

    public static List<LoginUser> toDomains(List<LoginUserJpo> loginUserJpos) {
        //
        return loginUserJpos.stream()
                .map(jpo -> jpo.toDomain())
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Long entityVersion) {
        this.entityVersion = entityVersion;
    }
}
