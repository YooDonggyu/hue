package model;

public class PositionVO {
	private int pId;
	private String pName;
	private String pHolidayCount;
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpHolidayCount() {
		return pHolidayCount;
	}
	public void setpHolidayCount(String pHolidayCount) {
		this.pHolidayCount = pHolidayCount;
	}
	public PositionVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PositionVO(int pId, String pName, String pHolidayCount) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pHolidayCount = pHolidayCount;
	}
	public PositionVO(int pId) {
		super();
		this.pId = pId;
	}
	@Override
	public String toString() {
		return "PositionVO [pId=" + pId + ", pName=" + pName + ", pHolidayCount=" + pHolidayCount + "]";
	}
	
	
	
}
