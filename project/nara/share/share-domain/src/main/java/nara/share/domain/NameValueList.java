/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.
 */

package nara.share.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Name-Value list object
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2016. 3. 29.
 */

public class NameValueList {
	//
	private List<NameValue> nameValues;
	
	public NameValueList() {
		// 
		this.nameValues = new ArrayList<>();
	}

	public NameValueList(NameValue nameValue) {
		//
		this();
		this.nameValues.add(nameValue);
	}

	public NameValueList(String name, String value) {
		//
		this();
		this.nameValues.add(new NameValue(name, value));
	}

	@Override
	public String toString() {
		//
		return nameValues.toString();
	}

	public void add(NameValue nameValue) {
		// 
		this.nameValues.add(nameValue); 
	}
	
	public NameValueList add(String name, String value) {
		// 
		this.nameValues.add(new NameValue(name, value));
		return this;
	}

	public String getValueOf(String name) {
		//
		String foundValue = null;
		for(NameValue nameValue : this.nameValues) {
			if (name.equals(nameValue.getName())) {
				foundValue = nameValue.getValue();
			}
		}

		return foundValue;
	}

	public void addAll(List<NameValue> nameValues) {
		// 
		this.nameValues.addAll(nameValues); 
	}
	
	public List<NameValue> getList() {
		// 
		return nameValues; 
	}

	public void setNameValues(List<NameValue> nameValues) {
		this.nameValues = nameValues;
	}

	public boolean containsName(String name) {
		//
		for(NameValue nameValue : this.nameValues) {
			if (name.equals(nameValue.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return nameValues.size(); 
	}
}