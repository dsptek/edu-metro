//package nara.town.domain.service;
//
//import Tier;
//import Metro;
//import RulerGroup;
//import CitizenLogic;
//import MetroLogic;
//import MetroProxyLycler;
//import TownMockProxyLycler;
//import CitizenService;
//import CitizenCdo;
//import MetroService;
//import MetroCdo;
//import MetroUdo;
//import TownStore;
//import nara.town.domain.store.mapstore.TownStoreMapLycler;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class MetroServiceTest {
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
//    @After
//    public void tearDown() throws Exception {
//        //
//        metroService.removeMetro(metroId);
//    }
//
//    @Test
//    public void findMetro() throws Exception {
//        //
//        Metro metro = metroService.findMetro(metroId);
//        assertNotNull(metro);
//    }
//
//    @Test
//    public void existMetroByName() throws Exception {
//        //
//        boolean result = metroService.existMetroByName(metroName);
//        assertEquals(result, true);
//    }
//
//    @Test
//    public void findMetros() throws Exception {
//        //
//        metroService.buildMetro(new MetroCdo("Kosta Blog", "http://blog.kosta.kr"));
//
//        List<Metro> metros = metroService.findMetros();
//        assertEquals(2, metros.size());
//    }
//
//    @Test
//    public void modifyMetro() throws Exception {
//        //
//        String metroName2 = "Kosta Blog";
//        String metroName3 = "KOKO Blog";
//
//        String metroId2 = metroService.buildMetro(new MetroCdo(metroName2));
//
//        MetroUdo metroUdo = new MetroUdo();
//        metroUdo.setName(metroName3);
//        metroService.modifyMetro(metroId2, metroUdo);
//        Metro result = metroService.findMetro(metroId2);
//
//        assertEquals(result.getName(), metroName3);
//    }
//
//
//
//    @Test
//    public void registerRuler() throws Exception {
//        //
//        List<String> citizenIds = citizenService.registerCitizens(metroId, CitizenCdo.getSamples());
//        metroService.registerRuler(metroId, citizenIds.get(0), Tier.Primary.name());
//
//        Metro metro = metroService.findMetro(metroId);
//        System.out.println(metro.toString());
//
//        assertNotNull(metro.getRulerGroup().getPrimaryRuler());
//    }
//
//    @Test
//    public void removeSecondaryRuler() throws Exception {
//        //
//        List<String> citizenIds = citizenService.registerCitizens(metroId, CitizenCdo.getSamples());
//        metroService.registerRuler(metroId, citizenIds.get(0), Tier.Primary.name());
//        metroService.registerRuler(metroId, citizenIds.get(1), Tier.Secondary.name());
//
//        Metro metro = metroService.findMetro(metroId);
//        System.out.println(metro.toString());
//
//        assertNotNull(metro.getRulerGroup().getPrimaryRuler());
//        assertEquals(metro.getRulerGroup().getSecondaryRulers().size(), 1);
//    }
//
//    @Test
//    public void findRuleGroup() throws Exception {
//        //
//        List<String> citizenIds = citizenService.registerCitizens(metroId, CitizenCdo.getSamples());
//        metroService.registerRuler(metroId, citizenIds.get(0), Tier.Primary.name());
//        metroService.registerRuler(metroId, citizenIds.get(1), Tier.Secondary.name());
//
//        RulerGroup rulerGroup = metroService.findRuleGroup(metroId);
//
//        assertEquals(rulerGroup.getSecondaryRulers().size(), 1);
//    }
//}