package nara.share.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {
	//
	private final String id;

	private Long entityVersion;

	protected Entity() {
		this.id = UUID.randomUUID().toString();
	}

	protected Entity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object target) {
		//
		if (this == target) {
			return true;
		}

		if (target == null || getClass() != target.getClass()) {
			return false;
		}

		Entity entity = (Entity) target;

		return Objects.equals(id, entity.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "id:" + id;
	}

	public String toSimpleString() {
		return "id:" + id.substring(0,8) + "...";
	}

	public Long getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(Long entityVersion) {
		this.entityVersion = entityVersion;
	}
}