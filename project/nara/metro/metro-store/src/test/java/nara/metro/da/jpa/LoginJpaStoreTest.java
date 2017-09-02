package nara.metro.da.jpa;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;
import nara.share.domain.granule.Name;
import nara.share.exception.store.AlreadyExistsException;
import nara.share.exception.store.NonExistenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.rmi.runtime.Log;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MetroStoreTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LoginJpaStoreTest {
    //
    @Autowired
    private LoginJpaStore loginStore;

    private static final String METRO_ID = "testMetro";
    private static final String CITIZEN1_EMAIL = "syhan@nextree.co.kr";
    private static final String CITIZEN2_EMAIL = "tsong@nextree.co.kr";
    private static final String LOGINUSER1_PASS = "1234";
    private static final String LOGINUSER2_PASS = "5678";

    private String citizenId1;
    private String citizenId2;

    @Before
    public void setUp() {
        //
        Citizen citizen1 = new Citizen(METRO_ID, 1, new Name(Locale.US, "Seongyeong", "Han"), CITIZEN1_EMAIL);
        citizenId1 = citizen1.getId();
        LoginUser loginUser1 = new LoginUser(citizen1, LOGINUSER1_PASS);
        loginStore.create(loginUser1);
        System.out.println("[TEST INFO] login user create => ID : " + citizenId1);

        Citizen citizen2 = new Citizen(METRO_ID, 2, new Name(Locale.US, "Taegook", "Song"), CITIZEN2_EMAIL);
        citizen2.setUsername("tsong");
        citizenId2 = citizen2.getId();
        LoginUser loginUser2 = new LoginUser(citizen2, LOGINUSER2_PASS);
        loginStore.create(loginUser2);
        System.out.println("[TEST INFO] login user create => ID : " + citizenId2);
    }

    @Test
    public void testCreate() {
        // Test duplication in the same metro with email.
        try {
            Citizen citizen = new Citizen(METRO_ID, 3, new Name(Locale.US, "Seongyeong", "Han"), "syhan@nextree.co.kr");
            LoginUser newUser = new LoginUser(citizen, "1234");
            loginStore.create(newUser);
            Assert.assertTrue(false);
        } catch (AlreadyExistsException e) {
            Assert.assertTrue(true);
        }

        // Test duplication in the same metro with username if login user has username.
        try {
            Citizen newCitizen = new Citizen(METRO_ID, 3, new Name(Locale.US, "Tae", "Song"), "hong@nextree.co.kr");
            newCitizen.setUsername("tsong");
            LoginUser newUser = new LoginUser(newCitizen, "5678");
            loginStore.create(newUser);
            Assert.assertTrue(false);
        } catch (AlreadyExistsException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testRetrieve() {
        //
        LoginUser loginUser = loginStore.retrieve(citizenId1);
        Assert.assertEquals(METRO_ID, loginUser.getMetroId());
        Assert.assertEquals(CITIZEN1_EMAIL, loginUser.getEmail());
        Assert.assertEquals(LOGINUSER1_PASS, loginUser.getPassword());

        // not exist ID retrieve
        try {
            loginStore.retrieve("None");
            Assert.assertTrue(false);
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testRetrieveByMetroIdAndEmail() {
        //
        LoginUser loginUser = loginStore.retrieveByMetroIdAndEmail(METRO_ID, CITIZEN2_EMAIL);
        Assert.assertEquals(METRO_ID, loginUser.getMetroId());
        Assert.assertEquals(CITIZEN2_EMAIL, loginUser.getEmail());
        Assert.assertEquals(LOGINUSER2_PASS, loginUser.getPassword());
        System.out.println("[TEST INFO] " + loginUser.toString());

        // Not exists case
        Assert.assertNull(loginStore.retrieveByMetroIdAndEmail("None", CITIZEN2_EMAIL));
        Assert.assertNull(loginStore.retrieveByMetroIdAndEmail(METRO_ID, "None"));
    }

    @Test
    public void testRetrieveByMetroIdAndUsername() {
        //
        LoginUser loginUser = loginStore.retrieveByMetroIdAndUserName(METRO_ID, "tsong");
        Assert.assertEquals(METRO_ID, loginUser.getMetroId());
        Assert.assertEquals(CITIZEN2_EMAIL, loginUser.getEmail());
        Assert.assertEquals(LOGINUSER2_PASS, loginUser.getPassword());
        Assert.assertEquals("tsong", loginUser.getUsername());

        // Not exists case
        Assert.assertNull(loginStore.retrieveByMetroIdAndUserName("None", "tsong"));
        Assert.assertNull(loginStore.retrieveByMetroIdAndUserName(METRO_ID, "None"));
    }

    @Test
    public void testRetrieveByMetroId() {
        //
        for (int i = 3; i < 20; i++) {
            Citizen citizen = new Citizen(METRO_ID, i, new Name(Locale.US, "Seongyeong", "Han"), "test" + i + "@nextree.co.kr");
            LoginUser loginUser = new LoginUser(citizen, "1111");
            loginStore.create(loginUser);
        }

        List<LoginUser> users = loginStore.retrieveByMetroId(METRO_ID, 8, 5);
        for (LoginUser loginUser : users) {
            System.out.println("[TEST INFO] " + loginUser.toString());
        }
        Assert.assertEquals(5, users.size());
    }

    @Test
    public void testUpdate() {
        //
        LoginUser loginUser = loginStore.retrieveByMetroIdAndEmail(METRO_ID, CITIZEN2_EMAIL);
        loginUser.setPassword("9999");
        loginStore.update(loginUser);

        loginUser = loginStore.retrieveByMetroIdAndEmail(METRO_ID, CITIZEN2_EMAIL);
        Assert.assertEquals("9999", loginUser.getPassword());

        // in the case of not exists.
        try {
            loginStore.update(LoginUser.getSample());
            Assert.assertTrue(false);
        } catch (NonExistenceException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDelete() {
        //
        LoginUser loginUser = loginStore.retrieve(citizenId1);
        loginStore.delete(loginUser);

        try {
            loginStore.retrieve(citizenId1);
            Assert.assertTrue(false);
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testLoginTime() {
        //
        List<LoginTime> inputs = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            LoginTime loginTime = new LoginTime("CITIZEN", ZonedDateTime.now());
            inputs.add(loginTime);
            loginStore.createLoginTime(loginTime);
        }

        List<LoginTime> times = loginStore.retrieveLoginTimeByCitizenId("CITIZEN", 0, 10);
        Assert.assertEquals(5, times.size());
        for (LoginTime loginTime : times) {
            System.out.println(loginTime);
        }

        // delete
        loginStore.deleteLoginTime(inputs.get(0).getId());
        times = loginStore.retrieveLoginTimeByCitizenId("CITIZEN", 0, 10);
        Assert.assertEquals(4, times.size());

        // delete by citizen
        loginStore.deleteLoginTimesByCitizenId("CITIZEN");
        times = loginStore.retrieveLoginTimeByCitizenId("CITIZEN", 0, 10);
        Assert.assertEquals(0, times.size());
    }

}
