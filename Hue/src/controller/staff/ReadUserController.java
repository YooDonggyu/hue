package controller.staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.StaffDAO;
import model.StaffVO;

public class ReadUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session=request.getSession();
	    StaffVO vo=StaffDAO.getInstance().findStaffById(((StaffVO)session.getAttribute("staffVO")).getId());
	    request.setAttribute("staffVO", vo);
		return "member/form_user.jsp";
	}

}
