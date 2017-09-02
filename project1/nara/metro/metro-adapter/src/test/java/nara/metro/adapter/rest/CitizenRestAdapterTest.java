package nara.metro.adapter.rest;

import nara.share.restclient.NaraRestClient;
import nara.share.restclient.springweb.SpringWebRestClient;

public class CitizenRestAdapterTest {
    //
    private static NaraRestClient naraRestClient = new SpringWebRestClient("http://localhost:9999");
//    private static CitizenRestAdapter citizenRestAdapter = new CitizenRestAdapter(naraRestClient);
//    private static TownRestAdapter townRestAdapter = new TownRestAdapter(naraRestClient);

    public static void main(String[] args) {
        //
//        String citizenId_admin = citizenRestAdapter.registerCitizen("1", new CitizenCdo("관리자", "admin@test.com"));
//        String citizenId_hong = citizenRestAdapter.registerCitizen("1", new CitizenCdo("홍길동", "hong@test.com"));
//        String citizenId_kim = citizenRestAdapter.registerCitizen("1", new CitizenCdo("김시연", "kim@test.com"));
//        String citizenId_lee = citizenRestAdapter.registerCitizen("1", new CitizenCdo("이순신", "lee@test.com"));
//        String citizenId_choi = citizenRestAdapter.registerCitizen("1", new CitizenCdo("최고봉", "choi@test.com"));
//        String citizenId_park = citizenRestAdapter.registerCitizen("1", new CitizenCdo("박관리", "park@test.com"));
//        String citizenId_kang = citizenRestAdapter.registerCitizen("1", new CitizenCdo("강사업", "kang@test.com"));

//        String citizenId_jmkim = citizenRestAdapter.registerCitizen("1", new CitizenCdo("김전문", "jmkim@test.com"));
//        String citizenId_jmlee = citizenRestAdapter.registerCitizen("1", new CitizenCdo("이전문", "jmlee@test.com"));
//        String citizenId_jmpark = citizenRestAdapter.registerCitizen("1", new CitizenCdo("박전문", "jmpark@test.com"));

//        logger.debug("{}", townRestAdapter.registerTowner("1-T-3", citizenId_hong));
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-3", citizenId_kim));
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-4", citizenId_lee));
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-4", citizenId_choi));
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-5", citizenId_park));
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-5", citizenId_kang));

//        logger.debug("{}", townRestAdapter.registerTowner("1-T-A", citizenId_jmkim));
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-A", citizenId_jmlee));
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-A", citizenId_jmpark));
//
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-9", citizenId_jmkim));
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-9", "Q@1")); // 홍길동
//        logger.debug("{}", townRestAdapter.registerTowner("1-T-9", "R@1")); // 김시연
    }
}
