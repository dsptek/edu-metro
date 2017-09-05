package nara.metro.sp;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nara.metro.AbstractMetroApplicationTests;
import nara.metro.domain.entity.LoginUser;

public class LoginServiceResourceTest extends AbstractMetroApplicationTests {

	//
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Test
    public void test() {
    	//
    	LoginUser foundLoginUser = loginServiceRestAdapter().findLoginUser(getSampleCitizenId());
    	if (foundLoginUser == null) {
    		Assert.fail();
    	}
    	
    	List<LoginUser> foundLoginUsers = loginServiceRestAdapter().findLoginUserByMetro(getSampleMetroId(), 0, 10);
    	if (foundLoginUsers.size() != 1) {
    		Assert.fail();
    	}    	
    }
    
}
