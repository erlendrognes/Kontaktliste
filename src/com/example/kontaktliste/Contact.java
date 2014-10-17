package com.example.kontaktliste;

public class Contact {
	
	private long id;
	private String firstname;
	private String lastname;
	private String phone;
	private String birthday;
	
	
	public Contact(String firstname, String lastname, String phone, String birthday) {
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


	public Contact(long id, String firstname, String lastname, String phone,
			String birthday) {
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}	
}
