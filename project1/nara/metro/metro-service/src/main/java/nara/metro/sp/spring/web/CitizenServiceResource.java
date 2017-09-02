package nara.metro.sp.spring.web;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.metro.domain.spec.CitizenService;
import nara.metro.domain.spec.sdo.CitizenCdo;
import nara.share.domain.NameValueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/s")
public class CitizenServiceResource implements CitizenService {
    //
    @Autowired
    @Qualifier("citizenLogic")
    private CitizenService citizenService;

    @Override
    @PostMapping("citizens")
    public String registerCitizen(@RequestBody CitizenCdo citizenCdo) {
        //
        return citizenService.registerCitizen(citizenCdo);
    }

    @Override
    @PostMapping("citizens/admin")
    public String registerMetroAdminCitizen(@RequestBody CitizenCdo citizenCdo) {
        //
        return citizenService.registerMetroAdminCitizen(citizenCdo);
    }

    @Override
    @GetMapping("citizens/{citizenId}")
    public Citizen findCitizen(@PathVariable String citizenId) {
        //
        return citizenService.findCitizen(citizenId);
    }

    @Override
    @GetMapping(value = "citizens", params = "email")
    public List<Citizen> findCitizenByEmail(@RequestParam String email) {
        //
        return citizenService.findCitizenByEmail(email);
    }

    @Override
    @GetMapping(value = "citizens", params = {"metroId", "email"})
    public Citizen findCitizenByMetroEmail(@RequestParam String metroId, @RequestParam String email) {
        //
        return citizenService.findCitizenByMetroEmail(metroId, email);
    }

    @Override
    @GetMapping(value = "citizens", params = {"metroId", "offset", "limit"})
    public List<Citizen> findCitizenByMetro(@RequestParam String metroId, @RequestParam int offset, @RequestParam int limit) {
        //
        return citizenService.findCitizenByMetro(metroId, offset, limit);
    }

    @Override
    @PutMapping("citizens/{citizenId}")
    public void modifyCitizen(@PathVariable String citizenId, @RequestBody NameValueList nameValues) {
        //
        citizenService.modifyCitizen(citizenId, nameValues);
    }

    @Override
    @PutMapping("citizens/{citizenId}/disqualification")
    public String disqualifyCitizen(@PathVariable String citizenId) {
        //
        return citizenService.disqualifyCitizen(citizenId);
    }

    @Override
    @GetMapping("disqualified-citizens")
    public List<DisqualifiedCitizen> findDisqualifiedCitizenByMetro(@RequestParam String metroId) {
        //
        return citizenService.findDisqualifiedCitizenByMetro(metroId);
    }

    @Override
    @GetMapping(value = "citizens/exists", params = {"metroId", "email"})
    public boolean existsCitizenEmail(@RequestParam String metroId, @RequestParam String email) {
        //
        return citizenService.existsCitizenEmail(metroId, email);
    }

    @Override
    @GetMapping(value = "citizens/exists", params = {"metroId", "username"})
    public boolean existsCitizenUsername(@RequestParam String metroId, @RequestParam String username) {
        //
        return citizenService.existsCitizenUsername(metroId, username);
    }
}
