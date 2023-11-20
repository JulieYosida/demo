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

	@Column(name = "password")
	private String password;

	public int getID() {
		return user_id;
	}

	public void setID(int user_id) {
		this.user_id = user_id;
	}

	public String getPW() {
		return password;
	}

	public void setPW(String password) {
		this.password = password;
	}

}