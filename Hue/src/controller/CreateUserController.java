package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StaffDAO;
import model.StaffVO;

public class CreateUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String mail=request.getParameter("mail");	
		try {
			StaffDAO.getInstance().createUser(new StaffVO(id, password, name, mail));
			return "redirect:member/login.jsp";
		}catch (Exception e) {
			return "redirect:error.jsp";
		}
	}
}
