package moim.user.vo;

/**
 * 유저 테이블 모델 객체 클래스
 * @author Ilhoon
 */


//유저 테이블 
public class UserVO {

 // 유저번호 
 private int userNo;

 // 유저이름 
 private String userName;

 // 유저이메일 
 private String userEmail;

 // 유저비밀번호 
 private String userPassword;

 // 유저프로필원파일 
 private String userImgOriginFile;

 // 유저프로필서버파일 
 private String userImgServerFile;

 // 유저전화번호 
 private int userPhoneNumber;
 
 // 유저 결제 정보 현황
 private String userPayStatus;
 

 
 
 
 public UserVO() {
	super();
	// TODO Auto-generated constructor stub
}
 

public String getUserPayStatus() {
	return userPayStatus;
}



public UserVO(int userNo, String userName, String userEmail, String userPassword, String userImgOriginFile,
		String userImgServerFile, int userPhoneNumber, String userPayStatus) {
	super();
	this.userNo = userNo;
	this.userName = userName;
	this.userEmail = userEmail;
	this.userPassword = userPassword;
	this.userImgOriginFile = userImgOriginFile;
	this.userImgServerFile = userImgServerFile;
	this.userPhoneNumber = userPhoneNumber;
	this.userPayStatus = userPayStatus;
}



public void setUserPayStatus(String userPayStatus) {
	this.userPayStatus = userPayStatus;
}



public int getUserNo() {
     return userNo;
 }

 public void setUserNo(int userNo) {
     this.userNo = userNo;
 }

 public String getUserName() {
     return userName;
 }

 public void setUserName(String userName) {
     this.userName = userName;
 }

 public String getUserEmail() {
     return userEmail;
 }

 public void setUserEmail(String userEmail) {
     this.userEmail = userEmail;
 }

 public String getUserPassword() {
     return userPassword;
 }

 public void setUserPassword(String userPassword) {
     this.userPassword = userPassword;
 }

 public String getUserImgOriginFile() {
     return userImgOriginFile;
 }

 public void setUserImgOriginFile(String userImgOriginFiile) {
     this.userImgOriginFile = userImgOriginFiile;
 }

 public String getUserImgServerFile() {
     return userImgServerFile;
 }

 public void setUserImgServerFile(String userImgServerFiile) {
     this.userImgServerFile = userImgServerFiile;
 }

 public int getUserPhoneNumber() {
     return userPhoneNumber;
 }

 public void setUserPhoneNumber(int userPhoneNumber) {
     this.userPhoneNumber = userPhoneNumber;
 }

 
 
 
 
 @Override
public String toString() {
	return "UserVO [userNo=" + userNo + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
			+ userPassword + ", userImgOriginFile=" + userImgOriginFile + ", userImgServerFile=" + userImgServerFile
			+ ", userPhoneNumber=" + userPhoneNumber + ", userPayStatus=" + userPayStatus + "]";
}



// TUser 모델 복사
 public void CopyData(UserVO param)
 {
     this.userNo = param.getUserNo();
     this.userName = param.getUserName();
     this.userEmail = param.getUserEmail();
     this.userPassword = param.getUserPassword();
     this.userImgOriginFile = param.getUserImgOriginFile();
     this.userImgServerFile = param.getUserImgServerFile();
     this.userPhoneNumber = param.getUserPhoneNumber();
 }
}

