package com.syhan.edu.mongodb.namoo.entity;

public class Towner {
	//
	private String id; 
	private String name;
	private String email;
	private String password;

	//--------------------------------------------------------------------------
	public Towner(){
		//
	}
	
	public Towner(String id) {
		//
		this.id = id;
	}
	
	public Towner(String id, String email) {
		//
		this.id = id;
		this.email = email;
	}

	public Towner(String name, String email, String password) {
		//
		this(); 
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id:'").append(id).append('\'');
        sb.append(", name:'").append(name).append('\'');
        sb.append(", email:'").append(email).append('\'');
        sb.append(", password:").append(password);
        sb.append('}');
        return sb.toString();
    }
		
	//--------------------------------------------------------------------------
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}