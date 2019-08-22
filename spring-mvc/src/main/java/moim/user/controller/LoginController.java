package moim.user.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import moim.user.service.UserService;
import moim.user.vo.UserVO;

//@SessionAttributes("userVO")
@Controller
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private UserService service;
	
	@GetMapping("/login")
	public String loginGET() {
		return "redirect:/";
	}
	
//	@GetMapping("/user/{no}")
//	public String goMypage() {
//		return "mtpage
//	}
	
	@PostMapping("/login")
	public String loginPOST(UserVO user, HttpSession session) throws Exception{
		UserVO userVO = service.login(user);
		if(userVO == null) {
			return "redirect:/";
		}
//		model.addAttribute("userVO",userVO);
		session.setAttribute("userVO", userVO);
		return "redirect:/mypage/" + userVO.getUserNo();
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}
}
