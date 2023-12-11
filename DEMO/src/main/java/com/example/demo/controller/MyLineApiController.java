package com.example.demo.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.Event;
import com.example.demo.bean.LineData;

@RestController
public class MyLineApiController {

	//ここにチャンネルアクセストークンる！
	String channelAccessToken = "vzN3qRhN59Cv3E+wml2maIFpwFuDAFHBBzheo+FcIvCPAjDUDfR9mTT4Y42bfh6l49bs3uOIsKoOubwTDejeWcXeBblsabqMKd3nFfoS7GqdgTRjJbODD/UuXzy2BI30ndqYv3f+FieM8nkhgzoUgQdB04t89/1O/w1cDnyilFU=";

	@PostMapping("/lineApi")
	@CrossOrigin(origins = "*")
	public void postApidata(@RequestBody LineData webhookData) {
		for (Event event : webhookData.getEvents()) {

			//メッセージを送ってきたアカウント情報を変数「replyToken」に格納する。
			String replyToken = event.getReplyToken();

			////////////////ここかから/////////////////////

			//メッセージの内容を変数「replyText」に格納。
			String replyText = event.getMessage().getText();

			//機密データ(フリー素材)
			String numataImg = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-numata-s-on.jpg";
			String watabeImg = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-watabe-m-on.jpg";
			String sueImg = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-suehara-a-on.jpg";

			//練習問題用
			//もしも受信したメッセージが「渡部」なら渡部画像を表示させる。
			//「末原先生」なら末原先生の画像を表示させるようにしてみよう。
			//どれでもないなら沼田先生を表示
			//if("渡部".equals(??????)){
			//			replyImageMessage(replyToken, watabeImg, watabeImg);
			//} else if(){
			//				replyImageMessage(replyToken, watabeImg, watabeImg);
			//	}

			//LINEに返すデータを作成するメソッド(第一引数に「replyToken」、第二、第三引数に送りたい画像を渡す)
			//replyImageMessage(replyToken, numataImg, numataImg);

			//文字を送る場合
			replyMessage(replyToken, "https://768c-116-82-246-181.ngrok-free.app/login");
			//スタンプを送る場合
			//replyStampMessage(replyToken);

			//////////////ここまでを見てね。///////////////////////
		}
	}

	//
	private void replyImageMessage(String replyToken, String originalContentUrl, String previewImageUrl) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信する画像を設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "image");
		message.put("originalContentUrl", originalContentUrl);
		message.put("previewImageUrl", previewImageUrl);

		// リクエストボディを設定（画像用）
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		// 画像を送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);

	}

	//文字を送りたい場合はこのメソッドを呼び出す。
	//呼び出す際、第二引数に送りたい文字列を渡す。
	private void replyMessage(String replyToken, String replyText) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		message.put("text", replyText);

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		System.out.println("test");

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

	//メッセージ送ってきた人に返信するためのメソッド
	private void replyStampMessage(String replyToken) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "sticker");
		message.put("packageId", 446);
		message.put("stickerId", 1988);
		//↑packageIdとstickrIdでどのスタンプを送るか定義している。
		//詳しくは公式Docを見てみよう！
		//https://developers.line.biz/ja/docs/messaging-api/sticker-list/#sticker-definitions

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		System.out.println("test");

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}
}
