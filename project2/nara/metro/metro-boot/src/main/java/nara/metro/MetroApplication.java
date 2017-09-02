package nara.metro;

import nara.metro.domain.spec.CitizenProvider;
import nara.metro.domain.spec.MetroProvider;
import nara.metro.domain.spec.sdo.CitizenCdo;
import nara.metro.domain.spec.sdo.MetroCdo;
import nara.share.domain.granule.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Locale;

@SpringBootApplication
public class MetroApplication {
	//
	public static void main(String[] args) {
		//
		SpringApplication.run(MetroApplication.class, args);
	}

	@Autowired
	@Qualifier("metroLogic")
	private MetroProvider metroProvider;

	@Autowired
	@Qualifier("citizenLogic")
	private CitizenProvider citizenProvider;

	// initial data setup
	@PostConstruct
	public void initialize() {
		//
		MetroCdo metroCdo = new MetroCdo("Nextree", "Nextree Metro");
		String metroId = metroProvider.buildMetro(metroCdo);

		citizenProvider.registerMetroAdminCitizen(new CitizenCdo(metroId, new Name(Locale.KOREAN, "태국", "송"), "tsong@nextree.co.kr"));
		citizenProvider.registerCitizen(new CitizenCdo(metroId, new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr"));
		citizenProvider.registerCitizen(new CitizenCdo(metroId, new Name(Locale.KOREAN, "형구", "강"), "hkkang@nextree.co.kr"));
		citizenProvider.registerCitizen(new CitizenCdo(metroId, new Name(Locale.KOREAN, "지수", "서"), "jsseo@nextree.co.kr"));
		citizenProvider.registerCitizen(new CitizenCdo(metroId, new Name(Locale.KOREAN, "인영", "이"), "iylee@nextree.co.kr"));
	}

}
