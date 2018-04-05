package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StaffDAO;

public class DeleteUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StaffDAO.getInstance().deleteUser(request.getParameter("id"));
		
		return "dispatcher?command=logout";
	}

}
