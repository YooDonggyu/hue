package controller.holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import controller.Controller;
import model.HolidayDAO;
import model.StaffVO;

public class UpdateHolidayFlagController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		StaffVO sessionVO = (StaffVO)session.getAttribute("staffVO");
		if(sessionVO == null) {
			return "redirect:index.jsp";
		}else {
			int hNo = Integer.parseInt(request.getParameter("hNo"));
			String status = request.getParameter("status");	
			String reason = request.getParameter("denyHolidayReason");
			
			HolidayDAO.getInstance().updateHolidayFlagByHnum(hNo, sessionVO.getId(), status, reason);
			
			JSONObject json = null;
			json = new JSONObject();
			json.put("flag", "ok");
			
			request.setAttribute("responseBody", json);
			return "AjaxView";
		}
	}
}
