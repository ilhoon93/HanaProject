package moim.account.vo;

public class MoimUserVO {
	private int accountNo;
	private int newUserNo;
	private int newUserAccountNo;
	
	public MoimUserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MoimUserVO(int accountNo, int newUserNo, int newUserAccountNo) {
		super();
		this.accountNo = accountNo;
		this.newUserNo = newUserNo;
		this.newUserAccountNo = newUserAccountNo;
	}
	
	
	public int getNewUserAccountNo() {
		return newUserAccountNo;
	}
	public void setNewUserAccountNo(int newUserAccountNo) {
		this.newUserAccountNo = newUserAccountNo;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public int getNewUserNo() {
		return newUserNo;
	}
	public void setNewUserNo(int newUserNo) {
		this.newUserNo = newUserNo;
	}
	@Override
	public String toString() {
		return "MoimUserVO [accountNo=" + accountNo + ", newUserNo=" + newUserNo + ", newUserAccountNo="
				+ newUserAccountNo + "]";
	}

	
	
}
