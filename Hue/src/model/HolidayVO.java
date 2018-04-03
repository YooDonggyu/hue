package model;

public class HolidayVO {
	private int hNo;
	private String hStartDate;
	private String hEndDate;
	private String hRegDate;
	private String hContent;
	private String hFlag;
	private String hReason;
	private StaffVO staffVO;
	
	public HolidayVO(int hNo, String hStartDate, String hEndDate, String hRegDate, String hContent, String hFlag,
			StaffVO staffVO) {
		super();
		this.hNo = hNo;
		this.hStartDate = hStartDate;
		this.hEndDate = hEndDate;
		this.hRegDate = hRegDate;
		this.hContent = hContent;
		this.hFlag = hFlag;
		this.staffVO = staffVO;
	}
	
	public HolidayVO(int hNo, String hStartDate, String hEndDate, String hRegDate, String hContent, String hFlag, String hReason, StaffVO staffVO) {
    super();
    this.hNo = hNo;
    this.hStartDate = hStartDate;
    this.hEndDate = hEndDate;
    this.hRegDate = hRegDate;
    this.hContent = hContent;
    this.hFlag = hFlag;
    this.hReason = hReason;
    this.staffVO = staffVO;
  }



  public int gethNo() {
		return hNo;
	}
	public void sethNo(int hNo) {
		this.hNo = hNo;
	}
	public String gethStartDate() {
		return hStartDate;
	}
	public void sethStartDate(String hStartDate) {
		this.hStartDate = hStartDate;
	}
	public String gethEndDate() {
		return hEndDate;
	}
	public void sethEndDate(String hEndDate) {
		this.hEndDate = hEndDate;
	}
	public String gethRegDate() {
		return hRegDate;
	}
	public void sethRegDate(String hRegDate) {
		this.hRegDate = hRegDate;
	}
	public String gethContent() {
		return hContent;
	}
	public void sethContent(String hContent) {
		this.hContent = hContent;
	}
	public String gethFlag() {
		return hFlag;
	}
	public void sethFlag(String hFlag) {
		this.hFlag = hFlag;
	}
	public StaffVO getStaffVO() {
		return staffVO;
	}
	public void setStaffVO(StaffVO staffVO) {
		this.staffVO = staffVO;
	}

	@Override
	public String toString() {
		return "HolidayVO [hNo=" + hNo + ", hStartDate=" + hStartDate + ", hEndDate=" + hEndDate + ", hRegDate="
				+ hRegDate + ", hContent=" + hContent + ", hFlag=" + hFlag + ", staffVO=" + staffVO + "]";
	}
}
