package com.javaex.vo;

public class GuestbookVo {

	
	private int no;
	private String name;
	private String password;
	private String content;
	private String date;

	
	public GuestbookVo() {

	}
	
	public GuestbookVo(int no, String pw) {
		this.no = no;
		this.password = pw;
	}
	
	public GuestbookVo(String name, String pw, String co) {
		this.name = name;
		this.password = pw;
		this.content = co;
	}

	public GuestbookVo(int no, String name, String pw, String co, String date) {
		this.no = no;
		this.name = name;
		this.password = pw;
		this.content = co;
		this.date = date;
	}
	
	// method getter/setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString() {
		return "GuestbookVo [no= " + no + ", name= " + name + ", password= " + password + ", content= " + content + ", date= " + date + "]";
	}
	

}
