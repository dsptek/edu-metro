/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.
 */

package nara.share.domain;

import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class IdNameList {
	//
	private List<IdName> idNames;

	public IdNameList() {
		//
		this.idNames = new ArrayList<>();
	}

	public IdNameList(IdName idName) {
		//
		this();
		this.idNames.add(idName);
	}

	public IdNameList(String id, String name) {
		//
		this();
		this.idNames.add(new IdName(id, name));
	}

	@Override
	public String toString() {
		//
		return idNames.toString();
	}

	public String toJson() {
		//
		return JsonUtil.toJson(this);
	}

	public static IdNameList fromJson(String json) {
		//
		return JsonUtil.fromJson(json, IdNameList.class);
	}

	public void add(IdName idName) {
		// 
		this.idNames.add(idName);
	}
	
	public void add(String id, String name) {
		// 
		this.idNames.add(new IdName(id, name));
	}
	
	public void addAll(List<IdName> idNames) {
		// 
		this.idNames.addAll(idNames);
	}
	
	public List<IdName> getList() {
		// 
		return idNames;
	}

	public boolean containsName(String name) {
		//
		for(IdName idName : this.idNames) {
			if (name.equals(idName.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return idNames.size();
	}
}