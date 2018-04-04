package controller.holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import controller.Controller;
import model.HolidayDAO;
import model.StaffVO;

public class DeleteHolidayController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		StaffVO staffVO = (StaffVO)session.getAttribute("staffVO");
		int hNo = Integer.parseInt(request.getParameter("hNo"));
		
		boolean flag = HolidayDAO.getInstance().deleteHoliday(hNo, staffVO.getId());
		JSONObject json = null;
		if(flag) {
			json = new JSONObject();
			json.put("flag", "ok");
		}else {
			json = new JSONObject();
			json.put("flag", "fail");
		}
		request.setAttribute("responseBody", json);
		return "AjaxView";
	}
}
