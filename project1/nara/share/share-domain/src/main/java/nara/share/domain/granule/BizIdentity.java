package nara.share.domain.granule;


import nara.share.domain.Embedded;
import nara.share.util.json.JsonUtil;

public class BizIdentity implements Embedded {
    //
    private String bizType;             // 업종
    private String bizCategory;         // 업태
    private String bizRegCertificate;   // 사업자 등록번호
    private String companyRegNumber;    // 법인 등록번호

    public BizIdentity(){
        //
    }

    public BizIdentity(String bizType, String bizCategory, String bizRegCertificate, String companyRegNumber) {
        //
        this.bizType = bizType;
        this.bizCategory = bizCategory;
        this.bizRegCertificate = bizRegCertificate;
        this.companyRegNumber = companyRegNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BizIdentity{");
        sb.append("bizType='").append(bizType).append('\'');
        sb.append(", bizCategory='").append(bizCategory).append('\'');
        sb.append(", bizRegCertificate='").append(bizRegCertificate).append('\'');
        sb.append(", companyRegNumber='").append(companyRegNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static BizIdentity getSample() {
        //
        String bizType = "서비스";
        String bizCategory = "온라인 Java교육";
        String bizRegCertificate = "123-45-67891";
        String companyRegNumber = "110111-006243";

        BizIdentity sample = new BizIdentity(bizType, bizCategory, bizRegCertificate, companyRegNumber);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static BizIdentity fromJson(String json) {
        //
        return JsonUtil.fromJson(json, BizIdentity.class);
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizCategory() {
        return bizCategory;
    }

    public void setBizCategory(String bizCategory) {
        this.bizCategory = bizCategory;
    }

    public String getBizRegCertificate() {
        return bizRegCertificate;
    }

    public void setBizRegCertificate(String bizRegCertificate) {
        this.bizRegCertificate = bizRegCertificate;
    }

    public String getCompanyRegNumber() {
        return companyRegNumber;
    }

    public void setCompanyRegNumber(String companyRegNumber) {
        this.companyRegNumber = companyRegNumber;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}