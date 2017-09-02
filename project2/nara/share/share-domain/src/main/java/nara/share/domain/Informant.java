package nara.share.domain;

import nara.share.util.json.JsonUtil;

public class Informant implements ValueObject {
    //
    private ScreenId screenId;
    private Actor player;
    private IdName sourceEntity;

    public Informant() {
        //
    }

    public Informant(ScreenId screenId, Actor player) {
        //
        this(screenId, player, null);
    }

    public Informant(ScreenId screenId, Actor player, IdName sourceEntity) {
        //
        this.screenId = screenId;
        this.player = player;
        this.sourceEntity = sourceEntity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Informant{");
        sb.append("screenId=").append(screenId);
        sb.append(", player=").append(player);
        sb.append(", sourceEntity=").append(sourceEntity);
        sb.append('}');
        return sb.toString();
    }

    public static Informant getSample() {
        //
        ScreenId screenId = ScreenId.getSample();
        Actor actor = Actor.getSample();
        IdName sourceEntity = new IdName("1111", "Customer");

        Informant sample = new Informant(screenId, actor, sourceEntity);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static Informant fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Informant.class);
    }

    public ScreenId getScreenId() {
        return screenId;
    }

    public void setScreenId(ScreenId screenId) {
        this.screenId = screenId;
    }

    public Actor getPlayer() {
        return player;
    }

    public void setPlayer(Actor player) {
        this.player = player;
    }

    public IdName getSourceEntity() {
        return sourceEntity;
    }

    public void setSourceEntity(IdName sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}