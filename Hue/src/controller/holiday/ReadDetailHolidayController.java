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
		System.out.println(vo);
		
		JSONObject holidayVO = new JSONObject();
		JSONObject staffVO = new JSONObject();
		JSONObject positionVO = new JSONObject(vo.getStaffVO().getPositionVO());
		//staff
		staffVO.put("positionVO", positionVO);
		staffVO.put("id",vo.getStaffVO().getId());
		staffVO.put("password",vo.getStaffVO().getPassword());
		staffVO.put("name",vo.getStaffVO().getName());
		staffVO.put("mail",vo.getStaffVO().getMail());
		staffVO.put("imagePath",vo.getStaffVO().getImagePath());
		//holiday
		
		holidayVO.put("hNo",vo.gethNo());
		holidayVO.put("hStartDate",vo.gethStartDate() );
		holidayVO.put("hEndDate",vo.gethEndDate() );
		holidayVO.put("hRegDate", vo.gethRegDate());
		holidayVO.put("hContent", vo.gethContent());
		holidayVO.put("hFlag",vo.gethFlag() );
		holidayVO.put("hReason",vo.gethReason() );
		holidayVO.put("staffVO",staffVO );
		
		request.setAttribute("responseBody", holidayVO);
		return "AjaxView";
	}

}
