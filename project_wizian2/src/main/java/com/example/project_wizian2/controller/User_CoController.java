package com.example.project_wizian2.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project_wizian2.command.ManagerResumeListVO;
import com.example.project_wizian2.command.ResumeVO;
import com.example.project_wizian2.job.service.JobService;
import com.example.project_wizian2.manager.service.ManagerService;

@Controller
@RequestMapping("/user_co")
public class User_CoController {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private JobService jobService;
	
	// 인재리스트
	@GetMapping("/apply_co")
	public String apply_co(Model model) {
		
		ArrayList<ResumeVO> list = managerService.umList();
		model.addAttribute("list", list);
		System.out.println(list);
		
		return "/user_co/apply_co";
	}
	
	@GetMapping("/co_home")
	public String co_home() {
		
		return "/user_co/co_home";
	}
	
	
	@GetMapping("/co_myhome")
	public String co_myhome(Model model) {
		
		ArrayList<ManagerResumeListVO> updatePost = managerService.updatePost();
		model.addAttribute("updatePost", updatePost);

		ArrayList<ManagerResumeListVO> uploadPost = managerService.uploadPost();
		model.addAttribute("uploadPost", uploadPost);
		

		ArrayList<ResumeVO> umList = managerService.umList2();
		model.addAttribute("umList", umList);
		
		return "/user_co/co_myhome";
	}
	
	
	@GetMapping("/approve/{title}")
    public String approvePost(@RequestParam("stu_name") String stu_name) {
		System.out.println("이력서 승인 업데이트 컨트롤러 시작 : " + stu_name);
		managerService.updateResume(stu_name, "Y");
		
        return "redirect:/user_co/co_myhome"; // 승인 후 게시글 목록 페이지로 리다이렉트
    }

	@GetMapping("/reject/{title}")
    public String rejectPost(@RequestParam("stu_name") String stu_name) {
    	System.out.println("거절 업데이트 컨트롤러 시작 : " + stu_name);
    	managerService.updateResume(stu_name, "N");
    	
        return "redirect:/user_co/co_myhome"; // 거절 후 게시글 목록 페이지로 리다이렉트
    }
	
	
	
	@GetMapping("/hirePost")
	public String hirePost(Model model) {
    	System.out.println("공고등록현황페이지");
		ArrayList<ManagerResumeListVO> uploadPost = managerService.uploadPost();
		model.addAttribute("uploadPost", uploadPost);
		System.out.println("공고등록현황페이지 끝");
		System.out.println(uploadPost);
		return "/user_co/hirePost";
	}
	
	
	@GetMapping("/hire_co")
	public String hire_co(Model model) {
		
		ArrayList<ResumeVO> umList = managerService.umList2();
		model.addAttribute("umList", umList);
		
		return "/user_co/hire_co";
	}
	
	@GetMapping("/approve_second/{title}")
    public String approvePostSecond(@RequestParam("stu_name") String stu_name) {
		System.out.println("이력서 승인 업데이트 컨트롤러 시작 : " + stu_name);
		managerService.updateResume(stu_name, "Y");
		
        return "redirect:/user_co/hire_co"; // 승인 후 게시글 목록 페이지로 리다이렉트
    }

	@GetMapping("/reject_second/{title}")
    public String rejectPostSecond(@RequestParam("stu_name") String stu_name) {
    	System.out.println("거절 업데이트 컨트롤러 시작 : " + stu_name);
    	managerService.updateResume(stu_name, "N");
    	
        return "redirect:/user_co/hire_co"; // 거절 후 게시글 목록 페이지로 리다이렉트
    }
	
    @GetMapping("/hire_co_document")
	public String hire_co_document(Model model) {
    	
    	ArrayList<ResumeVO> vo = managerService.umList3();
    	model.addAttribute("vo", vo);
		return "user_co/hire_co_document";
	}
    
    @GetMapping("/approve_third/{title}")
    public String approvePostThird(@RequestParam("stu_name") String stu_name) {
		System.out.println("이력서 승인 업데이트 컨트롤러 시작 : " + stu_name);
		managerService.updateResume(stu_name, "YY");
		
        return "redirect:/user_co/hire_co_document"; // 승인 후 게시글 목록 페이지로 리다이렉트
    }

    @GetMapping("/reject_third/{title}")
    public String rejectPostThird(@RequestParam("stu_name") String stu_name) {
    	System.out.println("거절 업데이트 컨트롤러 시작 : " + stu_name);
    	managerService.updateResume(stu_name, "NN");
    	
        return "redirect:/user_co/hire_co_document"; // 거절 후 게시글 목록 페이지로 리다이렉트
    }
    
    
    @GetMapping("/hire_co_interview")
    public String hire_co_interview(Model model) {
    	ArrayList<ResumeVO> vo = managerService.umList4();
    	model.addAttribute("vo", vo);
    	return "user_co/hire_co_interview";
    }
    
    @GetMapping("/approve_fourth/{title}")
    public String approvePostFourth(@RequestParam("stu_name") String stu_name) {
		System.out.println("이력서 승인 업데이트 컨트롤러 시작 : " + stu_name);
		managerService.updateResume(stu_name, "YYY");
		
        return "redirect:/user_co/hire_co_interview"; // 승인 후 게시글 목록 페이지로 리다이렉트
    }

    @GetMapping("/reject_fourth/{title}")
    public String rejectPostFourth(@RequestParam("stu_name") String stu_name) {
    	System.out.println("거절 업데이트 컨트롤러 시작 : " + stu_name);
    	managerService.updateResume(stu_name, "NNN");
    	
        return "redirect:/user_co/hire_co_interview"; // 거절 후 게시글 목록 페이지로 리다이렉트
    }
    
    @GetMapping("/hire_co_pass")
    public String hire_co_pass(Model model) {
    	ArrayList<ResumeVO> vo = managerService.umList5();
    	model.addAttribute("vo", vo);
    	return "user_co/hire_co_pass";
    }
    
    @GetMapping("/approve_fifth/{title}")
    public String approvePostFifth(@RequestParam("stu_name") String stu_name) {
		System.out.println("이력서 승인 업데이트 컨트롤러 시작 : " + stu_name);
		managerService.updateResume(stu_name, "YYYY");
		
        return "redirect:/user_co/hire_co_pass"; // 승인 후 게시글 목록 페이지로 리다이렉트
    }

    @GetMapping("/reject_fifth/{title}")
    public String rejectPostFifth(@RequestParam("stu_name") String stu_name) {
    	System.out.println("거절 업데이트 컨트롤러 시작 : " + stu_name);
    	managerService.updateResume(stu_name, "NNNN");
    	
        return "redirect:/user_co/hire_co_pass"; // 거절 후 게시글 목록 페이지로 리다이렉트
    }
    
    
    
    
	
	@GetMapping("/purpose_co")
	public String purpose_co() {
		
		return "/user_co/purpose_co";
	}
	
	@GetMapping("/resume_jobpost")
	public String resume_jobpost() {
		
		return "/user_co/resume_jobpost";
	}
	


}
