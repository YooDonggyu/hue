package controller.holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.HolidayDAO;

public class UpdateHolidayController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("staffVO")==null) {
			return "redirect:member/login.jsp";
		}else {
			int hNo= Integer.parseInt(request.getParameter("hNo"));
			String hContent = request.getParameter("hContent");
			String hStartDate = request.getParameter("hStartDate");
			String hEndDate = request.getParameter("hEndDate");
			HolidayDAO.getInstance().updateHoliday(hContent,hStartDate,hEndDate,hNo);
			return "redirect:/dispatcher?commad=read_detail_holiday&hNo="+hNo;
		}
	}

}
