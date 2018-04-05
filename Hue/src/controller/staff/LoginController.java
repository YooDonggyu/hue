package controller.staff;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.StaffDAO;
import model.StaffVO;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("staffId");
		String password = request.getParameter("staffPw");
		StaffVO vo = StaffDAO.getInstance().login(id,password);
		if(vo!=null) {
			HttpSession session= request.getSession();
			session.setAttribute("staffVO", vo);
			request.setAttribute("responseBody", "ok");
		}else {
			request.setAttribute("responseBody", "fail");
		}
		return "AjaxView";
	}
}
