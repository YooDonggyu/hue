package controller.holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.Controller;
import model.HolidayDAO;
import model.HolidayVO;

public class ReadDetailHolidayController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int hNo = Integer.parseInt(request.getParameter("hNo"));
		HolidayVO vo =  HolidayDAO.getInstance().findDetailHolidayByPno(hNo);
		
		
		
		JSONObject json = new JSONObject();
		json.put("holidayVO", vo);
		
		json.put("staffVO", vo.getStaffVO());
		json.put("positionVO", vo.getStaffVO().getPositionVO());
		request.setAttribute("responseBody", json);
		return "AjaxView";
	}

}
