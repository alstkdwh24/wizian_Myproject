package com.example.project_wizian2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project_wizian2.command.CompanyVO;
import com.example.project_wizian2.command.ManagerVO;
import com.example.project_wizian2.command.StudentVO;
import com.example.project_wizian2.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	public static void main(String[] args) {
        System.out.println("hello world");
        System.out.println("파일수정..");
}

	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/join")
	public String join() {
		
		return "/user/join";
	}
	
	@GetMapping("/login")
	public String login( ) {

		return "/user/login";
	}
	
	@PostMapping("/login")
	public String loginForm(StudentVO vo, CompanyVO vo2, ManagerVO vo3, Model model, HttpSession session) {
		
		
		if(vo.getStu_id() != null) {
			
			
			try {
				
				if( userservice.stu_idcheck(vo).getStu_id() == null || userservice.stu_idcheck(vo).getStu_pw() == null ) {
					
					System.out.println(123);
					return "/user/login";
					
				} else {
					String stu_id = userservice.stu_idcheck(vo).getStu_id();
					String stu_pw = userservice.stu_idcheck(vo).getStu_pw();
					
					if(vo.getStu_id().equals(stu_id) &&  vo.getStu_pw().equals(stu_pw)) {
						System.out.println("YES!!!");
						session.setAttribute("user_id", stu_id);
						return "redirect:/user_stu/home";
					
					}else {
						
						model.addAttribute("pw_msg", "비밀번호가 잘못입력되었습니다");
						return "redirect:/user/login";
						
					}
					
				}
				
			} catch (Exception e) {
				model.addAttribute("id_msg", "아이디가 잘못입력되었습니다");
				return "redirect:/user/login";
				
			}

		
		} else if (vo2.getCom_id() != null) {
			
			try {
				if( userservice.com_idcheck(vo2).getCom_id() == null || userservice.com_idcheck(vo2).getCom_pw() == null ) {
					return "/user/login";
				} else {
					String com_id = userservice.com_idcheck(vo2).getCom_id();
					String com_pw = userservice.com_idcheck(vo2).getCom_pw();
					
					if(vo2.getCom_id().equals(com_id) &&  vo2.getCom_pw().equals(com_pw)) {
						System.out.println("login successful");
						return "redirect:/user_co/co_home";
					}else {
						return "/user/login";
					}
				}
				
			} catch (Exception e) {
				model.addAttribute("id_msg", "아이디가 잘못입력되었습니다");
				return "redirect:/user/login";
			}
				
			
		} else if (vo3.getMan_id() != null) {
			
			try {
				if( userservice.man_idcheck(vo3).getMan_id() == null || userservice.man_idcheck(vo3).getMan_pw() == null ) {
					return "/user/login";
				} else {
					String man_id = userservice.man_idcheck(vo3).getMan_id();
					String man_pw = userservice.man_idcheck(vo3).getMan_pw();
					
					if(vo3.getMan_id().equals(man_id) && vo3.getMan_pw().equals(man_pw)) {
						System.out.println("login successful");
						
						return "redirect:/user_mn/mn_home";
					}else {
						return "/user/login";
					}
				}
			} catch (Exception e) {
				model.addAttribute("id_msg", "아이디가 잘못입력되었습니다");
				return "redirect:/user/login";
			}
			
			
		}
		
		return "/user";
	}
	
	
	@PostMapping("/joinStudent")
	public String joinStudent(StudentVO vo) {
		
		userservice.stu_regist(vo);
		
		return "redirect:/user/login";
	}
	
	@PostMapping("/joinCompany")
	public String joincompany(CompanyVO vo) {
		
		userservice.com_regist(vo);
		
		return "redirect:/user/login";
	}
	
	@PostMapping("/joinManager")
	public String joinManager(ManagerVO vo) {
		userservice.man_regist(vo);
		return "redirect:/user/login";
	}
	
}
