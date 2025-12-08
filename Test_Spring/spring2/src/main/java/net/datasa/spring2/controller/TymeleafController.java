package net.datasa.spring2.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.spring2.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Controller
@Slf4j
@RequestMapping("th")
public class TymeleafController {
	@GetMapping("thymeleaf1")
	public String tymeleaf1(Model model){
		String str = "문자열";
		int num = 300;
		Person p = new Person("abc", "123", "김철수", "010-0101-2929", "KT");
		String tag = "<marquee>html태그</marquee>";
		String url = "https://google.com";
		
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("person", p);
		model.addAttribute("tag", tag);
		model.addAttribute("url", url);
		
		int n1 = 10000000;
		double n2 = 123.123123;
		double n3 = 0.5;
		Date d = new Date();
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		String values = "Java,HTML,CSS";
		
		model.addAttribute("inum", n1);
		model.addAttribute("dnum", n2);
		model.addAttribute("dnum2", n3);
		model.addAttribute("date", d);
		model.addAttribute("localDate", localDate);
		model.addAttribute("localDateTime", localDateTime);
		model.addAttribute("values", values);
		
		return "thView/thymeleaf1";

	}
	
	@GetMapping("thymeleaf2")
	public String thymeleaf2(Model model){
		
		String str = "문자열";
		int num = 1;
		
		ArrayList<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		
		ArrayList<Person> personList = new ArrayList<>();
		personList.add(new Person("a", "1", "철수", "11", "KT"));
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("product", "키보드");
		map.put("price", 10000);
		
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		
		
		return "thView/thymeleaf2";
	}
}
