package nara.metro.da.mongo.document.login;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import nara.metro.domain.entity.LoginUser;

@Document(collection = "MT_LOGIN_USER")
public class LoginUserDoc {
	//
	@Id
	private String id;
	private String username;        // login user name
    private String email;
    private String password;
    private long passwordChangedTime;
    private String metroId;
    
    @Version
    private Long entityVersion;
    
    public LoginUserDoc() {
    	//
    }
    
    public static LoginUserDoc toDocument(LoginUser loginUser) {
    	//
    	LoginUserDoc doc = new LoginUserDoc();
    	doc.setId(loginUser.getId());
    	doc.setEntityVersion(loginUser.getEntityVersion());
    	BeanUtils.copyProperties(loginUser, doc);
        return doc;
    }
    
    public static List<LoginUser> toDomains(List<LoginUserDoc> loginUserDocuments) {
    	//
    	return loginUserDocuments.stream()
    			.filter(Objects::nonNull)
    			.map(LoginUserDoc::toDomain)
    			.collect(Collectors.toList());
    }
    
    public LoginUser toDomain() {
    	//
    	LoginUser loginUser = new LoginUser(id);
    	loginUser.setEntityVersion(entityVersion);
    	BeanUtils.copyProperties(this, loginUser);
        return loginUser;
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
