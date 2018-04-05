package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.StaffDAO;
import model.StaffVO;

public class DeleteUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StaffDAO.getInstance().deleteUser(request.getParameter("id"));
		
		HttpSession session = request.getSession(false);
		StaffVO user = (StaffVO) session.getAttribute("staffVO");
		if(user == null) {
			return "redirect:index.jsp";
		}else {
			if(user.getPositionVO().getpName().equals("관리자")) {
				
				return "dispatcher?command=read_total_user";
				
			}else {
				return "dispatcher?command=logout";
				
			}
		}
	}
}
