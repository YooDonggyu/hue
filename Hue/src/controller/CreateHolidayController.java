package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HolidayDAO;
import model.HolidayVO;
import model.StaffVO;

public class CreateHolidayController implements Controller {

	/**
	 * 로그인한 직원의 정보를 가져오고,
	 * 휴가시작일, 휴가마지막일, 신청내용을 입력받아
	 * HolidayDAO로 보내주는 메소드.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		StaffVO sessionVO = (StaffVO)session.getAttribute("staffVO");
		if(sessionVO == null) {
			return "member/login.jsp";
		}else {
			String voId=request.getParameter("voId");
			String hStartDate=request.getParameter("holidayStartDate");
			String hEndDate=request.getParameter("holidayEndDate");
			String hContent=request.getParameter("holidayContent");
			
			try {
				HolidayDAO.getInstance().createHoliday(new HolidayVO(0, hStartDate, hEndDate, null, hContent, null, new StaffVO(voId)));
				return "redirect:dispatcher?command=read_holiday";
			}catch (Exception e) {
				return "redirect:error.jsp";
			}
		}
	}
}
