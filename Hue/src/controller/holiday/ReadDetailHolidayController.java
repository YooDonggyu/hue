package controller.holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import controller.Controller;
import model.holiday.HolidayDAO;
import model.holiday.HolidayVO;
import model.staff.StaffVO;

/**
 * 
 * 클릭한 휴가 상세 내용을 가겨 온다.
 * JSONArray에 JSONObect를 담는다.
 * HolidayVO 객체 안에 StaffVO가 있고 StaffVO 안에 PositionVO가 있기 때문에
 * JSONArray에서 접근하려면 StaffVO와 PositionVO를 JSONObject로 만들어서 put 해줘야한다.
 * @author ydg
 *
 */
public class ReadDetailHolidayController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		StaffVO sessionVO = (StaffVO)session.getAttribute("staffVO");
		if(sessionVO == null) {
			return "redirect:index.jsp";
		}else {
			int hNo = Integer.parseInt(request.getParameter("hNo"));
			HolidayVO vo =  HolidayDAO.getInstance().findDetailHolidayByPno(hNo);
			JSONObject holidayVO = new JSONObject();
			JSONObject staffVO = new JSONObject();
			
			//position
			JSONObject positionVO = new JSONObject();
			positionVO.put("pId", vo.getStaffVO().getPositionVO().getpId());
			positionVO.put("pName", vo.getStaffVO().getPositionVO().getpName());
			positionVO.put("pHolidayCount", vo.getStaffVO().getPositionVO().getpHolidayCount());
			
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
}
