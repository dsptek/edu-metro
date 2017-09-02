package nara.metro.da.mybatis;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.share.domain.granule.Name;
import nara.share.exception.store.AlreadyExistsException;
import nara.share.exception.store.NonExistenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@MybatisTest
@Import(CitizenMybatisStore.class)
public class CitizenMybatisStoreTest {
    //
    @Autowired
    private CitizenMybatisStore citizenStore;

    private static final String METRO_ID = "testMetro";

    private static final Name CITIZEN1_NAME  = new Name(Locale.US, "Seongyeong", "Han");
    private static final String CITIZEN1_EMAIL = "syhan@nextree.co.kr";

    private static final Name   CITIZEN2_NAME  = new Name(Locale.US, "Taegook", "Song");
    private static final String CITIZEN2_EMAIL = "tsong@nextree.co.kr";

    private String citizenId1;
    private String citizenId2;

    @Before
    public void setUp() {
        // create citizen
        Citizen citizen = new Citizen(METRO_ID, 1, CITIZEN1_NAME, CITIZEN1_EMAIL);
        citizenId1 = citizenStore.create(citizen);
        System.out.println("[TEST INFO] citizen create => ID : " + citizenId1);

        citizen = new Citizen(METRO_ID, 2, CITIZEN2_NAME, CITIZEN2_EMAIL);
        citizen.setUsername("tsong");
        citizenId2 = citizenStore.create(citizen);
        System.out.println("[TEST INFO] citizen create => ID : " + citizenId2);
    }

    @Test
    public void testCreate() {
        // Test duplication in the same metro with email.
        try {
            citizenStore.create(new Citizen(METRO_ID, 3, CITIZEN1_NAME, CITIZEN1_EMAIL));
            Assert.assertTrue(false);
        } catch (AlreadyExistsException e) {
            Assert.assertTrue(true);
        }

        // Test duplication in the same metro with username if citizen has username.
        try {
            Citizen newCitizen = new Citizen(METRO_ID, 3, new Name(Locale.US, "Tae", "Song"), "hong@nextree.co.kr");
            newCitizen.setUsername("tsong");
            citizenStore.create(newCitizen);
            Assert.assertTrue(false);
        } catch (AlreadyExistsException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testRetrieve() {
        //
        Citizen citizen = citizenStore.retrieve(citizenId1);
        Assert.assertEquals(METRO_ID, citizen.getMetroId());
        Assert.assertEquals(CITIZEN1_NAME.toJson(), citizen.getName().toJson());
        System.out.println("[TEST INFO] " + citizen.toString());

        // not exist ID retrieve
        try {
            citizenStore.retrieve("None");
            Assert.assertTrue(false);
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testRetrieveByName() {
        //
        List<Citizen> citizens = citizenStore.retrieveByName(METRO_ID, "Seongyeong Han");
        Assert.assertEquals(1, citizens.size());
        Assert.assertEquals(CITIZEN1_NAME.toJson(), citizens.get(0).getName().toJson());
        System.out.println("[TEST INFO] " + citizens.get(0).toString());

        Assert.assertEquals(0, citizenStore.retrieveByName("None", "Seongyeong Han").size());
        Assert.assertEquals(0, citizenStore.retrieveByName(METRO_ID, "None").size());
    }

    @Test
    public void testRetrieveByEmail() {
        //
        List<Citizen> citizens = citizenStore.retrieveByEmail(CITIZEN2_EMAIL);
        Assert.assertEquals(1, citizens.size());
        Assert.assertEquals(CITIZEN2_NAME.toJson(), citizens.get(0).getName().toJson());
        System.out.println("[TEST INFO] " + citizens.get(0).toString());

        Assert.assertEquals(0, citizenStore.retrieveByEmail("None").size());
    }

    @Test
    public void testRetrieveByMetro() {
        //
        for (int i = 3; i < 20; i++) {
            Citizen citizen = new Citizen(METRO_ID, i, CITIZEN1_NAME, "test" + i + "@nextree.co.kr");
            citizenStore.create(citizen);
        }

        List<Citizen> citizens = citizenStore.retrieveByMetro(METRO_ID, 8, 5);
        for (Citizen citizen : citizens) {
            System.out.println(citizen);
        }
        Assert.assertEquals(5, citizens.size());
        Assert.assertEquals(9L, citizens.get(0).getSequence());
        Assert.assertEquals(13L, citizens.get(4).getSequence());
    }

    @Test
    public void testRetrieveByMetroUsername() {
        //
        Citizen citizen = citizenStore.retrieveByMetroUsername(METRO_ID, "tsong");
        Assert.assertEquals(METRO_ID, citizen.getMetroId());
        Assert.assertEquals(CITIZEN2_NAME.toJson(), citizen.getName().toJson());
        System.out.println("[TEST INFO] " + citizen.toString());

        // not exist metro ID retrieve
        Assert.assertNull(citizenStore.retrieveByMetroUsername("None", "tsong"));

        // not exist username retrieve
        Assert.assertNull(citizenStore.retrieveByMetroUsername(METRO_ID, "None"));
    }

    @Test
    public void testRetrieveByMetroEmail() {
        //
        Citizen citizen = citizenStore.retrieveByMetroEmail(METRO_ID, CITIZEN2_EMAIL);
        Assert.assertEquals(METRO_ID, citizen.getMetroId());
        Assert.assertEquals(CITIZEN2_NAME.toJson(), citizen.getName().toJson());
        System.out.println("[TEST INFO] " + citizen.toString());

        // not exist metro ID retrieve
        Assert.assertNull(citizenStore.retrieveByMetroEmail("None", CITIZEN1_EMAIL));

        // not exist email retrieve
        Assert.assertNull(citizenStore.retrieveByMetroEmail(METRO_ID, "None"));
    }

    @Test
    public void testUpdate() {
        //
        Name nameChanged = new Name(Locale.US, "Tae-gook", "Song");

        // update
        Citizen citizen = citizenStore.retrieveByMetroEmail(METRO_ID, CITIZEN2_EMAIL);
        System.out.println("[TEST INFO] before update => " + citizen.toString());
        citizen.setName(nameChanged);
        citizenStore.update(citizen);

        // retrieve
        citizen = citizenStore.retrieveByMetroEmail(METRO_ID, CITIZEN2_EMAIL);
        Assert.assertEquals(nameChanged.getDisplayName(), citizen.getName().getDisplayName());
        System.out.println("[TEST INFO] after update => " + citizen.toString());

        // not exist citizen update
        try {
            citizenStore.update(Citizen.getSample());
            Assert.assertTrue(false);
        } catch (NonExistenceException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDelete() {
        //
        Citizen citizen = citizenStore.retrieve(citizenId1);
        citizenStore.delete(citizen);

        // not exist citizen retrieve
        try {
            citizenStore.retrieve(citizenId1);
            Assert.assertTrue(false);
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDisqualifiedCitizen() {
        //
        Citizen citizen1 = citizenStore.retrieve(citizenId1);
        Citizen citizen2 = citizenStore.retrieve(citizenId2);

        citizenStore.createDisqualified(new DisqualifiedCitizen(citizen1));
        citizenStore.createDisqualified(new DisqualifiedCitizen(citizen2));

        DisqualifiedCitizen disqualifiedCitizen1 = citizenStore.retrieveDisqualified(citizenId1);
        Assert.assertEquals(METRO_ID, disqualifiedCitizen1.getMetroId());
        Assert.assertEquals(citizen1.getName().toJson(), disqualifiedCitizen1.getName().toJson());

        List<DisqualifiedCitizen> disqualifiedCitizens = citizenStore.retrieveDisqualifiedByMetro(METRO_ID);
        Assert.assertEquals(2, disqualifiedCitizens.size());

        citizenStore.delete(disqualifiedCitizens.get(0));
        disqualifiedCitizens = citizenStore.retrieveDisqualifiedByMetro(METRO_ID);
        Assert.assertEquals(1, disqualifiedCitizens.size());
    }
}
