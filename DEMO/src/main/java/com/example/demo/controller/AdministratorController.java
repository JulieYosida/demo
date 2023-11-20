package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdministratorController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/adminstrator", method = RequestMethod.POST)
	public String viewPage() {

		return "adminstrator";
	}

	//一覧表示用
	@RequestMapping(path = "/studentdata", method = RequestMethod.POST)
	public String viewData(Model model) {

		List<Map<String, Object>> resultList;

		resultList = jdbcTemplate.queryForList("SELECT * FROM Attendance;");

		model.addAttribute("tangoList", resultList);

		return "studentdata";

	}

	//INSERT（登録）用メソッド
	@RequestMapping(path = "/StudataIns", method = RequestMethod.POST)
	public String postIns() {

		return "studentdata";
	}

	//UPDATE(更新)用メソッド
	@RequestMapping(path = "/studataUpd", method = RequestMethod.POST)
	public String postUpd(int student_id, int class_id, String student_name, Date attendance_data, boolean is_present) {

		jdbcTemplate.update("INSERT INTO Attendance VALUES(?,?,?,?,?)", student_id, class_id, student_name,
				attendance_data, is_present);

		return "studentdata";
	}

	//削除用メソッド
	@RequestMapping(path = "/studataDel", method = RequestMethod.POST)
	public String postSr() {

		return "studentdata";
	}

}
