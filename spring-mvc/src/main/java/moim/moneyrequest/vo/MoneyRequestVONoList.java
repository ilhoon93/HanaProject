package moim.moneyrequest.vo;

public class MoneyRequestVONoList {
	private int trcNo;
	private String trcTitle;
	private int trcAccountNo;
	private int trcAmount;
	private String trcDate;
	private String trcStatus;
	public MoneyRequestVONoList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MoneyRequestVONoList(int trcNo, String trcTitle, int trcAccountNo, int trcAmount, String trcDate,
			String trcStatus) {
		super();
		this.trcNo = trcNo;
		this.trcTitle = trcTitle;
		this.trcAccountNo = trcAccountNo;
		this.trcAmount = trcAmount;
		this.trcDate = trcDate;
		this.trcStatus = trcStatus;
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
	public int getTrcAccountNo() {
		return trcAccountNo;
	}
	public void setTrcAccountNo(int trcAccountNo) {
		this.trcAccountNo = trcAccountNo;
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
	public String getTrcStatus() {
		return trcStatus;
	}
	public void setTrcStatus(String trcStatus) {
		this.trcStatus = trcStatus;
	}
	@Override
	public String toString() {
		return "MoneyRequestVONoList [trcNo=" + trcNo + ", trcTitle=" + trcTitle + ", trcAccountNo=" + trcAccountNo
				+ ", trcAmount=" + trcAmount + ", trcDate=" + trcDate + ", trcStatus=" + trcStatus + "]";
	}
	
	
}
