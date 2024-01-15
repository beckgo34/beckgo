package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.DemoDTO;

// @Controller: 해당 클래스를 컨트롤러 클래스로 스프링핀에 등록
// 스프링빈: 스플이이 관리해주는 자바 객체
@Controller
public class HomeController {
	// / 주소에 대해 get 요청이 front로 부터 오면 아래 메서드가 실행
	@GetMapping("/")
	// method 이름: index
	// return 타입: String
	public String index() {
		// templates 폴더에 있는 index.html을 찾아 화면에 띄워라
		return "index";
	}
	
	// /hi 로 get 주소 요청을 했을 때 hi.html 출력
	@GetMapping("/hi")
	public String hi() {
		return "hi";
	}
	
	// /param1 주소를 처리하기 위한 메서드
	@GetMapping("/param1")
	// p1이라는 이름으로 전달된 파라미터를 받아서 String p1에 담는다.
	public String param1(@RequestParam ("p1") String p1) 	{
		System.out.println("p1" + p1);
		return "index";
	}
	
	@GetMapping("/param2")
	public String param2(@RequestParam("p1") String p1,
						 @RequestParam("p2") String p2) {
		System.out.println("p1" + p1);
		System.out.println("p2" + p2);
		return "index";
	}
	
	// form1 주소요처에 form1.html 출력
	@GetMapping("/form1")
	public String form1() {
		return "form1";
	}
	
	@PostMapping("/form1-param")
	public String form1Param(@RequestParam("p1") String p1,
			 				 @RequestParam("p2") String p2) {
		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p2);
		return "index";
		
	}
	
	@GetMapping("/model1")
	public String model1(Model model) {
		String str1 = "오늘은 금요일";
		// 화면에 가져갈 데이터를 담음
		model.addAttribute("s1", str1);
		return "model1";
	}
	
	@GetMapping("/form2")
	public String form2() {
		return "form2";
	}
	@PostMapping("/form-param2")
	public String formParam2(@RequestParam("s1") String s1, Model model) {
		model.addAttribute("s1", s1);
		return "model2";
	}
	
	@GetMapping("/form3")
	public String form3() {
		return "form3";
	}
	
	@PostMapping("form-param3")
//	public String formParam3(@RequestParam("name") String name,
//							 @RequestParam("age") String age) {
	public String formParam3(@ModelAttribute DemoDTO demoDTO, Model model) {
//		System.out.println(name);
//		System.out.println(age);
		System.out.println(demoDTO);
		model.addAttribute("demo", demoDTO);
		return "model3";
	}
	
	@GetMapping("/model4")
	public String model4(Model model) {
		List<DemoDTO> list = new ArrayList<>();
		for(int i = 1; i<=10; i++) {
			DemoDTO demoDTO = new DemoDTO();
			demoDTO.setName("name" + i);
			demoDTO.setAge(i);
			list.add(demoDTO);
			}
		model.addAttribute("list", list);
		return "model4";
	}
}

















