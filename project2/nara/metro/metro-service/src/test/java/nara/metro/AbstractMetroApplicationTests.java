package nara.metro;

import java.util.Locale;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nara.metro.adapter.rest.CitizenServiceRestAdapter;
import nara.metro.adapter.rest.MetroProviderRestAdapter;
import nara.metro.adapter.rest.MetroServiceRestAdapter;
import nara.metro.domain.spec.sdo.CitizenCdo;
import nara.metro.domain.spec.sdo.MetroCdo;
import nara.share.domain.granule.Name;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.springweb.SpringWebRestClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MetroTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractMetroApplicationTests {
	//
	private MetroCdo sampleMetroCdo;
	private String sampleMetroId;

	private CitizenCdo sampleCitizenCdo;
	private String sampleCitizenId;

	@LocalServerPort
	private int port;

	@Bean
	public NaraRestClient createRestClient() {
		//
		return new SpringWebRestClient("http://localhost:" + port);
	}

	@Bean
	public MetroServiceRestAdapter metroServiceRestAdapter() {
		//
		return new MetroServiceRestAdapter(createRestClient());
	}

	@Bean
	public MetroProviderRestAdapter metroProviderRestAdapter() {
		//
		return new MetroProviderRestAdapter(createRestClient());
	}

	@Bean
	public CitizenServiceRestAdapter citizenServiceRestAdapter() {
		//
		return new CitizenServiceRestAdapter(createRestClient());
	}

	@Before
	public void setup() throws InterruptedException {
		//
		sampleMetroCdo = new MetroCdo("Nextree", "Nextree Soft & Nextree Consulting");
		sampleMetroId = metroServiceRestAdapter().buildMetro(sampleMetroCdo);

		sampleCitizenCdo = new CitizenCdo(sampleMetroId, new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr");
		sampleCitizenId = citizenServiceRestAdapter().registerCitizen(sampleCitizenCdo);
	}

	public String getSampleMetroId() {
		//
		return sampleMetroId;
	}

	public MetroCdo getSampleMetroCdo() {
		//
		return sampleMetroCdo;
	}
}
