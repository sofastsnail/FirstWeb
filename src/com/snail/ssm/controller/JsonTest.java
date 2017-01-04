package com.snail.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snail.ssm.po.ItemCustom;

@Controller
public class JsonTest {
	@RequestMapping("/requestJson")
	public @ResponseBody ItemCustom requestJson(
			@RequestBody ItemCustom itemCustom) {
		return itemCustom;
	}

	@RequestMapping("/responseJson")
	public @ResponseBody ItemCustom responseJson(ItemCustom itemCustom) {
		return itemCustom;
	}
}
