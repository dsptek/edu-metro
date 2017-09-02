/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.
 */

package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ActorList {
	//
	private List<Actor> actors;

	public ActorList() {
		//
		this.actors = new ArrayList<>();
	}

	public ActorList(Actor actor) {
		//
		this();
		this.actors.add(actor);
	}

	public ActorList(String id, String name) {
		//
		this();
		this.actors.add(new Actor(id, name));
	}

	@Override
	public String toString() {
		//
		return actors.toString();
	}

	public String toJson() {
		//
		return JsonUtil.toJson(this);
	}

	public static ActorList fromJson(String json) {
		//
		return JsonUtil.fromJson(json, ActorList.class);
	}

	public void add(Actor actor) {
		// 
		this.actors.add(actor);
	}
	
	public void add(String id, String name) {
		// 
		this.actors.add(new Actor(id, name));
	}
	
	public void addAll(List<Actor> actors) {
		// 
		this.actors.addAll(actors);
	}
	
	public List<Actor> getList() {
		// 
		return actors;
	}

	public boolean containsName(String name) {
		//
		for(Actor actor : this.actors) {
			if (name.equals(actor.getName())) {
				return true;
			}
		}
		return false;
	}

	public boolean containsId(String id) {
		//
		for(Actor actor : this.actors) {
			if (id.equals(actor.getId())) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return actors.size();
	}
}