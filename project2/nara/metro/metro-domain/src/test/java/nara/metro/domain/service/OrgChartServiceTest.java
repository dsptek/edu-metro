//package nara.town.domain.service;
//
//import NameValueList;
//import OrgChart;
//import OrgNode;
//import ChartStyle;
//import TownType;
//import MetroLogic;
//import OrgChartLogic;
//import MetroProxyLycler;
//import TownMockProxyLycler;
//import OrgChartService;
//import nara.town.domain.spec.chart.sdo.*;
//import MetroService;
//import MetroCdo;
//import nara.town.domain.store.mapstore.TownStoreMapLycler;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class OrgChartServiceTest {
//    //
//    private MetroService metroService;
//    private OrgChartService chartService;
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
//        this.chartService = new OrgChartLogic(townStoreCycler, townProxyLycler);
//        this.metroId = metroService.buildMetro(new MetroCdo(metroName));
//    }
//
//    @Test
//    public void initChartBook() throws Exception {
//        //
//        chartService.initChartBook(metroId);
//        OrgChart primaryChart = chartService.findPrimaryChart(metroId);
//
//        assertNull(primaryChart);
//    }
//
//    @Test
//    public void registerPrimaryChart() throws Exception {
//        //
//        chartService.initChartBook(metroId);
//        chartService.registerPrimaryChart(metroId);
//
//        OrgChart primaryChart = chartService.findPrimaryChart(metroId);
//        System.out.println(primaryChart);
//
//        assertNotNull(primaryChart);
//    }
//
//    @Test
//    public void registerSecondaryChart() throws Exception {
//        //
//        this.prepare();
//        chartService.registerSecondaryChart(metroId, new OrgChartCdo("NextreeMall", ChartStyle.Flat.name()));
//        List<OrgChart> secondaryCharts = chartService.findSecondaryCharts(metroId);
//
//        assertEquals(2, secondaryCharts.size());
//        for(OrgChart secondaryChart : secondaryCharts) {
//            System.out.println(secondaryChart);
//        }
//    }
//
//    @Test
//    public void findChart() throws Exception {
//        //
//        this.prepare();
//        String chartId = chartService.registerSecondaryChart(metroId, new OrgChartCdo("NextreeMall", ChartStyle.Flat.name()));
//        OrgChart orgChart = chartService.findChart(metroId, chartId);
//        assertNotNull(orgChart);
//    }
//
//    @Test
//    public void findSecondaryCharts() throws Exception {
//        //
//        this.prepare();
//        String chartId = chartService.registerSecondaryChart(metroId, new OrgChartCdo("NextreeMall", ChartStyle.Flat.name()));
//        OrgChart orgChart = chartService.findChart(metroId, chartId);
//        assertNotNull(orgChart);
//    }
//
//    @Test
//    public void modifyChart() throws Exception {
//        //
//        this.prepare();
//        OrgChart orgChart = chartService.findPrimaryChart(metroId);
//        NameValueList nameValues = new NameValueList("title", "Nextree Main");
//
//        OrgChartUdo orgChartUdo = new OrgChartUdo();
//        orgChartUdo.setTitle("Nextree Main");
//        chartService.modifyChart(metroId, orgChart.getId(), orgChartUdo);
//        OrgChart primaryChart = chartService.findPrimaryChart(metroId);
//
//        System.out.println(primaryChart);
//
//        assertEquals(nameValues.getList().get(0).getValue(), primaryChart.getTitle());
//    }
//
//    @Test
//    public void addChildNode() throws Exception {
//        //
//        this.prepare();
//        OrgChart orgChart = chartService.findPrimaryChart(metroId);
//        OrgNode rootNode = orgChart.getRootNode();
//        System.out.println(rootNode);
//        String childTownName = "Nara Lab";
//        OrgNode addedChild = chartService.addChildNode(metroId, orgChart.getId(), new OrgNodeCdo(rootNode.getTownId(), childTownName, TownType.Subordinate.name()));
//        orgChart = chartService.findPrimaryChart(metroId);
//        System.out.println(orgChart.getRootNode());
//        System.out.println(addedChild);
//
//        assertNotNull(addedChild);
//    }
//
//    @Test
//    public void findSpecificNodeTree() throws Exception {
//        //
//        this.prepare();
//        OrgChart orgChart = chartService.findPrimaryChart(metroId);
//        OrgNode rootNode = orgChart.getRootNode();
//        String childTownName = "Nara Lab";
//        OrgNode addedChild = chartService.addChildNode(metroId, orgChart.getId(), new OrgNodeCdo(rootNode.getTownId(), childTownName, TownType.Subordinate.name()));
//        OrgNode specificNode = chartService.findSpecificNodeTree(metroId, orgChart.getId(), rootNode.getTownId());
//
//        System.out.println(specificNode);
//
//        assertNotNull(specificNode);
//    }
//
//    @Test
//    public void removeChildNode() throws Exception {
//        //
//        this.prepare();
//        OrgChart orgChart = chartService.findPrimaryChart(metroId);
//        OrgNode rootNode = orgChart.getRootNode();
//        String childTownName = "Nara Lab";
//        OrgNode addedChild = chartService.addChildNode(metroId, orgChart.getId(), new OrgNodeCdo(rootNode.getTownId(), childTownName, TownType.Subordinate.name()));
//        OrgNode specificNode = chartService.findSpecificNodeTree(metroId, orgChart.getId(), rootNode.getTownId());
//
//        System.out.println(specificNode);
//
//        chartService.removeChildNode(metroId, orgChart.getId(), new OrgNodeDdo(rootNode.getTownId(), addedChild.getTownId()));
//
//        specificNode = chartService.findSpecificNodeTree(metroId, orgChart.getId(), rootNode.getTownId());
//
//        System.out.println(specificNode);
//
//        assertNotNull(specificNode);
//    }
//
//    @Test
//    public void updateOrgNode() throws Exception {
//        //
//        this.prepare();
//        OrgChart orgChart = chartService.findPrimaryChart(metroId);
//        OrgNode rootNode = orgChart.getRootNode();
//        String childTownName = "Nara Lab";
//        OrgNode addedChild = chartService.addChildNode(metroId, orgChart.getId(), new OrgNodeCdo(rootNode.getTownId(), childTownName, TownType.Subordinate.name()));
//        OrgNode specificNode = chartService.findSpecificNodeTree(metroId, orgChart.getId(), rootNode.getTownId());
//
//        System.out.println(specificNode);
//
//        OrgNodeUdo orgNodeUdo = new OrgNodeUdo();
//        orgNodeUdo.setTownId(addedChild.getTownId());
//        orgNodeUdo.setTownName("Nara Team");
//        chartService.modifyOrgNode(metroId, orgChart.getId(), orgNodeUdo);
//        specificNode = chartService.findSpecificNodeTree(metroId, orgChart.getId(), orgNodeUdo.getTownId());
//
//        System.out.println(specificNode);
//
//        assertEquals("Nara Team", specificNode.getTownName());
//
//    }
//
//    private void prepare() {
//        //
//        chartService.initChartBook(metroId);
//        chartService.registerPrimaryChart(metroId);
//        chartService.registerSecondaryChart(metroId, new OrgChartCdo("NextreeUnited", ChartStyle.Single.name()));
//    }
//}