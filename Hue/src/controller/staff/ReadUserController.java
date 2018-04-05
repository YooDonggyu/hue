package controller.staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.staff.StaffDAO;
import model.staff.StaffVO;

public class ReadUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		StaffVO sessionVO = (StaffVO)session.getAttribute("staffVO");
		if(sessionVO == null) {
			return "redirect:index.jsp";
	    }else {
	    	sessionVO = StaffDAO.getInstance().findStaffById(sessionVO.getId());
	    	request.setAttribute("staffVO", sessionVO);
	    	return "member/form_user.jsp";
	    }
	}
}
