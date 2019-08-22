package moim.moneyrequest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import moim.account.service.AccountService;
import moim.moneyrequest.service.MoneyRequestService;
import moim.moneyrequest.vo.MoneyRequestVO;
import moim.user.service.UserService;

@Controller
public class MoneyRequestController {

	@Autowired
	private MoneyRequestService mrService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/sendMoneyRequest", method=RequestMethod.POST)
//	@ResponseBody
	// 여기가 문제 여기서 친구목록 볼수 없고(초대 기능 안됨, 자기 아이디값도 까먹음)
//	public void requestMoneyList(@RequestBody Map<String, Object> map
//			) {
//		System.out.println(map.get("trcDate"));
		public String requestMoneyList(
				@RequestParam(value="getPayList") List<String> payList,
				@RequestParam(value="trcTitle") String title,
				@RequestParam(value="trcDate") String date,
				@RequestParam(value="trcAmount") int amount,
				@RequestParam(value="trcAccountNo") int accountNo
				) {
		
		Map<String, Object> retVal = new HashMap<String, Object>();
		retVal.put("code", "OK");
		retVal.put("message", "등록에 성공 하였습니다.");
		retVal.put("title", title);
		retVal.put("amount", amount);
		retVal.put("trcDate", date);
		
		// 세션에 등록
		MoneyRequestVO mrVO = new MoneyRequestVO();
		mrVO.setTrcNo(1);
		mrVO.setTrcTitle(title);
		mrVO.setTrcAccountNo(accountNo);
		mrVO.setTrcDate(date);
		mrVO.setTrcAmount(amount);
		mrVO.setTrcStatus("1");
//		
//		
//		// VO에 입력하기 위해 int [] 객체로
		int [] payListArray = new int[payList.size()];
		for(int i=0; i<payList.size(); i++) {
			int a = Integer.parseInt(payList.get(i));
			payListArray[i] = a;
		}
//		// 출금요청보내는 코드 날리기
		mrVO.setRequestList(payListArray);

		List<Integer> payListInt = new ArrayList<Integer>(); // String 리스트를 Int 리스트로 변환
		for(int i=0; i<payList.size(); i++) {
			int a = Integer.parseInt(payList.get(i));
			payListInt.add(a);
		}

		mrService.insertMoneyRequest(mrVO);
//		userService.updateUserPayStatus(payListInt);
		MoneyRequestVO newMR = mrService.selectNewRequest();
		newMR.setRequestList(payListArray);
		
		
		System.out.println(newMR);
//
//		// 트랜잭션 번호와 유저 리스트 입력
		Map<String, Object> putList = new HashMap<String, Object>();
		for(int i=0;i<newMR.getRequestList().length;i++) {
//			putList.put(newMR.getRequestList()[i], newMR.getTrcNo());
			int a = newMR.getRequestList()[i];
			putList.put("userNo", a);
			putList.put("trcNo", newMR.getTrcNo());

			mrService.insertMoneyRequestList(putList);
		}

		
//		ModelAndView mav = new ModelAndView("account/group");
//		
//		AccountVO account = accountService.selectOneAccount(accountNo);
//		List<UserVO> moimUserList = accountService.selectMoimUserByNo(accountNo);
//		
//		
//		mav.addObject("accountVO",account);
//		mav.addObject("moimUserList",moimUserList);
//
//		return mav;
		return "redirect:/account/group/"+accountNo+"/1";
	}
}
