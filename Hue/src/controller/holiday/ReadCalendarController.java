package controller.holiday;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.Controller;
import model.holiday.HolidayDAO;
import model.holiday.HolidayVO;

public class ReadCalendarController implements Controller{

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String year=request.getParameter("year");
    String month=request.getParameter("month");
    if(month.length()<2)
      month="0"+month;
    String date=year+"-"+month+"-"+"01";
    ArrayList<HolidayVO> holidayVOList=HolidayDAO.getInstance().getTotalHoliday(date);
    JSONArray holidayList=new JSONArray();
    for(HolidayVO holidayVO:holidayVOList){
      JSONObject holiday = new JSONObject();
      JSONObject staff = new JSONObject();
      
      //position
      JSONObject position = new JSONObject();
      position.put("pId", holidayVO.getStaffVO().getPositionVO().getpId());
      position.put("pName", holidayVO.getStaffVO().getPositionVO().getpName());
      position.put("pHolidayCount", holidayVO.getStaffVO().getPositionVO().getpHolidayCount());
      
      //staff
      staff.put("positionVO", position);
      staff.put("id",holidayVO.getStaffVO().getId());
      staff.put("password",holidayVO.getStaffVO().getPassword());
      staff.put("name",holidayVO.getStaffVO().getName());
      staff.put("mail",holidayVO.getStaffVO().getMail());
      staff.put("imagePath",holidayVO.getStaffVO().getImagePath());
      
      //holiday
      holiday.put("hNo",holidayVO.gethNo());
      holiday.put("hStartDate",holidayVO.gethStartDate() );
      holiday.put("hEndDate",holidayVO.gethEndDate() );
      holiday.put("hRegDate", holidayVO.gethRegDate());
      holiday.put("hContent", holidayVO.gethContent());
      holiday.put("hFlag",holidayVO.gethFlag() );
      holiday.put("hReason",holidayVO.gethReason() );
      holiday.put("staffVO",staff );
      
      holidayList.put(holiday);
    }
    request.setAttribute("responseBody",holidayList);
    return "AjaxView";
  }
  
}
