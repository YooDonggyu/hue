package test;

import java.sql.SQLException;

import model.holiday.HolidayDAO;
import model.holiday.HolidayVO;
import model.staff.StaffDAO;

public class StaffTest {
  public static void main(String[] args) {
    StaffDAO dao=StaffDAO.getInstance();
    try {
   /*   //read & update
      System.out.println("origin : "+dao.findStaffById("java"));  //"java", "abcd", "update@naver.com", "/upload/image/update.jpg"
      StaffVO vo=new StaffVO("java", "1234", "update@gmail.com", "/upload/image/update2.jpg");
      StaffDAO.getInstance().updateStaff(vo);
      System.out.println("update : "+dao.findStaffById("java")); //"java", "1234", "update@gmail.com", "/upload/image/update2.jpg"
      
      //login test
      vo=StaffDAO.getInstance().login("java", "1234");
      System.out.println(vo.toString());*/
      
      
      //read_detail_holiday
      HolidayVO vo =HolidayDAO.getInstance().findDetailHolidayByPno(1);
      System.out.println(vo);
      
    } catch (SQLException e) {
    	e.printStackTrace();
      //System.out.println("StaffTest update_user error: "+ e.getMessage());
    }
  }
}
