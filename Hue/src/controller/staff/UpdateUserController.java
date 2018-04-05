package controller.staff;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import model.staff.StaffDAO;
import model.staff.StaffVO;

/**
 * MultipartRequest를 이용하여 파일과 변경된 내용을 읽어 사용자 내용을 업데이트 한다.
 * 파일 첨부를안했을 때 기존 세션에 있던 이미지 경로를 저장하고 있으면 그 경로를 저장.
 * @author ydg
 *
 */

public class UpdateUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		StaffVO sessionVO = (StaffVO)session.getAttribute("staffVO");
		if(sessionVO == null) {
			return "redirect:index.jsp";
		}else {
			MultipartRequest mr = new MultipartRequest(request, request.getServletContext().getRealPath("dist/img"),
					1024 * 1024 * 10, "utf-8", new DefaultFileRenamePolicy());
			File imageFile = mr.getFile("image");
			String imagePath;
			// 파일 첨부 안했을 시
			if (imageFile == null) {
				imagePath = sessionVO.getImagePath();
			} else {
				imagePath = "/dist/img/" + imageFile.getName();
			}
			StaffVO staffVO = new StaffVO(mr.getParameter("id"), mr.getParameter("password"), 
											mr.getParameter("mail"),imagePath, null);
	
			StaffDAO.getInstance().updateStaff(staffVO);
			sessionVO.setImagePath(imagePath);
			return "redirect:dispatcher?command=read_user";
		}
	}
}

