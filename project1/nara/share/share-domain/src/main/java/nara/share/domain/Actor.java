package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.StringTokenizer;

public class Actor {
    //
    private String citizenId;
    private String playerId;
    private String name;

    public Actor() {

    }

    public Actor(String id, String name) {
        //
        this.citizenId = id.substring(id.indexOf('@') + 1);
        this.playerId = id;
        this.name = name;
    }

    public Actor(String citizenId, String playerId, String name) {
        //
        this.citizenId = citizenId;
        this.playerId = playerId;
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Actor{");
        sb.append("citizenId='").append(citizenId).append('\'');
        sb.append(", playerId='").append(playerId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Deprecated
    public static Actor fromString(String idNameStr) {
        //
        StringTokenizer tokenizer = new StringTokenizer(idNameStr, ":");
        String id = tokenizer.nextToken();
        String name = tokenizer.nextToken();

        return new Actor(id, name);
    }

    public static Actor getSample() {
        //
        String citizenId = "1-1";
        String playerId = "1@1-1";
        String name = "Choonsoo Kim";

        Actor actor = new Actor(citizenId, playerId, name);
        return actor;
    }

    public static Actor getSample2() {
        //
        String playerId = "1@1-1";
        String name = "Choonsoo Kim";

        Actor actor = new Actor(playerId, name);
        return actor;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static Actor fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Actor.class);
    }

    public String getId() {
        return playerId;
    }

    public void setId(String id) {
        this.playerId = id;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
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
        System.out.println(getSample2());
    }
}