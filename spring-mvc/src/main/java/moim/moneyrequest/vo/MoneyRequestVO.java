package moim.moneyrequest.vo;

import java.util.Arrays;

public class MoneyRequestVO {
	private int trcNo;
	private String trcTitle;
	private int trcAccountNo;
	private int trcAmount;
	private String trcDate;
	private String trcStatus;
	private int[] requestList;
	public MoneyRequestVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTrcNo() {
		return trcNo;
	}
	public void setTrcNo(int trcNo) {
		this.trcNo = trcNo;
	}
	public String getTrcTitle() {
		return trcTitle;
	}
	public void setTrcTitle(String trcTitle) {
		this.trcTitle = trcTitle;
	}
	public int getTrcAmount() {
		return trcAmount;
	}
	public void setTrcAmount(int trcAmount) {
		this.trcAmount = trcAmount;
	}
	public String getTrcDate() {
		return trcDate;
	}
	public void setTrcDate(String trcDate) {
		this.trcDate = trcDate;
	}
	public int[] getRequestList() {
		return requestList;
	}
	public void setRequestList(int[] requestList) {
		this.requestList = requestList;
	}
	
	
	public int getTrcAccountNo() {
		return trcAccountNo;
	}
	public void setTrcAccountNo(int trcAccountNo) {
		this.trcAccountNo = trcAccountNo;
	}
	public String getTrcStatus() {
		return trcStatus;
	}
	public void setTrcStatus(String trcStatus) {
		this.trcStatus = trcStatus;
	}
	
	
	
	
	public MoneyRequestVO(int trcNo, String trcTitle, int trcAccountNo, int trcAmount, String trcDate, String trcStatus,
			int[] requestList) {
		super();
		this.trcNo = trcNo;
		this.trcTitle = trcTitle;
		this.trcAccountNo = trcAccountNo;
		this.trcAmount = trcAmount;
		this.trcDate = trcDate;
		this.trcStatus = trcStatus;
		this.requestList = requestList;
	}
	@Override
	public String toString() {
		return "MoneyRequestVO [trcNo=" + trcNo + ", trcTitle=" + trcTitle + ", trcAccountNo=" + trcAccountNo
				+ ", trcAmount=" + trcAmount + ", trcDate=" + trcDate + ", trcStatus=" + trcStatus + ", requestList="
				+ Arrays.toString(requestList) + "]";
	}
	
	
	
}
