package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Login;
import com.example.demo.repository.LoginRepository;

@Controller
public class LoginController {
	@Autowired
	private LoginRepository loginRepository;

	@RequestMapping(path = "/mylogin", method = RequestMethod.GET)
	public String third() {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String third(String user_id, String password, String practice, Model model) {
		model.addAttribute("sample1", user_id);
		model.addAttribute("sample2", password);

		// データベースからユーザー情報を検索

		Login user = loginRepository.findById(Integer.parseInt(user_id)).orElse(null);

		if (user != null && user.getPW().equals(password)) {
			return "redirect:/home";
		} else {
			return "redirect:/ng";
		}

	}

}
