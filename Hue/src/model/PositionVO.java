package model;

public class PositionVO {
	private int pId;
	private String pName;
	private int pHolidayCount;
  public PositionVO(int pId, String pName, int pHolidayCount) {
    super();
    this.pId = pId;
    this.pName = pName;
    this.pHolidayCount = pHolidayCount;
  }
  @Override
  public String toString() {
    return "PositionVO [pId=" + pId + ", pName=" + pName + ", pHolidayCount=" + pHolidayCount + "]";
  }
}
