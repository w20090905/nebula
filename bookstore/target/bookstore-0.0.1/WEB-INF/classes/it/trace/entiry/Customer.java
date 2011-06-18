package it.trace.entiry;

import java.sql.Date;

public class Customer {
	private long id;
	private java.lang.String userName;
	private java.lang.String userPassword;
	private java.lang.String name;
	private java.lang.String sex;
	private Date birthday;
	private int level;
	private java.lang.String memo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(java.lang.String userPassword) {
		this.userPassword = userPassword;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getSex() {
		return sex;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public java.lang.String getMemo() {
		return memo;
	}

	public void setMemo(java.lang.String memo) {
		this.memo = memo;
	}

}
