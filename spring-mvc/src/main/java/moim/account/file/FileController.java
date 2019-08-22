package moim.account.file;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import moim.account.service.AccountService;
import moim.account.vo.AccountVO;
import moim.transaction.service.TransactionService;
import moim.user.service.UserService;
import moim.user.vo.UserVO;

@Controller

@RequestMapping("account/group/{accountNo}/{userNo}")
public class FileController {

	
	@Autowired
	private AccountService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private TransactionService trcService;
	
	
	@RequestMapping(value="/file/{trcNo}", method=RequestMethod.POST)
	public String fileUpload(@PathVariable("accountNo") int accountNo, 
			@PathVariable("userNo") int userNo,
			@PathVariable("trcNo") int trcNo,
			MultipartHttpServletRequest mRequest) throws Exception{
		
		AccountVO account = service.selectOneAccount(accountNo);
		List<UserVO> moimUserList = service.selectMoimUserByNo(accountNo);
		List<Integer> friList = userService.selectFriendsByNo(userNo);
		List<UserVO> fNameList = userService.selectFriendsNames(friList);
		
		String uploadDir = servletContext.getRealPath("") + "/resources/file/";
		System.out.println(uploadDir);
		
//		ModelAndView mav = new ModelAndView("account/group");
		
//		mav.addObject("accountVO",account);
//		mav.addObject("moimUserList",moimUserList);
//		mav.addObject("friendsList", friList);
//		mav.addObject("fNameList", fNameList);

				
		Iterator<String> iter = mRequest.getFileNames();
		while(iter.hasNext()) {
			String formFileName = iter.next();
			
			MultipartFile mFile = mRequest.getFile(formFileName);
			
			// 원본 파일명
			String oriFileName = mFile.getOriginalFilename();
			System.out.println("원본파일명 : " + oriFileName);
			
			if(oriFileName != null && !oriFileName.equals("")) {
				// 확장자 처리
				
				String ext = "";
				int index = oriFileName.lastIndexOf(".");
				if(index != -1) {
					ext = oriFileName.substring(index);
				}		
				//파일 사이즈
				long fileSize = mFile.getSize();
				System.out.println("파일 사이즈 : " + fileSize);
				String saveFileName = "moim-" + UUID.randomUUID().toString() + ext;
				mFile.transferTo(new File(uploadDir + saveFileName));
				
				//파일 업로드 
				Map<String, Object> fileUploadMap = new HashMap<String, Object>();
				fileUploadMap.put("serverFilename", saveFileName);
				fileUploadMap.put("originFilename", oriFileName);
				fileUploadMap.put("trcNo",trcNo);
				System.out.println(fileUploadMap);
				trcService.receiptFileUpload(fileUploadMap);
			}
			
		}
				
		return "redirect:/account/group/{accountNo}/{userNo}";
//		return mav;
	}
}