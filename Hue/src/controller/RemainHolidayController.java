package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.HolidayDAO;

public class RemainHolidayController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("voId");
		if(id == null) {
			return "redirect:index.jsp";
		} else {
			//내가 휴가를 신청한 횟수
			int count = HolidayDAO.getInstance().readCountHolidayNum(id);
			//내 휴가 남은 일수
			int result = 0;
			if(count==0) {
				result = HolidayDAO.getInstance().readInitialHoliday(id);
			}else {
				result = HolidayDAO.getInstance().readRemainHoliday(id);
			}
			JSONObject json=null;
			if(result>0) {
				json = new JSONObject();
				json.put("result", result);
			}else {
				json = new JSONObject();
				json.put("result", -1);
			}
			request.setAttribute("responseBody", json);
			return "AjaxView";
		}
	}
}
