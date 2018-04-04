package controller.holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.HolidayDAO;
import model.StaffVO;

public class UpdateHolidayController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		StaffVO vo=(StaffVO) session.getAttribute("staffVO");
		if(session==null || session.getAttribute("staffVO")==null) {
			return "redirect:member/login.jsp";
		}else {
			String id=vo.getId();
			int totalHoliday= vo.getPositionVO().getpHolidayCount();
			int result=HolidayDAO.getInstance().findHolidayCountById(id);
			int hCount_result= totalHoliday-result;
			request.setAttribute("hCount", hCount_result);
			if(hCount_result>0) {
				int hNo= Integer.parseInt(request.getParameter("hNo"));
				String hContent = request.getParameter("hContent");
				String hStartDate = request.getParameter("hStartDate");
				String hEndDate = request.getParameter("hEndDate");
				HolidayDAO.getInstance().updateHoliday(hContent,hStartDate,hEndDate,hNo);
				return "redirect:/dispatcher?commad=read_detail_holiday&hNo="+hNo;
			}else {
				return "holiday/holiday_fail.jsp";
			}
		}
	}

}
