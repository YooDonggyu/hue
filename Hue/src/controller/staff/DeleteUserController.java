package controller.staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.staff.StaffDAO;
import model.staff.StaffVO;

public class DeleteUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("1");
		
		HttpSession session = request.getSession(false);
		StaffVO user = (StaffVO) session.getAttribute("staffVO");
		if(user == null) {
			System.out.println("2");
			return "redirect:index.jsp";
		}else {
			StaffDAO.getInstance().deleteUser(request.getParameter("id"));
			System.out.println("3");
			if(user.getPositionVO().getpName().equals("관리자")) {
				request.setAttribute("responseBody", "ok");
				return "AjaxView";
			}else {
				return "dispatcher?command=logout";
			}
		}
	}
}
