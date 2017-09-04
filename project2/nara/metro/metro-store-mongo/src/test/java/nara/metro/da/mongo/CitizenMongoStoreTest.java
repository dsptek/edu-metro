package nara.metro.da.mongo;

import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.Metro;
import nara.metro.domain.store.CitizenStore;
import nara.metro.domain.store.MetroStore;
import nara.share.domain.granule.Name;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CitizenMongoStoreTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "nara.metro.da.mongo")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CitizenMongoStoreTest {
	//
	@Autowired
	private CitizenStore citizenStore;

	@Autowired
	private MetroStore metroStore;

	private static final long METRO_SEQUENCE = 1L;
	private static final String METRO_NAME = "testMetro";
	private static final String METRO_MEMO = "testMetro memo";

	private static final String FIRST_NAME1 = "John";
	private static final String FAMILY_NAME1 = "Doe";
	private static final String EMAIL1 = "john@acompany.com";

	private static final String FIRST_NAME2 = "Jane";
	private static final String FAMILY_NAME2 = "Roe";
	private static final String EMAIL2 = "jane@bcompany.com";

	private static final Name NAME1 = new Name(Locale.KOREA, FIRST_NAME1, FAMILY_NAME1);
	private static final Name NAME2 = new Name(Locale.KOREA, FIRST_NAME2, FAMILY_NAME2);

	private String metroId;
	private String citizenId1;
	private String citizenId2;

	@Before
	public void setUp() {
		//
		Metro metro = new Metro(METRO_SEQUENCE, METRO_NAME, METRO_MEMO);
		metroId = metroStore.create(metro);

		metro = metroStore.nextCitizenSequenceMetro(metroId);
		long citizenSequence = metro.getCitizenSequence();

		Citizen citizen;
		citizen = new Citizen(metroId, citizenSequence, NAME1, EMAIL1);
		citizenId1 = citizenStore.create(citizen);
		System.out.println("[TEST INFO] citizen create => ID : " + citizenId1);
		
		metro = metroStore.nextCitizenSequenceMetro(metroId);
		citizenSequence = metro.getCitizenSequence();
		citizen = new Citizen(metroId, citizenSequence, NAME2, EMAIL2);
		citizenId2 = citizenStore.create(citizen);
		System.out.println("[TEST INFO] citizen create => ID : " + citizenId2);
	}

	@Test
	public void testCreate() {
		//
		try {
			Metro metro = metroStore.nextCitizenSequenceMetro(metroId);

			long citizenSequence = metro.getCitizenSequence();
			citizenStore.create(new Citizen(metroId, citizenSequence, NAME1, EMAIL1));
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testRetrieve() {
		//
		Citizen citizen = citizenStore.retrieve(citizenId1);
		Assert.assertEquals(metroId, citizen.getMetroId());
		Assert.assertEquals(NAME1.toJson(), citizen.getName().toJson());
		System.out.println("[TEST INFO] " + citizen.toString());
	}

	@Test
	public void testRetrieveByEmail() {
		//
		Citizen citizen = citizenStore.retrieveByMetroEmail(metroId, EMAIL2);

		Assert.assertEquals(metroId, citizen.getMetroId());
		Assert.assertEquals(NAME2.toJson(), citizen.getName().toJson());
		System.out.println("[TEST INFO] " + citizen.toString());
		// not exist metro ID retrieve
		Assert.assertNull(citizenStore.retrieveByMetroEmail("None", EMAIL1));

		// not exist email retrieve
		Assert.assertNull(citizenStore.retrieveByMetroEmail(metroId, "None"));
	}

	@Test
	public void testRetrieveByName() {
		//
		List<Citizen> citizens = citizenStore.retrieveByName(metroId, NAME1.getDisplayName());

		Assert.assertEquals(1, citizens.size());
		
		Citizen citizen = citizens.get(0);
		Assert.assertEquals(NAME1.toJson(), citizen.getName().toJson());
		System.out.println("[TEST INFO] " + citizen.toString());
	}
	
}
