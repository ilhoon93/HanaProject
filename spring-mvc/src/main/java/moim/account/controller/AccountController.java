package moim.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import moim.account.service.AccountService;
import moim.account.vo.AccountVO;
import moim.transaction.service.TransactionService;
import moim.transaction.vo.TransactionVO;
import moim.user.service.UserService;
import moim.user.vo.UserVO;

@Controller
public class AccountController {
	

	@Autowired
	private AccountService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService trcService;
	
	
	@RequestMapping(value="account/group/{no}/{userNo}", method=RequestMethod.GET)
	public ModelAndView detailAccount(@PathVariable("no")int accountNo, @PathVariable("userNo")int userNo) {
		AccountVO account = service.selectOneAccount(accountNo);
		List<UserVO> moimUserList = service.selectMoimUserByNo(accountNo);
		
		
		List<Integer> friList = userService.selectFriendsByNo(userNo);
		List<UserVO> fNameList = userService.selectFriendsNames(friList);
		
		List<TransactionVO> trcList = trcService.selectTransactionList(accountNo);
		
		
		// 나의 연결 계좌 정보 조회
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("moimAccount", accountNo);
		param.put("userNo", userNo);
		
		
		System.out.println("param : " + param);
		
		int linkedAccount = service.selectLinkedAccount(param);
//		List<MoimUserVO> moimUserAccountList = service.selectMoimUserByNo(accountNo);
		System.out.println("linkedAccount : " + linkedAccount);
		
		
		ModelAndView mav = new ModelAndView("account/group");
		
		mav.addObject("accountVO",account);
		mav.addObject("moimUserList",moimUserList);
		mav.addObject("friendsList", friList);
		mav.addObject("fNameList", fNameList);
		mav.addObject("linkedAccount", linkedAccount);
		mav.addObject("trcList",trcList);
		
		return mav;
	}
	
	@RequestMapping(value="account/group/{no}/chat", method=RequestMethod.GET)
	public ModelAndView moimchat(@PathVariable("no")int accountNo) {
		AccountVO account = service.selectOneAccount(accountNo);
		List<UserVO> moimUserList = service.selectMoimUserByNo(accountNo);
		
		ModelAndView mav = new ModelAndView("account/groupChat");
		
		mav.addObject("accountVO",account);
		mav.addObject("moimUserList",moimUserList);
		return mav;
	}
	
	

	
	@RequestMapping(value="/sendMoneyRequest", method=RequestMethod.GET)
	@ResponseBody
	public String checkTest() {
		return "test";
	}
}
