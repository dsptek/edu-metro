/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 5. 10.
 */

package com.syhan.edu.mongodb.namoo.entity;


public class ClubMember {
	//
	private String clubId; 
	private String memberId;
	
	private Towner towner;

	//--------------------------------------------------------------------------
	private ClubMember() {
		// 
	}
	
	public ClubMember(Club club, Towner towner){
		//
		this(); 
		this.clubId = club.getId(); 
		this.memberId = String.format("%s-%s", club.getId(), towner.getEmail()); 
		this.towner = towner; 
	}

	public ClubMember(String clubId, String memberId, Towner towner) {
		//
		this.clubId = clubId;
		this.memberId = memberId; 
		this.towner = towner; 
	}
	
	//--------------------------------------------------------------------------
	public String getTownerId() {
		if (towner == null) return null;
		return towner.getId(); 
	}
	
	public String getEmail() {
		if (towner == null) return null;
		return towner.getEmail(); 
	}
	
	public String getName() {
		if (towner == null) return null;
		return towner.getName(); 
	}

	public String getClubId() {
		return clubId;
	}

	public String getMemberId() {
		return memberId;
	}

	public Towner getTowner() {
		return towner;
	}
	
	public void setTowner(Towner towner) {
		this.towner = towner;
	}

}