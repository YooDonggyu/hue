package controller.staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.StaffDAO;
import model.StaffVO;

public class UpdateUserPositionController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		StaffVO user = (StaffVO) session.getAttribute("staffVO");
		if(user == null) {
			return "redirect:index.jsp";
		}else {
			if(user.getPositionVO().getpName().equals("관리자")) {
				String id = request.getParameter("staffId");
				String pNum = request.getParameter("pname");
				
				StaffDAO.getInstance().updateStaffPositionById(id, pNum);
				
				return "redirect:dispatcher?command=read_total_user";
			}
			return "redirect:index.jsp"; 
		}
	}

}
