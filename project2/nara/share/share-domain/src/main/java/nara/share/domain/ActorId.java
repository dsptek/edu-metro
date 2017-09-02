package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.StringTokenizer;

public class ActorId implements ValueObject {
    //
    private String audianceId;
    private String playerId;
    private String name;

    public ActorId() {
        //
    }

    public ActorId(String audianceId, String playerId, String name) {
        //
        this.audianceId = audianceId;
        this.playerId = playerId;
        this.name = name;
    }

    public ActorId(String oneId) {
        //
        StringTokenizer tokenizer = new StringTokenizer(oneId, ":");

        this.audianceId = tokenizer.nextToken();
        this.playerId = tokenizer.nextToken();
        this.name = tokenizer.nextToken();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActorId{");
        sb.append("audianceId='").append(audianceId).append('\'');
        sb.append(", playerId='").append(playerId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static ActorId getSample() {
        //
        String audianceId = "1@1";
        String playerId = "1@1-1";
        String name = "Steve Jobs";

        ActorId sample = new ActorId(audianceId, playerId, name);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static ActorId fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ActorId.class);
    }

    public String getOneId() {
        //
        return String.format("%s:%s:%s", audianceId, playerId, name);
    }

    public String getAudianceId() {
        return audianceId;
    }

    public void setAudianceId(String audianceId) {
        this.audianceId = audianceId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().getOneId());
        System.out.println(new ActorId(getSample().getOneId()));
    }
}