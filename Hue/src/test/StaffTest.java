package test;


import java.sql.SQLException;

import model.StaffDAO;
import model.StaffVO;

public class StaffTest {
	public static void main(String[] args) {
		try {
			
			StaffVO vo=StaffDAO.getInstance().login("java", "1234");
			System.out.println(vo.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
