package com.example.demo.bean;

public class ApiDataPra {
	String ApiKey;

	public ApiDataPra() {
	}

	//コンストラクタ。
	//この部分がJsonデータの中身になる。
	//例えば↓の例で言うと、
	//{message:あいうえお}みたいなデータになる。
	public ApiDataPra(String ApiKey) {
		this.ApiKey = ApiKey;
	}

	public String getApiKey() {
		return ApiKey;
	}

	public void setApiKey(String apiKey) {
		ApiKey = apiKey;
	}

}
