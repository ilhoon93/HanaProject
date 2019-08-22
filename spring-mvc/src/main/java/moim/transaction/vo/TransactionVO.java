package moim.transaction.vo;

public class TransactionVO {
	
	
	private int trcNo;
	private String trcDate;
	private int trcAmount;
	private int trcSendAccount;
	private int trcReceiveAccount;
	private String trcStatus;
	private String trcSenderName;
	private String trcTitle;
	private String trcOriginFile;
	private String trcServerFile;
	
	public TransactionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public TransactionVO(int trcNo, String trcDate, int trcAmount, int trcSendAccount, int trcReceiveAccount,
			String trcStatus, String trcSenderName, String trcTitle, String trcOriginFile, String trcServerFile) {
		super();
		this.trcNo = trcNo;
		this.trcDate = trcDate;
		this.trcAmount = trcAmount;
		this.trcSendAccount = trcSendAccount;
		this.trcReceiveAccount = trcReceiveAccount;
		this.trcStatus = trcStatus;
		this.trcSenderName = trcSenderName;
		this.trcTitle = trcTitle;
		this.trcOriginFile = trcOriginFile;
		this.trcServerFile = trcServerFile;
	}


	public String getTrcOriginFile() {
		return trcOriginFile;
	}

	public void setTrcOriginFile(String trcOriginFile) {
		this.trcOriginFile = trcOriginFile;
	}




	public String getTrcServerFile() {
		return trcServerFile;
	}




	public void setTrcServerFile(String trcServerFile) {
		this.trcServerFile = trcServerFile;
	}
	public int getTrcNo() {
		return trcNo;
	}
	public void setTrcNo(int trcNo) {
		this.trcNo = trcNo;
	}
	public String getTrcDate() {
		return trcDate;
	}
	public void setTrcDate(String trcDate) {
		this.trcDate = trcDate;
	}
	public int getTrcAmount() {
		return trcAmount;
	}
	public void setTrcAmount(int trcAmount) {
		this.trcAmount = trcAmount;
	}
	public int getTrcSendAccount() {
		return trcSendAccount;
	}
	public void setTrcSendAccount(int trcSendAccount) {
		this.trcSendAccount = trcSendAccount;
	}
	public int getTrcReceiveAccount() {
		return trcReceiveAccount;
	}
	public void setTrcReceiveAccount(int trcReceiveAccount) {
		this.trcReceiveAccount = trcReceiveAccount;
	}
	public String getTrcStatus() {
		return trcStatus;
	}
	public void setTrcStatus(String trcStatus) {
		this.trcStatus = trcStatus;
	}

	
	public String getTrcSenderName() {
		return trcSenderName;
	}

	public void setTrcSenderName(String trcSenderName) {
		this.trcSenderName = trcSenderName;
	}




	public String getTrcTitle() {
		return trcTitle;
	}




	public void setTrcTitle(String trcTitle) {
		this.trcTitle = trcTitle;
	}




	@Override
	public String toString() {
		return "TransactionVO [trcNo=" + trcNo + ", trcDate=" + trcDate + ", trcAmount=" + trcAmount
				+ ", trcSendAccount=" + trcSendAccount + ", trcReceiveAccount=" + trcReceiveAccount + ", trcStatus="
				+ trcStatus + ", trcSenderName=" + trcSenderName + ", trcTitle=" + trcTitle + ", trcOriginFile="
				+ trcOriginFile + ", trcServerFile=" + trcServerFile + "]";
	}
	
	
	
}
