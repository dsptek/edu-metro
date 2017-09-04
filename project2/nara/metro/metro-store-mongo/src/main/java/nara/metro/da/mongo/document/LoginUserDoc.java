package nara.metro.da.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import nara.metro.domain.entity.LoginUser;

@Document(collection="loginUserDocument")
public class LoginUserDoc {

	@Id
	private String id;
	private Long entityVersion;
	
	private String username;        // login user name
    private String email;
    private String password;
    private long passwordChangedTime;
    private String metroId;
    
    public static LoginUserDoc createDocument(LoginUser loginUser) {
    	LoginUserDoc result = new LoginUserDoc();
    	
    	result.id = loginUser.getId();
    	result.entityVersion = loginUser.getEntityVersion();
    	result.username = loginUser.getUsername();
    	result.email = loginUser.getEmail();
    	result.password = loginUser.getPassword();
    	result.metroId = loginUser.getMetroId();
    	
    	return result;
    }
    
    public LoginUser createDomain() {
    	LoginUser result = new LoginUser(this.id);
    	result.setEntityVersion(this.entityVersion);
    	
    	result.setUsername(this.username);
    	result.setEmail(this.email);
    	result.setPassword(this.password);
    	result.setMetroId(this.metroId);
    	
    	return result;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(Long entityVersion) {
		this.entityVersion = entityVersion;
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
    
}
