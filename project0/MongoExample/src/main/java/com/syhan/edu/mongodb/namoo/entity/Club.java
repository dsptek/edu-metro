package com.syhan.edu.mongodb.namoo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Club {
	//
	private String id;
	private String name;
	private String description;
	private Date openDate;
	
	private ClubAdmin admin; 
	private List<ClubMember> members;

	//--------------------------------------------------------------------------
	private Club() {
		// 
		this.openDate = new Date();
		this.members = new ArrayList<ClubMember>();
	}
	
	public Club(String id) {
		//
		this();
		this.id = id;
	}
	
	public Club(String clubName, String description, ClubAdmin admin) {
		//
		this();
		this.name = clubName; 
		this.description = description; 
		this.admin = admin; 
	}
	
	public Club(String id, String clubName, String description, Date openDate, ClubAdmin admin) {
		//
		this(clubName, description, admin);
		this.id = id;
		this.openDate = openDate;
	}
	
	//--------------------------------------------------------------------------
	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder();
		builder.append("id:" + id);
		builder.append(", name:" + name);
		builder.append(", description:" + description);
		builder.append(", admin:" + admin.getName());
		builder.append(", member count:" + getMembers().size());
		
		return builder.toString();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id; 
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public void addMember(ClubMember member) {
		members.add(member); 
	}
	
	public void setMembers(List<ClubMember> members) {
		//
		this.members = members;
	}
	
	public List<ClubMember> getMembers() {
		return members;
	}
	
	public ClubAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(ClubAdmin admin) {
		this.admin = admin;
	}
}