package nara.metro.sp;

import nara.metro.AbstractMetroApplicationTests;
import nara.metro.domain.entity.Metro;
import nara.share.domain.NameValueList;
import nara.share.domain.granule.Admin;
import nara.share.domain.granule.AdminList;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MetroServiceResourceTest extends AbstractMetroApplicationTests {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void blankTest() {
    	//
    }
    
//    @Test
    public void test() {
        //
        Metro metro = metroServiceRestAdapter().findMetro(getSampleMetroId());
        Assert.assertNotNull(metro);

        logger.debug("{}", metro);

        Assert.assertTrue(metroServiceRestAdapter().existMetroByName(getSampleMetroCdo().getName()));

        List<Metro> metros = metroServiceRestAdapter().findMetros();
        Assert.assertEquals(1, metros.size());

        String modifiedName = "Nara";
        String modifiedMemo = "Nara Platform";

        NameValueList nameValues = new NameValueList();
        nameValues.add("name", modifiedName);
        nameValues.add("memo", modifiedMemo);

        AdminList adminList = new AdminList();
        adminList.add(new Admin("kchuh", "허기철"));
        nameValues.add("admins", adminList.toJson());

        metroServiceRestAdapter().modifyMetro(getSampleMetroId(), nameValues);
        metro = metroServiceRestAdapter().findMetro(getSampleMetroId());

        Assert.assertEquals(modifiedName, metro.getName());
        Assert.assertEquals(modifiedMemo, metro.getMemo());

        logger.debug("{}", metro);

        metroServiceRestAdapter().removeMetro(getSampleMetroId());

        try {
            metroServiceRestAdapter().findMetro(getSampleMetroId());
        } catch (Exception e) {
            Assert.assertTrue(true);
        }

    }

}
