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
	    HttpSession session=request.getSession(false);
	    StaffVO vo = (StaffVO)session.getAttribute("staffVO");
	    if(vo == null) {
	    	return "member/login.jsp";
	    }else {
	    	vo = StaffDAO.getInstance().findStaffById(vo.getId());
	    }
	    
	    request.setAttribute("staffVO", vo);
		return "member/form_user.jsp";
	}
}
