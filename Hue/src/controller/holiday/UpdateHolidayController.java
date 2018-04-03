package controller.holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class UpdateHolidayController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hContent = request.getParameter("hContent");
		String hStartDate = request.getParameter("hStartDate");
		String hEndDate = request.getParameter("hEndDate");
		
		return null;
	}

}
