package nara.metro.da.jpa;

import nara.metro.domain.entity.Metro;
import nara.metro.domain.entity.MetroBook;
import nara.share.domain.Tier;
import nara.share.domain.granule.Admin;
import nara.share.domain.granule.AdminList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MetroStoreTestApplication.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MetroJpaStoreTest {
    //
    @Autowired
    private MetroJpaStore metroStore;

    private static final String METRO1_NAME = "metro1";
    private static final String METRO2_NAME = "metro2";

    private String metroId1;
    private String metroId2;

    @Before
    public void setUp() throws Exception {
        //
        Metro metro = new Metro(1L, METRO1_NAME, "metro1 memo");
        metroId1 = metroStore.create(metro);
        System.out.println("[TEST INFO] metro create => ID : " + metroId1);

        metro = new Metro(2L, METRO2_NAME, "metro2 memo");
        metroId2 = metroStore.create(metro);
        System.out.println("[TEST INFO] metro create => ID : " + metroId2);
    }

    @Test
    public void testCreate() throws Exception {
        //
        try {
            //long sequence = metroStore.retrieveNextSequence();
            long sequence = 3L;
            metroStore.create(new Metro(sequence, METRO1_NAME, "memo"));
            Assert.assertTrue(false);
            // 메트로 명은 Unique하도록 인덱스 설정이 되어있어, 같은이름으로 등록 시도할 경우, 예외발생
        } catch (DataIntegrityViolationException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testRetrieve() throws Exception {
        //
        Metro metro = metroStore.retrieve(metroId1);
        Assert.assertEquals(METRO1_NAME, metro.getName());
        System.out.println("[TEST INFO] " + metro.toString());

        // not exist ID retrieve
        try {
            metroStore.retrieve("None");
            Assert.assertTrue(false);
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testNextCitizenSequenceMetro() throws Exception {
        //
        Metro metro = metroStore.retrieve(metroId1);
        Metro metroNextCitizenSequence = metroStore.nextCitizenSequenceMetro(metroId1);
        Assert.assertEquals(1, metroNextCitizenSequence.getCitizenSequence() - metro.getCitizenSequence());
    }

    @Test
    public void testExistByName() throws Exception {
        //
        List<Metro> metros = metroStore.retrieveAll();
        Assert.assertEquals(2, metros.size());

        // metro name existence
        boolean exist;
        exist = metroStore.existByName(METRO1_NAME);
        Assert.assertTrue(exist);
        exist = metroStore.existByName(METRO2_NAME);
        Assert.assertTrue(exist);
        exist = metroStore.existByName("None");
        Assert.assertFalse(exist);
    }

    @Test
    public void testUpdate() throws Exception {
        //
        Metro metro = metroStore.retrieve(metroId1);
        System.out.println("[TEST INFO] 0. Before update => " + metro);
        metroStore.update(metro);

        metro = metroStore.retrieve(metroId1);
        System.out.println("[TEST INFO] 1. After update => " + metro);

        metroStore.update(metro);

        metro = metroStore.retrieve(metroId1);
        System.out.println("[TEST INFO] 2. After update => " + metro);

        // admins update
        AdminList admins = AdminList.getSample();
        metro = metroStore.retrieve(metroId2);
        System.out.println("[TEST INFO] 0. Before update => " + metro);
        metro.setAdmins(admins);
        metroStore.update(metro);

        metro = metroStore.retrieve(metroId2);
        Admin sample = Admin.getSample();
        Assert.assertEquals(sample.getId(), metro.getAdmins().getPrimaryAdmin().getId());
        Assert.assertEquals(Tier.Primary, metro.getAdmins().getPrimaryAdmin().getTier());
        System.out.println("[TEST INFO] 1. After update => " + metro);
    }

    @Test
    public void testDelete() throws Exception {
        //
        Metro metro = metroStore.retrieve(metroId1);
        metroStore.delete(metro);

        Assert.assertEquals(1, metroStore.retrieveAll().size());

        // not exist citizen retrieve
        try {
            metroStore.retrieve(metroId1);
            Assert.assertTrue(false);
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testNextSequenceBook() throws Exception {
        //
        MetroBook metroBook = metroStore.nextSequenceBook();
        Assert.assertTrue(metroBook.getSequence() > 0);

        MetroBook metroVolumeBook = metroStore.nextVolumeSequenceBook(5);
        Assert.assertEquals(5, metroVolumeBook.getSequence() - metroBook.getSequence());
    }
}
