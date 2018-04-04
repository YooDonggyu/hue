package controller.holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.HolidayDAO;
import model.ListVO;
import model.PagingBean;
import model.StaffVO;

public class ReadHolidayController implements Controller {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpSession session=request.getSession(false);
    
    StaffVO user=(StaffVO)session.getAttribute("staffVO");
    HolidayDAO dao=HolidayDAO.getInstance();
    
    ListVO listVO=null;
    PagingBean bean;
    String pnoParameter=request.getParameter("pageNo");
    String condition=request.getParameter("condition");
    if(pnoParameter==null){
      if(user.getPositionVO().getpName().equals("점장")){
        if(condition!=null){
          bean=new PagingBean(dao.getTotalPostCount(condition));
        } else{
          bean=new PagingBean(dao.getTotalPostCount());
        }
      } else{ //직원일때
        if(condition!=null){
          bean=new PagingBean(dao.getUserPostCount(user.getId(),condition));
        } else{
          bean=new PagingBean(dao.getUserPostCount(user.getId()));
        }
      }
    } else{
      int pNo=Integer.parseInt(pnoParameter);
      if(user.getPositionVO().getpName().equals("점장")){
        if(condition!=null){
          bean=new PagingBean(dao.getTotalPostCount(condition),pNo);
        } else{
          bean=new PagingBean(dao.getTotalPostCount(),pNo);
        }
      } else{ //직원일때
        if(condition!=null){
          bean=new PagingBean(dao.getUserPostCount(user.getId(),condition),pNo);
        } else{
          bean=new PagingBean(dao.getUserPostCount(user.getId()),pNo);
        }
      }
    }
    
    if(user.getPositionVO().getpName().equals("점장")){
      if(condition==null){
        listVO=new ListVO(dao.getTotalHoliday(bean),bean);
      } else{
        listVO=new ListVO(dao.getTotalHoliday(condition,bean),bean);
      }
    } else{ //직원일때
      if(condition==null){
        listVO=new ListVO(dao.findHolidayById(user.getId(),bean),bean);
      } else{
        listVO=new ListVO(dao.findHolidayById(user.getId(),condition,bean),bean);
      }
    }
    
    //request.setAttribute("url", "/board/list.jsp");
    request.setAttribute("listVO", listVO);
    request.setAttribute("useHoliday",dao.findHolidayCountById(user.getId()));
    
    
    return "holiday/list_holiday.jsp";
  }

}
