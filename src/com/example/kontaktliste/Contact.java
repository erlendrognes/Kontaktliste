package com.example.kontaktliste;

public class Contact {
	
	private long id;
	private String firstname;
	private String lastname;
	private int phone;
	private int birthday;
	
	
	public Contact(String firstname, String lastname, int phone, int birthday) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.birthday = birthday;
	}


	public Contact() {
		super();
	}


	public Contact(long id) {
		super();
		this.id = id;
	}


	public Contact(long id, String firstname, String lastname, int phone,
			int birthday) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.birthday = birthday;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public int getBirthday() {
		return birthday;
	}


	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}	
}
