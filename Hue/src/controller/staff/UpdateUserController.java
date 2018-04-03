package controller.staff;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import model.StaffDAO;
import model.StaffVO;

public class UpdateUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MultipartRequest mr = new MultipartRequest(request, request.getServletContext().getRealPath("upload/image"),
				1024 * 1024 * 10, "utf-8", new DefaultFileRenamePolicy());
		File imageFile = mr.getFile("image");
		String imagePath;
		// 파일 첨부 안했을 시
		if (imageFile == null) {
			imagePath = ((StaffVO) session.getAttribute("staffVO")).getImagePath();
		} else {
			imagePath = "/upload/image/" + imageFile.getName();
		}
		StaffVO staffVO = new StaffVO(mr.getParameter("id"), mr.getParameter("password"), 
										mr.getParameter("mail"),imagePath, null);

		StaffDAO.getInstance().updateStaff(staffVO);
		return "redirect:dispatcher?command=read_user";
	}
}

