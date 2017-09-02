package nara.metro.sp.spring.web;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.spec.CitizenProvider;
import nara.metro.domain.spec.sdo.CitizenCdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/p")
public class CitizenProviderResource implements CitizenProvider {
    //
    @Autowired
    @Qualifier("citizenLogic")
    private CitizenProvider citizenProvider;

    @Override
    @PostMapping("citizens")
    public String registerCitizen(@RequestBody CitizenCdo citizenCdo) {
        //
        return citizenProvider.registerCitizen(citizenCdo);
    }

    @Override
    @PostMapping("citizens/admin")
    public String registerMetroAdminCitizen(@RequestBody CitizenCdo citizenCdo) {
        //
        return citizenProvider.registerMetroAdminCitizen(citizenCdo);
    }

    @Override
    @GetMapping("citizens/{citizenId}")
    public Citizen findCitizen(@PathVariable String citizenId) {
        //
        return citizenProvider.findCitizen(citizenId);
    }

    @Override
    @GetMapping(value = "citizens", params = "email")
    public List<Citizen> findCitizenByEmail(@RequestParam String email) {
        //
        return citizenProvider.findCitizenByEmail(email);
    }

    @Override
    @GetMapping(value = "citizens", params = {"metroId", "email"})
    public Citizen findCitizenByMetroEmail(@RequestParam String metroId, @RequestParam String email) {
        //
        return citizenProvider.findCitizenByMetroEmail(metroId, email);
    }

    @Override
    @GetMapping(value = "citizens", params = {"metroId", "offset", "limit"})
    public List<Citizen> findCitizenByMetro(@RequestParam String metroId, @RequestParam int offset, @RequestParam int limit) {
        //
        return citizenProvider.findCitizenByMetro(metroId, offset, limit);
    }

    @Override
    @PutMapping("citizens/{citizenId}/disqualification")
    public String disqualifyCitizen(@PathVariable String citizenId) {
        //
        return citizenProvider.disqualifyCitizen(citizenId);
    }

    @Override
    @GetMapping(value = "citizens/exists", params = {"metroId", "email"})
    public boolean existsCitizenEmail(@RequestParam String metroId, @RequestParam String email) {
        //
        return citizenProvider.existsCitizenEmail(metroId, email);
    }

}
