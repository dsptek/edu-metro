//package nara.town.domain.service;
//
//import Citizen;
//import CitizenLogic;
//import MetroLogic;
//import MetroProxyLycler;
//import TownMockProxyLycler;
//import CitizenService;
//import CitizenCdo;
//import CitizenUdo;
//import MetroService;
//import MetroCdo;
//import TownStore;
//import nara.town.domain.store.mapstore.TownStoreMapLycler;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class CitizenServiceTest {
//    //
//    private MetroService metroService;
//    private CitizenService citizenService;
//
//    private TownStore townStore;
//    private String metroId;
//    private String metroName = "Nextree Blog";
//
//    @Before
//    public void setUp() throws Exception {
//        //
//        TownStoreMapLycler townStoreCycler = new TownStoreMapLycler();
//        MetroProxyLycler townProxyLycler = new TownMockProxyLycler();
//
//        this.metroService = new MetroLogic(townStoreCycler, townProxyLycler);
//        this.citizenService = new CitizenLogic(townStoreCycler, townProxyLycler);
//        this.townStore = townStoreCycler.requestTownStore();
//        this.metroId = metroService.buildMetro(new MetroCdo(metroName));
//    }
//
//    @Test
//    public void registerCitizen() throws Exception {
//        //
//        CitizenCdo sampleCdo = CitizenCdo.getSample();
//        String result = citizenService.registerCitizen(metroId, sampleCdo);
//
//        assertNotNull(result);
//    }
//
//    @Test
//    public void registerCitizens() throws Exception {
//        //
//        List<String> result = citizenService.registerCitizens(metroId, CitizenCdo.getSamples());
//
//        assertNotNull(result);
//        assertEquals(result.size(), 2);
//    }
//
//    @Test
//    public void modifyCitizen() throws Exception {
//        //
//        CitizenCdo sampleCdo = CitizenCdo.getSample();
//        String resultId = citizenService.registerCitizen(metroId, sampleCdo);
//
//        CitizenUdo citizenUdo = new CitizenUdo();
//        citizenUdo.setDisplayName("Hankook");
//        citizenService.modifyCitizen(resultId, citizenUdo);
//        Citizen result = citizenService.findCitizen(resultId);
//        System.out.println(result.toString());
//
//        assertEquals(result.getDisplayName(), "Hankook");
//    }
//
//    @Test
//    public void removeCitizen() throws Exception {
//        //
//        List<String> citizenIds = citizenService.registerCitizens(metroId, CitizenCdo.getSamples());
//        String citizenId = citizenIds.get(0);
//        citizenService.removeCitizen(citizenId);
//        Citizen result = citizenService.findCitizen(citizenId);
//
//        assertNull(result);
//    }
//
//    @Test
//    public void removeCitizens() throws Exception {
//        //
//        List<String> citizenIds = citizenService.registerCitizens(metroId, CitizenCdo.getSamples());
//        String citizenId = citizenIds.get(0);
//        citizenService.removeCitizens(citizenIds);
//        Citizen result1 = citizenService.findCitizen(citizenIds.get(0));
//        Citizen result2 = citizenService.findCitizen(citizenIds.get(1));
//
//        assertNull(result1);
//        assertNull(result2);
//    }
//}
