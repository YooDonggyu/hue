package controller.staff;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.PagingBean;
import model.PositionVO;
import model.StaffDAO;
import model.StaffListVO;
import model.StaffVO;

public class ReadTotalUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		StaffVO user = (StaffVO) session.getAttribute("staffVO");
		if(user == null) {
			return "redirect:index.jsp";
		}else {
			if(user.getPositionVO().getpName().equals("관리자")) {
				StaffDAO dao = StaffDAO.getInstance();
				StaffListVO staffListVO = null;
				PagingBean bean = null;
				
				String pnoParameter = request.getParameter("pageNo");
				
				if (pnoParameter == null) {
					bean = new PagingBean(dao.getTotalStaffCount());
				}else {
					int pNo = Integer.parseInt(pnoParameter);
					bean = new PagingBean(dao.getTotalStaffCount(), pNo);
				}
				
				staffListVO = new StaffListVO(dao.getTotalStaff(bean), bean);
				ArrayList<PositionVO> positionList = dao.getPositionList();
				
				request.setAttribute("staffListVO", staffListVO);
				request.setAttribute("positionList", positionList);
				request.setAttribute("url", "/admin/list_staff.jsp");
				return "template/main.jsp";
			}
			return "redirect:index.jsp";
		} 
	}
}
