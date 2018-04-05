package controller.staff;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.StaffDAO;
import model.StaffVO;

public class ReadTotalUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<StaffVO> list = (ArrayList<StaffVO>)StaffDAO.getInstance().readTotalUser();
		
		request.setAttribute("staffList", list);
		
		return "/admin/list_member.jsp";
	}

}
