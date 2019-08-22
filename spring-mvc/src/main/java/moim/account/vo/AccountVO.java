package moim.account.vo;

public class AccountVO {
	 // °èÁÂ¹øÈ£ 
    private int accountNo;

    // °èÁÂ ¼ÒÀ¯ÀÚ ¹øÈ£ 
    private int accountOwnerNo;

    // °èÁÂ ÃÑ ÀÜ¾× 
    private int accountTotalAmount;

    // °èÁÂ Á¾·ù 
    private String accountType;

    // °èÁÂ ±Ý¸® 
    private Double accountRate;

    // °èÁÂ °áÁ¦ ±×·ì 
    private int accountPaymentGroup;
    
    
    public AccountVO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

	public AccountVO(int accountNo, int accountOwnerNo, int accountTotalAmount, String accountType, Double accountRate,
			int accountPaymentGroup) {
		super();
		this.accountNo = accountNo;
		this.accountOwnerNo = accountOwnerNo;
		this.accountTotalAmount = accountTotalAmount;
		this.accountType = accountType;
		this.accountRate = accountRate;
		this.accountPaymentGroup = accountPaymentGroup;
	}



	public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getAccountOwnerNo() {
        return accountOwnerNo;
    }

    public void setAccountOwnerNo(int accountOwnerNo) {
        this.accountOwnerNo = accountOwnerNo;
    }

    public int getAccountTotalAmount() {
        return accountTotalAmount;
    }

    public void setAccountTotalAmount(int accountTotalAmount) {
        this.accountTotalAmount = accountTotalAmount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getAccountRate() {
        return accountRate;
    }

    public void setAccountRate(Double accountRate) {
        this.accountRate = accountRate;
    }

    public int getAccountPaymentGroup() {
        return accountPaymentGroup;
    }

    public void setAccountPaymentGroup(int accountPaymentGroup) {
        this.accountPaymentGroup = accountPaymentGroup;
    }



	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", accountOwnerNo=" + accountOwnerNo + ", accountTotalAmount="
				+ accountTotalAmount + ", accountType=" + accountType + ", accountRate=" + accountRate
				+ ", accountPaymentGroup=" + accountPaymentGroup + "]";
	}
    
    
}
