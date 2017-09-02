package nara.share.domain;

public abstract class DramaEntity extends Entity {
	//
	private ScreenId screenId;

	public DramaEntity() {
		//
		super();
	}

	public DramaEntity(String id) {
		//
		super(id);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DramaEntity{");
		sb.append("screenId=").append(screenId);
		sb.append('}');
		return sb.toString();
	}

	public ScreenId getScreenId() {
		return screenId;
	}

	public void setScreenId(ScreenId screenId) {
		this.screenId = screenId;
	}
}