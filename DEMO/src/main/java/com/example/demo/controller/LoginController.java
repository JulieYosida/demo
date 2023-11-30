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

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String third() {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String third(String user_id, String password, String practice, Model model) {
		model.addAttribute("sample1", user_id);
		model.addAttribute("sample2", password);

		// データベースからユーザー情報を検索

		Login user = loginRepository.findById(Integer.parseInt(user_id)).orElse(null);

		if (user != null && user.getPassword().equals(password)) {
			return "redirect:/home";
		} else {
			return "redirect:/ng";
		}

	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "register";
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String register(String user_id, String student_name, String password, String confirm_password, Model model) {
		// Check if passwords match
		if (!password.equals(confirm_password)) {
			model.addAttribute("error", "Passwords do not match");
			return "register";
		}

		// Check if the user_id is already registered
		if (loginRepository.findById(Integer.parseInt(user_id)).isPresent()) {
			model.addAttribute("error", "User ID already exists");
			return "register";
		}

		// Create a new user and save to the database
		Login newUser = new Login();
		newUser.setID(Integer.parseInt(user_id));
		newUser.setStudent_name(student_name);
		newUser.setPassword(password);
		loginRepository.save(newUser);

		// Redirect to login page after successful registration
		return "redirect:/login";
	}

}
