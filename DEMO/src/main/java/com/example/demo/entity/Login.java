package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//↓「name="xxx"」の「xxx」の部分に模倣したいテーブル名を書く
@Table(name = "login")
public class Login {

	//主キーには「@Id」を設定する！
	@Id
	//カラム名(列名)を書く。
	@Column(name = "user_id")
	private int user_id;

	@Column(name = "student_name")
	private String student_name;

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "password")
	private String password;

	public int getID() {
		return user_id;
	}

	public void setID(int user_id) {
		this.user_id = user_id;
	}

}