package test;

import java.sql.SQLException;

import model.StaffDAO;
import model.StaffVO;

public class StaffTest {
  public static void main(String[] args) {
    StaffDAO dao=StaffDAO.getInstance();
    try {
      System.out.println("origin : "+dao.findStaffById("java"));  //"java", "abcd", "update@naver.com", "upload/image/update.jpg"
      StaffVO vo=new StaffVO("java", "1234", "update@gmail.com", "upload/image/update2.jpg");
      StaffDAO.getInstance().updateStaff(vo);
      System.out.println("update : "+dao.findStaffById("java")); //"java", "1234", "update@gmail.com", "upload/image/update2.jpg"
    } catch (SQLException e) {
      System.out.println("StaffTest update_user error: "+ e.getMessage());
    }
  }
}
