package moim.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import moim.account.service.AccountService;
import moim.account.vo.AccountVO;
import moim.user.service.UserService;
import moim.user.vo.UserVO;

@Controller
public class UserController {
		
		@Autowired
		private UserService service;
		
		
		@Autowired 
		private AccountService accountService;
		
		
		@RequestMapping(value="mypage/{no}", method=RequestMethod.GET)
		public ModelAndView detail(@PathVariable("no")int userNo) {
			UserVO user = service.detailUser(userNo);
			List<AccountVO> list = accountService.selectAccount(userNo);
			List<Integer> friList = service.selectFriendsByNo(userNo);
			List<UserVO> fNameList = null;
			if(friList != null && friList.size() != 0) {
				 fNameList = service.selectFriendsNames(friList);
			}
			
			List<AccountVO> tempMoimList = accountService.selectMoimAccount(userNo);
			List<AccountVO> moimList = new ArrayList<AccountVO>();
			for(AccountVO  account : tempMoimList) {
				if(account.getAccountOwnerNo()!=userNo) {
					moimList.add(account);
				}
			}
			
			ModelAndView mav = new ModelAndView("user/mypage");
			mav.addObject("userVO", user);
			mav.addObject("accountList",list);
			mav.addObject("friendsList", friList);
			mav.addObject("fNameList", fNameList);
			mav.addObject("moimAccountList",moimList);
			
			System.out.println("user : " + user);
			System.out.println("account : " + list);
			System.out.println("friendsList : " + friList);
			System.out.println("fNameList : " + fNameList);
			System.out.println("moimAccountList : " + moimList);
			return mav;
		}
		
// 개인 프로필 조회 /user/1
//		@RequestMapping(value="user/{no}", method=RequestMethod.GET)
//		public ModelAndView detail(@PathVariable("no")int userNo) {
//			UserVO user = service.detailUser(userNo);
//			ModelAndView mav = new ModelAndView("user/mypage");
//			mav.addObject("user",user);
//			return mav;
//		}
		
		
//		@RequestMapping(value="/user/{no}", method=RequestMethod.POST)
//		public ModelAndView detail(@PathVariable("no")int userNo) {
//			UserVO user = service.detailUser(userNo);
//			ModelAndView mav = new ModelAndView("user/mypage");
//			mav.addObject("user",user);
//			return mav;
//		}
}
