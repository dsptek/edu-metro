/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.
 */

package nara.share.domain;

import java.util.ArrayList;
import java.util.List;

public class IdTitleList {
	//
	private List<IdTitle> idTitles;

	public IdTitleList() {
		//
		this.idTitles = new ArrayList<>();
	}

	public IdTitleList(IdTitle idTitle) {
		//
		this();
		this.idTitles.add(idTitle);
	}

	public IdTitleList(String id, String title) {
		//
		this();
		this.idTitles.add(new IdTitle(id, title));
	}

	@Override
	public String toString() {
		//
		return idTitles.toString();
	}

	public void add(IdTitle idTitle) {
		// 
		this.idTitles.add(idTitle);
	}
	
	public void add(String id, String title) {
		// 
		this.idTitles.add(new IdTitle(id, title));
	}
	
	public void addAll(List<IdTitle> idTitles) {
		// 
		this.idTitles.addAll(idTitles);
	}
	
	public List<IdTitle> getList() {
		// 
		return idTitles;
	}

	public boolean containsTitle(String title) {
		//
		for(IdTitle idTitle : this.idTitles) {
			if (title.equals(idTitle.getTitle())) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return idTitles.size();
	}
}