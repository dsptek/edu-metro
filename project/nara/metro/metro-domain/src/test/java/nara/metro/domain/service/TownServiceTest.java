//package nara.town.domain.service;
//
//import Tier;
//import OrgChart;
//import OrgNode;
//import ChartStyle;
//import TownType;
//import Town;
//import Towner;
//import CitizenLogic;
//import MetroLogic;
//import OrgChartLogic;
//import TownLogic;
//import MetroProxyLycler;
//import TownMockProxyLycler;
//import OrgChartService;
//import OrgChartCdo;
//import OrgNodeCdo;
//import CitizenService;
//import CitizenCdo;
//import MetroService;
//import MetroCdo;
//import TownService;
//import TownCdo;
//import TownUdo;
//import TownerUdo;
//import nara.town.domain.store.mapstore.TownStoreMapLycler;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class TownServiceTest {
//    //
//    private MetroService metroService;
//    private CitizenService citizenService;
//    private OrgChartService chartService;
//    private TownService townService;
//
//    private String metroId;
//    private String metroName = "Nextree Blog";
//    private String citizenId1;
//    private String citizenId2;
//
//    private String primaryChartId;
//    private String primaryDefaultTownId;
//    private String townId;
//    private String townerId1;
//
//    private String flowerTown3Id;
//
//    @Before
//    public void setUp() throws Exception {
//        //
//        TownStoreMapLycler townStoreCycler = new TownStoreMapLycler();
//        MetroProxyLycler townProxyLycler = new TownMockProxyLycler();
//
//        this.metroService = new MetroLogic(townStoreCycler, townProxyLycler);
//        this.citizenService = new CitizenLogic(townStoreCycler, townProxyLycler);
//        this.chartService = new OrgChartLogic(townStoreCycler, townProxyLycler);
//        this.townService = new TownLogic(townStoreCycler, townProxyLycler);
//
//        this.prepareMetroAndCitizen();
//        this.prepareTownAndTowner();
//    }
//
//    @Test
//    public void buildTown() throws Exception {
//        //
//        // TODO: build town
//    }
//
//    @Test
//    public void findTown() throws Exception {
//        //
//        Town town = townService.findTown(this.townId);
//
//        assertNotNull(town);
//        assertEquals("NextreeSoft", town.getName());
//    }
//
//    @Test
//    public void modifyTown() throws Exception {
//        //
//        TownUdo townUdo = new TownUdo();
//        townUdo.setTownName("NextreeConsulting");
//        townService.modifyTown(this.townId, townUdo);
//
//        Town result = townService.findTown(this.townId);
//        assertEquals("NextreeConsulting", result.getName());
//    }
//
//    @Test
//    public void removeTown() throws Exception {
//        //
//        townService.removeTown(this.townId);
//
//        // FIXME: MongoStore 같은경우는 Id로 조회한 문서가 존재하지 않으면 예외 발생하는데,
//        // MapStore도 그렇게 해야 할까..
//        Town town = townService.findTown(this.townId);
//        assertNull(town);
//    }
//
//    @Test
//    public void registerTowner() throws Exception {
//        //
//        String townerId = townService.registerTowner(this.townId, this.citizenId2);
//
//        Towner towner = townService.findTowner(this.townId, townerId);
//        assertNotNull(towner);
//        assertEquals("Hanhee", towner.getName());
//
//        Town town = townService.findTown(townId);
//
//        OrgChart citizenChart = chartService.findCitizenChart(metroId, town.getChartId(), citizenId2);
//        System.out.println(citizenChart);
//
//        townService.registerTowner(flowerTown3Id, this.citizenId2);
//        citizenChart = chartService.findCitizenChart(metroId, town.getChartId(), citizenId2);
//        System.out.println(citizenChart);
//    }
//
//    @Test
//    public void modifyTowner() throws Exception {
//        //
//        TownerUdo townerUdo = new TownerUdo();
//        townerUdo.setName("namoo");
//        townService.modifyTowner(this.townId, this.townerId1, townerUdo);
//
//        Towner towner = townService.findTowner(this.townId, this.townerId1);
//        assertEquals(towner.getName(), "namoo");
//    }
//
//    // @Test Not implemented yet
//    public void removeTowner() throws Exception {
//        //
//        townService.removeTowner(this.townId, this.townerId1);
//
//        Towner towner = townService.findTowner(this.townId, this.townerId1);
//        assertNull(towner);
//    }
//
//    // @Test Not implemented yet
//    public void removeTownerByCitizenId() throws Exception {
//        //
//        townService.removeTownerByCitizenId(this.townId, this.citizenId1);
//
//        Towner towner = townService.findTowner(this.townId, this.townerId1);
//        assertNull(towner);
//    }
//
////    @Test
//    @Deprecated
//    public void findMyTownTree() throws Exception {
//        //
//        prepareMetroAndCitizen();
//        OrgChart orgChart = chartService.findPrimaryChart(metroId);
//        String chartId = orgChart.getId();
//        OrgNode rootNode = orgChart.getRootNode();
//        String childTownName = "R&D";
//        OrgNode addedChild1 = chartService.addChildNode(metroId, chartId, new OrgNodeCdo(rootNode.getTownId(), childTownName, TownType.Subordinate.name()));
//        OrgNode addedChild11 = chartService.addChildNode(metroId, chartId, new OrgNodeCdo(rootNode.getTownId(), "NaraTeam", TownType.Subordinate.name()));
//        OrgNode addedChild12 = chartService.addChildNode(metroId, chartId, new OrgNodeCdo(rootNode.getTownId(), "SeekRoadTeam", TownType.Subordinate.name()));
//
//        townService.buildTown(new TownCdo(chartId, rootNode.getTownId()));
//        townService.buildTown(new TownCdo(chartId, addedChild1.getTownId()));
//        townService.buildTown(new TownCdo(chartId, addedChild11.getTownId()));
//        townService.buildTown(new TownCdo(chartId, addedChild12.getTownId()));
//
//        townService.registerTowner(addedChild11.getTownId(), citizenId1);
//        townService.registerTowner(addedChild12.getTownId(), citizenId2);
//
//    }
//
//
//    private void prepareMetroAndCitizen() {
//        //
//        // Build metro
//        this.metroId = metroService.buildMetro(new MetroCdo(metroName));
//
//        // Register citizens
//        List<String> citizenIds = citizenService.registerCitizens(metroId, CitizenCdo.getSamples());
//        this.citizenId1 = citizenIds.get(0);
//        this.citizenId2 = citizenIds.get(1);
//
//        // Register ruler
//        metroService.registerRuler(metroId, citizenId1, Tier.Primary.name());
//
//        // Register primary and secondary chart
//        chartService.initChartBook(metroId);
//        this.primaryChartId = chartService.registerPrimaryChart(metroId);
//        chartService.registerSecondaryChart(metroId, new OrgChartCdo("NextreeUnited", ChartStyle.Single.name()));
//
////        townService.buildTowns(primaryChartId);
//    }
//
//    private void prepareTownAndTowner() {
//        //
//        OrgChart chart = chartService.findPrimaryChart(this.metroId);
//        this.primaryDefaultTownId = chart.getRootNode().getTownId();
//
//        OrgNode orgNode = chartService.addChildNode(metroId, chart.getId(), new OrgNodeCdo(primaryDefaultTownId, "NextreeSoft", TownType.Subordinate.name()));
//        this.townId = townService.buildTown(new TownCdo(orgNode.getTownId(), chart.getId()));
//        this.townerId1 = townService.registerTowner(this.townId, this.citizenId1);
//
//        chartService.addChildNode(metroId, chart.getId(), new OrgNodeCdo(primaryDefaultTownId, "NextreeConsulting", TownType.Subordinate.name()));
//        OrgNode flowerNode = chartService.addChildNode(metroId, chart.getId(), new OrgNodeCdo(primaryDefaultTownId, "NextreeFlower", TownType.Subordinate.name()));
//
//        chartService.addChildNode(metroId, chart.getId(), new OrgNodeCdo(flowerNode.getTownId(), "Flower1", TownType.Subordinate.name()));
//        chartService.addChildNode(metroId, chart.getId(), new OrgNodeCdo(flowerNode.getTownId(), "Flower2", TownType.Subordinate.name()));
//        OrgNode flower3Node = chartService.addChildNode(metroId, chart.getId(), new OrgNodeCdo(flowerNode.getTownId(), "Flower3", TownType.Subordinate.name()));
//        flowerTown3Id = flower3Node.getTownId();
//        chartService.addChildNode(metroId, chart.getId(), new OrgNodeCdo(flowerTown3Id, "Flower4", TownType.Subordinate.name()));
//
////        townService.buildTowns(primaryChartId);
//    }
//
//}