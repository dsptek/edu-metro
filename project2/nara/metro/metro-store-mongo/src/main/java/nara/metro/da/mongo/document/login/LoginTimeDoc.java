package nara.metro.da.mongo.document.login;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import nara.metro.domain.entity.LoginTime;

@Document(collection = "MT_LOGIN_TIME")
public class LoginTimeDoc {
	//
	@Id
	private String id;
	
	private String citizenId;
    private ZonedDateTime time;
    
    @Version
    private Long entityVersion;

    public LoginTimeDoc() {
    	//
    }
    
    public static LoginTimeDoc toDocument(LoginTime loginTime) {
    	//
    	LoginTimeDoc doc = new LoginTimeDoc();
    	doc.setId(loginTime.getId());
    	doc.setEntityVersion(loginTime.getEntityVersion());
    	BeanUtils.copyProperties(loginTime, doc);
        return doc;
    }
    
    public static List<LoginTime> toDomains(List<LoginTimeDoc> loginTimeDocuments) {
    	//
    	return loginTimeDocuments.stream()
    			.filter(Objects::nonNull)
    			.map(LoginTimeDoc::toDomain)
    			.collect(Collectors.toList());
    }
    
    public LoginTime toDomain() {
    	//
    	LoginTime loginTime = new LoginTime(id);
    	loginTime.setEntityVersion(entityVersion);
    	BeanUtils.copyProperties(this, loginTime);
        return loginTime;
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

	public ZonedDateTime getTime() {
		return time;
	}

	public void setTime(ZonedDateTime time) {
		this.time = time;
	}

	public Long getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(Long entityVersion) {
		this.entityVersion = entityVersion;
	}
    
}
