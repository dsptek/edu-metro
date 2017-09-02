package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.StringTokenizer;

public class ScreenId implements ValueObject {
    //
    private String pavilionId;
    private String cineroomId;
    private String dramaId;
    private DramaScope scope;

    public ScreenId() {
        //
    }

    public ScreenId(String pavilionId, String cineroomId, String dramaId) {
        //
        this.pavilionId = pavilionId;
        this.cineroomId = cineroomId;
        this.dramaId = dramaId;
        this.scope = DramaScope.Cineroom;
    }

    public ScreenId(String oneId) {
        //
        StringTokenizer tokenizer = new StringTokenizer(oneId, ":");

        this.pavilionId = tokenizer.nextToken();
        this.cineroomId = tokenizer.nextToken();
        this.dramaId = tokenizer.nextToken();
        if (tokenizer.hasMoreElements()) {
            this.scope = DramaScope.valueOf(tokenizer.nextToken());
        } else {
            this.scope = DramaScope.Cineroom;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScreenId{");
        sb.append("pavilionId='").append(pavilionId).append('\'');
        sb.append(", cineroomId='").append(cineroomId).append('\'');
        sb.append(", dramaId='").append(dramaId).append('\'');
        sb.append(", scope=").append(scope);
        sb.append('}');
        return sb.toString();
    }

    public static ScreenId getSample() {
        //
        String pavilionId = "1";
        String cineroomId = "1-1";
        String dramaId = "17-0003";

        ScreenId sample = new ScreenId(pavilionId, cineroomId, dramaId);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static ScreenId fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ScreenId.class);
    }

    public String getOneId() {
        //
        return String.format("%s:%s:%s:%s", pavilionId, cineroomId, dramaId, scope);
    }

    public String getPavilionId() {
        return pavilionId;
    }

    public void setPavilionId(String pavilionId) {
        this.pavilionId = pavilionId;
    }

    public String getCineroomId() {
        return cineroomId;
    }

    public void setCineroomId(String cineroomId) {
        this.cineroomId = cineroomId;
    }

    public String getDramaId() {
        return dramaId;
    }

    public void setDramaId(String dramaId) {
        this.dramaId = dramaId;
    }

    public DramaScope getScope() {
        return scope;
    }

    public void setScope(DramaScope scope) {
        this.scope = scope;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().getOneId());
        System.out.println(new ScreenId(getSample().getOneId()));
    }
}