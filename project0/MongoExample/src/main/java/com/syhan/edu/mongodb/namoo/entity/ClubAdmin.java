/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 5. 10.
 */

package com.syhan.edu.mongodb.namoo.entity;

public class ClubAdmin {
	//
	private Towner towner; 
	
	//--------------------------------------------------------------------------
	private ClubAdmin() {
		//
	}
	
	public ClubAdmin(Towner towner){
		//
		this(); 
		this.towner = towner; 
	}

	//--------------------------------------------------------------------------
	public String getId() {
		return towner.getId(); 
	}
	
	public String getEmail() {
		return towner.getEmail(); 
	}
	
	public String getName() {
		return towner.getName(); 
	}

	public Towner getTowner() {
		return towner;
	}
	
}