package controller.holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.holiday.HolidayDAO;
import model.staff.StaffVO;

public class UpdateHolidayController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		StaffVO sessionVO = (StaffVO)session.getAttribute("staffVO");
		if(sessionVO == null) {
			return "redirect:index.jsp";
		}else {
			int hNo= Integer.parseInt(request.getParameter("hNo"));
			String hContent = request.getParameter("hContent");
			String hStartDate = request.getParameter("hStartDate");
			String hEndDate = request.getParameter("hEndDate");
			HolidayDAO.getInstance().updateHoliday(hContent,hStartDate,hEndDate,hNo);
			request.setAttribute("responseBody", "ok");
			return "AjaxView";
		}
	}
}
