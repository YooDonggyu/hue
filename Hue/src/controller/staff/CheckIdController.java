package controller.staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.Controller;
import model.staff.StaffDAO;

public class CheckIdController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		if(id==null) {
			request.setAttribute("message", "id값을 받아오지 못했어..");
			return "error.jsp";
		}else {
			boolean flag=StaffDAO.getInstance().checkId(id);
			JSONObject json=null;
			if(!flag) {//검색결과가 없을때 
				json=new JSONObject();
				json.put("flag", "true");
			}else {	//검색결과가 있을때 
				json=new JSONObject();
				json.put("flag", "fail");
			}
			request.setAttribute("responseBody", json);
			return "AjaxView";
		}
	}
}
