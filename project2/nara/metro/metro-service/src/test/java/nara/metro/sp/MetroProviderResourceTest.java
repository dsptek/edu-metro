package nara.metro.sp;

import nara.metro.AbstractMetroApplicationTests;
import nara.metro.domain.entity.Metro;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MetroProviderResourceTest extends AbstractMetroApplicationTests {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        //
        Metro metro = metroProviderRestAdapter().findMetro(getSampleMetroId());
        Assert.assertNotNull(metro);

        logger.debug("{}", metro);

        List<Metro> metros = metroProviderRestAdapter().findMetros();
        Assert.assertEquals(1, metros.size());

        metro = metroProviderRestAdapter().findMetroByName(metros.get(0).getName());
        Assert.assertNotNull(metro);

        logger.debug("{}", metro);
    }

}
